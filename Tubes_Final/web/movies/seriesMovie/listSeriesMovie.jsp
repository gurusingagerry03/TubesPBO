<%-- 
    Document   : listSeriesMovie
    Created on : Dec 18, 2024, 10:10:09?PM
    Author     : ASUS
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.SeriesMovie"%>
<%
            List<SeriesMovie> seriesMovies = (List<SeriesMovie>) request.getAttribute("seriesMovies");

%>
<div class="mb-3 text-end">
    <a href="series-movies?action=create" class="btn btn-primary">Add New Series Movie</a>
</div>
<table class="table table-bordered table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Genre</th>
            <th>Season</th>
            <th>Episode Number</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% if (seriesMovies != null && !seriesMovies.isEmpty()) { %>
            <% for (SeriesMovie movie : seriesMovies) { %>
                <tr>
                    <td><%= movie.getSeriesMovieId()%></td>
                    <td><%= movie.getTitle() %></td>
                    <td><%= movie.getGenre() %></td>
                    <td><%= movie.getSeason() %></td>
                    <td><%= movie.getEpisodeNumber() %></td>
                    <td>
                        <a href="series-movies?action=edit&id=<%= movie.getSeriesMovieId() %>" class="btn btn-warning btn-sm">Edit</a>                               
                        <a href="series-movies?action=delete&id=<%= movie.getSeriesMovieId() %>" class="btn btn-danger btn-sm"
                            onclick="return confirm('Are you sure you want to delete this series movie?');">Delete
            
                        </a>
                    </td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="9" class="text-center">No Series Movies Found</td>
            </tr>
        <% } %>
    </tbody>
</table>




