/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Movie;
import model.SeriesMovie;
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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String page = request.getParameter("page");
        
        if (page != null) {
            switch (page) {
                case "series":
                    listSeriesMovies(request, response);
                    break;
                case "animation":
                    listAnimatedMovies(request, response);
                    break;
                default:
                    listAllMovies(request, response);
                    break;
            }
        } else {
            listAllMovies(request, response);
        }
    }

    private void listAllMovies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getInt("duration"),
                        rs.getDouble("rating")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("movies", movies);
        request.getRequestDispatcher("movies/movies.jsp").forward(request, response);
    }

    private void listSeriesMovies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SeriesMovie> seriesMovies = new ArrayList<>();
        String query = "SELECT m.*, s.series_movie_id, s.season, s.episode_number " +
                       "FROM movies m JOIN series_movies s ON m.movie_id = s.movie_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                seriesMovies.add(new SeriesMovie(
                        rs.getInt("series_movie_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getInt("duration"),
                        rs.getDouble("rating"),
                        rs.getInt("series_movie_id"),
                        rs.getInt("season"),
                        rs.getInt("episode_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("seriesMovies", seriesMovies);
        request.getRequestDispatcher("movies/movies.jsp").forward(request, response);
    }

    private void listAnimatedMovies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AnimatedMovie> animatedMovies = new ArrayList<>();
        String query = "SELECT m.*,a.animated_movie_id, a.animated_movie_id,a.animation_studio, a.age_recommendation " +
                        "FROM movies m JOIN animated_movies a ON m.movie_id = a.movie_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                animatedMovies.add(new AnimatedMovie(
                        rs.getInt("animated_movie_id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getInt("duration"),
                        rs.getDouble("rating"),
                        rs.getInt("animated_movie_id"),
                        rs.getString("animation_studio"),
                        rs.getString("age_recommendation")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("animatedMovies", animatedMovies);
        request.getRequestDispatcher("movies/movies.jsp").forward(request, response);
    }
}
