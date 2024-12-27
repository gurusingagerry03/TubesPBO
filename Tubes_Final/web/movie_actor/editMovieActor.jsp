<%-- 
    Document   : editMovieActor
    Created on : Dec 19, 2024, 9:21:29?PM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.Movie, model.Actor" %>
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    List<Actor> actors = (List<Actor>) request.getAttribute("actors");
    int currentMovieId = (int) request.getAttribute("currentMovieId");
    int currentActorId = (int) request.getAttribute("currentActorId");
    String role = (String) request.getAttribute("role");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Movie Actor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Edit Movie Actor</h1>

    <form action="movie-actors" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="oldMovieId" value="<%= currentMovieId %>">
        <input type="hidden" name="oldActorId" value="<%= currentActorId %>">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">


        <div class="mb-3">
            <label for="movieId" class="form-label">Movie</label>
            <select name="movieId" id="movieId" class="form-select" required>
                <% for (Movie movie : movies) { %>
                    <option value="<%= movie.getId() %>" <%= movie.getId() == currentMovieId ? "selected" : "" %>>
                        <%= movie.getTitle() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="actorId" class="form-label">Actor</label>
            <select name="actorId" id="actorId" class="form-select" required>
                <% for (Actor actor : actors) { %>
                    <option value="<%= actor.getId() %>" <%= actor.getId() == currentActorId ? "selected" : "" %>>
                        <%= actor.getName() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <input type="text" name="role" id="role" class="form-control" value="<%= role %>" required>
        </div>

        <button type="submit" class="btn btn-warning w-100">Update Movie Actor</button>
    </form>
</div>
</body>
</html>
