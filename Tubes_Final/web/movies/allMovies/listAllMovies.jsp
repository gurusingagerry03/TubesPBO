<%-- 
    Document   : listAllMovies
    Created on : Dec 18, 2024, 9:22:32?PM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Movie"%>
<%
    ArrayList<Movie> allMovies = (ArrayList<Movie>) request.getAttribute("movies");
%>
<div>
   <div class="mb-3 text-end">
        <a href="movies/allMovies/insertAllMovie.jsp" class="btn btn-primary">Add New Movie</a>
   </div>   
</div>
<table class="table table-bordered table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Release Year</th>
            <th>Genre</th>
            <th>Director</th>
            <th>Duration (min)</th>
            <th>Rating</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% if (allMovies != null && !allMovies.isEmpty()) { %>
            <% for (Movie movie : allMovies) { %>
                <tr>
                    <td><%= movie.getId()%></td>
                    <td><%= movie.getTitle() %></td>
                    <td><%= movie.getReleaseYear() %></td>
                    <td><%= movie.getGenre() %></td>
                    <td><%= movie.getDirector() %></td>
                    <td><%= movie.getDuration() %></td>
                    <td><%= movie.getRating() %></td>
                    <td>      
                        <a href="all-movies?action=edit&id=<%= movie.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="all-movies?action=delete&id=<%= movie.getId() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Apakah Anda yakin ingin menghapus film ini?');">Delete</a>
                    </td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="7" class="text-center">No Movies Found</td>
            </tr>
        <% } %>
    </tbody>
</table>


