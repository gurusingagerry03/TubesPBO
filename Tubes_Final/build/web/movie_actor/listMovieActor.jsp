<%-- 
    Document   : listMovieActor
    Created on : Dec 19, 2024, 9:03:20?PM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.MovieActor" %>
<%
    List<MovieActor> movieActors = (List<MovieActor>) request.getAttribute("movieActors");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Movie Actors List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">List of Movie Actors</h1>
            <div class="mb-3 text-end">
                <a href="movie-actors?action=create" class="btn btn-primary">Add Movie Actor</a>
            </div>
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Movie Title</th>
                        <th>Actor Name</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (movieActors != null && !movieActors.isEmpty()) { %>
                        <% for (MovieActor ma : movieActors) { %>
                            <tr>
                                <td><%= ma.getId() %></td>
                                <td><%= ma.getMovie().getTitle() %></td>
                                <td><%= ma.getActor().getName() %></td>
                                <td><%= ma.getRole() %></td>
                                <td>
                                    <a href="movie-actors?action=edit&id=<%= ma.getId() %>" 
                                       class="btn btn-warning btn-sm">Edit</a>
                                    <a href="movie-actors?action=delete&id=<%= ma.getId() %>" 
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Are you sure you want to delete this record?');">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="5" class="text-center">No Movie Actors Found</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="text-center mt-5">
                <a href="/Tubes_Final/" class="btn btn-outline-secondary">Back to index</a>
            </div>
        </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

