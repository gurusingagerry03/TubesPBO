/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.SeriesMovie;
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
@WebServlet("/series-movies")
public class SeriesMoviesServlet extends HttpServlet {

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
                    editSeriesMovie(request, response);
                    break;
                case "delete":
                    deleteSeriesMovie(request, response);
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
                    createSeriesMovie(request, response);
                    break;
                case "update":
                    updateSeriesMovie(request, response);
                    break;
            }
        }
    }
    
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("movies", fetchMovies());
        request.getRequestDispatcher("movies/seriesMovie/insertSeriesMovie.jsp").forward(request, response);
    }
    
    private void createSeriesMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        int season = Integer.parseInt(request.getParameter("season"));
        int episodeNumber = Integer.parseInt(request.getParameter("episode_number"));

        String insertQuery = "INSERT INTO series_movies (movie_id, season, episode_number) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {


                    insertStmt.setInt(1, movieId);
                    insertStmt.setInt(2, season);
                    insertStmt.setInt(3, episodeNumber);
                    insertStmt.executeUpdate();

        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=series");
    }
    
    private List<Movie> fetchMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT movie_id, title FROM movies";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        0,
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
    
    private void editSeriesMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int seriesMovieId = Integer.parseInt(request.getParameter("id"));

        String query = "SELECT m.*, s.series_movie_id, s.season, s.episode_number " +
                       "FROM movies m " +
                       "JOIN series_movies s ON m.movie_id = s.movie_id " +
                       "WHERE s.series_movie_id = ?";

        SeriesMovie seriesMovie = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seriesMovieId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    seriesMovie = new SeriesMovie(                        
                            resultSet.getInt("movie_id"),
                            resultSet.getString("title"),
                            resultSet.getInt("release_year"),
                            resultSet.getString("genre"),
                            resultSet.getString("director"),
                            resultSet.getInt("duration"),
                            resultSet.getDouble("rating"),
                            resultSet.getInt("series_movie_id"),
                            resultSet.getInt("season"),
                            resultSet.getInt("episode_number")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("seriesMovie", seriesMovie);
        request.getRequestDispatcher("movies/seriesMovie/editSeriesMovie.jsp").forward(request, response);
    }

    private void updateSeriesMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        int season = Integer.parseInt(request.getParameter("season"));
        int episodeNumber = Integer.parseInt(request.getParameter("episode_number"));

        String query = "UPDATE series_movies SET season = ?, episode_number = ? WHERE series_movie_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, season);
            statement.setInt(2, episodeNumber);
            statement.setInt(3, movieId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movies?page=series");
    }

    private void deleteSeriesMovie(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int seriesMovieId = Integer.parseInt(request.getParameter("id"));

    String seriesQuery = "DELETE FROM series_movies WHERE series_movie_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement seriesStmt = connection.prepareStatement(seriesQuery)) {

        seriesStmt.setInt(1, seriesMovieId);
        seriesStmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    response.sendRedirect("movies?page=series");
}

}
