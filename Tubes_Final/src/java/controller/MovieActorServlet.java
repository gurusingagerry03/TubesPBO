/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Movie;
import model.Actor;
import model.MovieActor;
import util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet("/movie-actors")
public class MovieActorServlet extends HttpServlet {

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
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteMovieActor(request, response);
                    break;
                default:
                    listMovieActors(request, response);
            }
        } else {
            listMovieActors(request, response);
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

        if ("create".equals(action)) {
            createMovieActor(request, response);
        } else if ("update".equals(action)) {
            updateMovieActor(request, response);
        }
    }

private void listMovieActors(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    List<MovieActor> movieActors = new ArrayList<>();
    String query = "SELECT ma.id, ma.role, m.movie_id, m.title, a.actor_id, a.name " +
                   "FROM movie_actors ma " +
                   "JOIN movies m ON ma.movie_id = m.movie_id " +
                   "JOIN actors a ON ma.actor_id = a.actor_id";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String role = rs.getString("role");

            Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    0,      
                    null,   
                    null,  
                    0,      
                    0.0     
            );

            Actor actor = new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("name"),
                    "", // Dummy birthDate
                    ""  // Dummy gender
            );
            movieActors.add(new MovieActor(id, movie, actor, role));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    request.setAttribute("movieActors", movieActors);
    request.getRequestDispatcher("movie_actor/listMovieActor.jsp").forward(request, response);
}

   private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("movies", fetchMovies());
        request.setAttribute("actors", fetchActors());
        request.getRequestDispatcher("movie_actor/insertMovieActor.jsp").forward(request, response);
    }
   
    private void createMovieActor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int actorId = Integer.parseInt(request.getParameter("actorId"));
        String role = request.getParameter("role");

        String query = "INSERT INTO movie_actors (movie_id, actor_id, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);
            stmt.setString(3, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movie-actors");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        int movieId = 0;
        int actorId = 0;
        String role = "";
        String query = "SELECT movie_id, actor_id, role FROM movie_actors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movieId = rs.getInt("movie_id");
                    actorId = rs.getInt("actor_id");
                    role = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("role", role);
        request.setAttribute("currentMovieId", movieId);
        request.setAttribute("currentActorId", actorId);
        request.setAttribute("movies", fetchMovies());
        request.setAttribute("actors", fetchActors());
        request.getRequestDispatcher("movie_actor/editMovieActor.jsp").forward(request, response);
    }




    private void updateMovieActor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int newMovieId = Integer.parseInt(request.getParameter("movieId"));
        int newActorId = Integer.parseInt(request.getParameter("actorId"));
        String role = request.getParameter("role");

        String query = "UPDATE movie_actors SET movie_id = ?, actor_id = ?, role = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newMovieId);
            stmt.setInt(2, newActorId);
            stmt.setString(3, role);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movie-actors");
    }



    private void deleteMovieActor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String query = "DELETE FROM movie_actors WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("movie-actors");
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

    private List<Actor> fetchActors() {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, name FROM actors";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                actors.add(new Actor(rs.getInt("actor_id"), rs.getString("name"), "", ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }
}
