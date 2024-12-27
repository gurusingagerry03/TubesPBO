/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import model.AnimatedMovie;
import util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Movie;


/**
 *
 * @author ASUS
 */
@WebServlet("/animated-movies")
public class AnimatedMoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    editAnimatedMovie(request, response);
                    break;
                case "delete":
                    deleteAnimatedMovie(request, response);
                    break;
                case "create":
                    showCreateForm(request, response);
                    break;
            }
        } else {
            response.sendRedirect("index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createAnimatedMovie(request, response);
                    break;
                case "update":
                    updateAnimatedMovie(request, response);
                    break;
            }
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("movies", fetchMovies());
        request.getRequestDispatcher("movies/animatedMovie/insertAnimatedMovie.jsp").forward(request, response);
    }

    private List<Movie> fetchMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT m.movie_id, m.title, m.release_year " +
                       "FROM movies m " +
                       "LEFT JOIN animated_movies a ON m.movie_id = a.movie_id " +
                       "WHERE a.movie_id IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        null,
                        null,
                        0,
                        0
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    
    private void createAnimatedMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        String animationStudio = request.getParameter("animation_studio");
        String ageRecommendation = request.getParameter("age_recommendation");
        String insertQuery = "INSERT INTO animated_movies (movie_id, animation_studio, age_recommendation) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, movieId);
                    insertStmt.setString(2, animationStudio);
                    insertStmt.setString(3, ageRecommendation);
                    insertStmt.executeUpdate();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=animation");
    }

    private void editAnimatedMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int animatedMovieId = Integer.parseInt(request.getParameter("id"));

        String query = "SELECT m.*,a.animated_movie_id, a.animation_studio, a.age_recommendation " +
                       "FROM movies m JOIN animated_movies a ON m.movie_id = a.movie_id " +
                       "WHERE a.animated_movie_id = ?";

        AnimatedMovie animatedMovie = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, animatedMovieId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    animatedMovie = new AnimatedMovie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getInt("release_year"),
                            resultSet.getString("genre"),
                            resultSet.getString("director"),
                            resultSet.getInt("duration"),
                            resultSet.getDouble("rating"),
                            resultSet.getInt("animated_movie_id"),
                            resultSet.getString("animation_studio"),
                            resultSet.getString("age_recommendation")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("animatedMovie", animatedMovie);
        request.getRequestDispatcher("movies/animatedMovie/editAnimatedMovie.jsp").forward(request, response);
    }

    private void updateAnimatedMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        String animationStudio = request.getParameter("animation_studio");
        String ageRecommendation = request.getParameter("age_recommendation");

        String query = "UPDATE animated_movies SET animation_studio = ?, age_recommendation = ? WHERE animated_movie_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, animationStudio);
            statement.setString(2, ageRecommendation);
            statement.setInt(3, movieId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=animation");
    }

    private void deleteAnimatedMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int animatedMovieId = Integer.parseInt(request.getParameter("id"));

        String query = "DELETE FROM animated_movies WHERE animated_movie_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, animatedMovieId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=animation");
    }
}