<%-- 
    Document   : listAnimatedMovie
    Created on : Dec 19, 2024, 12:01:55?AM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.AnimatedMovie" %>
<%
    List<AnimatedMovie> animatedMovies = (List<AnimatedMovie>) request.getAttribute("animatedMovies");
%>
<div class="mb-3 text-end">
    <a href="animated-movies?action=create" class="btn btn-primary">Add New Anomation Studio</a>
</div>
<table class="table table-bordered table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Animation Studio</th>
            <th>Age Recommendation</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% if (animatedMovies != null && !animatedMovies.isEmpty()) { %>
            <% for (AnimatedMovie movie : animatedMovies) { %>
                <tr>
                    <td><%= movie.getAnimatedMovieId()%></td>
                    <td><%= movie.getTitle() %></td>
                    <td><%= movie.getAnimationStudio() %></td>
                    <td><%= movie.getAgeRecommendation() %></td>
                    <td>
                        <a href="animated-movies?action=edit&id=<%= movie.getAnimatedMovieId() %>" class="btn btn-warning btn-sm">Edit</a>                               
                        <a href="animated-movies?action=delete&id=<%= movie.getAnimatedMovieId() %>" class="btn btn-danger btn-sm"
                            onclick="return confirm('Are you sure you want to delete this series movie?');">Delete
            
                        </a>
                    </td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="9" class="text-center">No Animated Movies Found</td>
            </tr>
        <% } %>
    </tbody>
</table>

