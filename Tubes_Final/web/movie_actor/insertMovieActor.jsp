<%-- 
    Document   : insertMovieActor
    Created on : Dec 19, 2024, 9:15:35?PM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.Movie, model.Actor" %>
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    List<Actor> actors = (List<Actor>) request.getAttribute("actors");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Movie Actor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Add Movie Actor</h1>

    <form action="movie-actors" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="create">
        <div class="mb-3">
            <label for="movieId" class="form-label">Movie</label>
            <select name="movieId" id="movieId" class="form-select" required>
                <option value="">-- Select a Movie --</option>
                <% for (Movie movie : movies) { %>
                    <option value="<%= movie.getId() %>"><%= movie.getTitle() %></option>
                <% } %>
            </select>
        </div>
        <div class="mb-3">
            <label for="actorId" class="form-label">Actor</label>
            <select name="actorId" id="actorId" class="form-select" required>
                <option value="">-- Select an Actor --</option>
                <% for (Actor actor : actors) { %>
                    <option value="<%= actor.getId() %>"><%= actor.getName() %></option>
                <% } %>
            </select>
        </div>
        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <input type="text" name="role" id="role" class="form-control" placeholder="Enter the role" required>
        </div>
        <button type="submit" class="btn btn-success w-100">Add Movie Actor</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
