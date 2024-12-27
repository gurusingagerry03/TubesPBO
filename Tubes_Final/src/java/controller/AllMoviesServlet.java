/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Movie;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */

@WebServlet("/all-movies")
public class AllMoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if (action.equals("edit")) {
            editMovie(request, response);
        } else if (action.equals("delete")) {
            deleteMovie(request, response);
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

        if (action.equals("create")) {
            createMovie(request, response);
        } else if (action.equals("update")) {
            updateMovie(request, response);
        }
    }

    private void createMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double rating = Double.parseDouble(request.getParameter("rating"));

        String query = "INSERT INTO movies (title, release_year, genre, director, duration, rating) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setInt(2, releaseYear);
            statement.setString(3, genre);
            statement.setString(4, director);
            statement.setInt(5, duration);
            statement.setDouble(6, rating);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?action=all"); 
    }

    private void editMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("id"));

        String query = "SELECT * FROM movies WHERE movie_id = ?";
        Movie movie = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    movie = new Movie(
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getInt("release_year"),
                            resultSet.getString("genre"),
                            resultSet.getString("director"),
                            resultSet.getInt("duration"),
                            resultSet.getDouble("rating")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("movie", movie);
        
        request.getRequestDispatcher("movies/allMovies/editAllMovie.jsp").forward(request, response);
    }

    private void updateMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        String title = request.getParameter("title");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        int duration = Integer.parseInt(request.getParameter("duration"));
        double rating = Double.parseDouble(request.getParameter("rating"));

        String updateQuery = "UPDATE movies SET title = ?, release_year = ?, genre = ?, director = ?, duration = ?, rating = ? WHERE movie_id = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            ) 
        {
            updateStmt.setString(1, title);
            updateStmt.setInt(2, releaseYear);
            updateStmt.setString(3, genre);
            updateStmt.setString(4, director);
            updateStmt.setInt(5, duration);
            updateStmt.setDouble(6, rating);
            updateStmt.setInt(7, movieId);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=all");
    }



    private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        String seriesQuery = "DELETE FROM series_movies WHERE movie_id = ?";
        String animationQuery = "DELETE FROM animated_movies WHERE movie_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement seriesStmt = connection.prepareStatement(seriesQuery);
             PreparedStatement animationStmt = connection.prepareStatement(animationQuery)) {
            seriesStmt.setInt(1, movieId);
            seriesStmt.executeUpdate();
            animationStmt.setInt(1, movieId);
            animationStmt.executeUpdate();
            String movieQuery = "DELETE FROM movies WHERE movie_id = ?";

            try (PreparedStatement movieStmt = connection.prepareStatement(movieQuery)) {
                movieStmt.setInt(1, movieId);
                movieStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("movies?page=all");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "There was an error while deleting the movie.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

}
