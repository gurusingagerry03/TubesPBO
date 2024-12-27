<%-- 
    Document   : listActor
    Created on : Dec 19, 2024, 8:16:29?PM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.Actor" %>
<%
    List<Actor> actors = (List<Actor>) request.getAttribute("actors");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Actor List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">List of Actors</h1>

            <div class="mb-3 text-end">
                <a href="actors/insertActor.jsp" class="btn btn-primary">Add New Actor</a>
            </div>

            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Birth Date</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (actors != null && !actors.isEmpty()) { %>
                        <% for (Actor actor : actors) { %>
                            <tr>
                                <td><%= actor.getId() %></td>
                                <td><%= actor.getName() %></td>
                                <td><%= actor.getBirthDate() %></td>
                                <td><%= actor.getGender() %></td>
                                <td>
                                    <a href="actors?action=edit&id=<%= actor.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="actors?action=delete&id=<%= actor.getId() %>" 
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Are you sure you want to delete this actor?');">Delete</a>
                                </td>
                            </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="5" class="text-center">No actors found.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="text-center mt-5">
                <a href="/Tubes_Final/" class="btn btn-outline-secondary">Back to index</a>
           </div>
        </div>
    </body>
</html>

