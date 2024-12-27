/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Actor;
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
@WebServlet("/actors")
public class ActorServlet extends HttpServlet {

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
                    editActor(request, response);
                    break;
                case "delete":
                    deleteActor(request, response);
                    break;
                default:
                    listActors(request, response);
            }
        } else {
            listActors(request, response);
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
                    createActor(request, response);
                    break;
                case "update":
                    updateActor(request, response);
                    break;
            }
        }
    }

    private void listActors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actors";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("name"),
                        rs.getString("birth_date"),
                        rs.getString("gender")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("actors", actors);
        request.getRequestDispatcher("actors/listActor.jsp").forward(request, response);
    }


    private void createActor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String birthDate = request.getParameter("birthDate");
        String gender = request.getParameter("gender");

        String query = "INSERT INTO actors (name, birth_date, gender) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, birthDate);
            stmt.setString(3, gender);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("actors");
    }

    private void editActor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int actorId = Integer.parseInt(request.getParameter("id"));

        String query = "SELECT * FROM actors WHERE actor_id = ?";
        Actor actor = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, actorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    actor = new Actor(
                            rs.getInt("actor_id"),
                            rs.getString("name"),
                            rs.getString("birth_date"),
                            rs.getString("gender")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("actor", actor);
        request.getRequestDispatcher("actors/editActor.jsp").forward(request, response);
    }

    private void updateActor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int actorId = Integer.parseInt(request.getParameter("actorId"));
        String name = request.getParameter("name");
        String birthDate = request.getParameter("birthDate");
        String gender = request.getParameter("gender");

        String query = "UPDATE actors SET name = ?, birth_date = ?, gender = ? WHERE actor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, birthDate);
            stmt.setString(3, gender);
            stmt.setInt(4, actorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("actors");
    }

    private void deleteActor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int actorId = Integer.parseInt(request.getParameter("id"));

        String query = "DELETE FROM actors WHERE actor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, actorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("actors");
    }
}
