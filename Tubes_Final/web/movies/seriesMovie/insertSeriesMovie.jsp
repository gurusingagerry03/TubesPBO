<%-- 
    Document   : insertSeriesMovie
    Created on : Dec 18, 2024, 10:17:54?PM
    Author     : ASUS
--%>

<%@ page import="java.util.List, model.Movie" %>
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Series Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Insert Series Movie</h1>

    <form action="series-movies" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="create">
        <div class="mb-3">
            <label for="movie" class="form-label">Select Movie</label>
            <select name="movie_id" id="movie" class="form-select" required>
                <option value="">-- Select a Movie --</option>
                    <% for (Movie movie : movies) { %>
                        <option value="<%= movie.getId() %>">
                            <%= movie.getTitle() %>
                        </option>
                    <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="season" class="form-label">Season</label>
            <input type="number" name="season" id="season" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="episode" class="form-label">Episode Number</label>
            <input type="number" name="episode_number" id="episode" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Create</button>
    </form>
    <div class="text-center mt-3">
        <a href="movies?page=series" class="btn btn-outline-secondary">Back to Animated Movies</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

