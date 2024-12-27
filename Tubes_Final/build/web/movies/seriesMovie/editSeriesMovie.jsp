<%-- 
    Document   : editSeriesMovie
    Created on : Dec 18, 2024, 10:28:09?PM
    Author     : ASUS
--%>

<%@ page import="model.SeriesMovie" %>
<%
    SeriesMovie seriesMovie = (SeriesMovie) request.getAttribute("seriesMovie");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Series Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Edit Series Movie</h1>

    <form action="series-movies" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= seriesMovie.getSeriesMovieId() %>">
        <input type="hidden" name="movie_id" value="<%= seriesMovie.getId() %>">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" id="title" class="form-control" value="<%= seriesMovie.getTitle() %>" readonly>
        </div>
        <div class="mb-3">
            <label for="season" class="form-label">Season</label>
            <input type="number" name="season" id="season" class="form-control" value="<%= seriesMovie.getSeason() %>" required>
        </div>
        <div class="mb-3">
            <label for="episode" class="form-label">Episode Number</label>
            <input type="number" name="episode_number" id="episode" class="form-control" value="<%= seriesMovie.getEpisodeNumber() %>" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Update</button>
    </form>
    <div class="text-center mt-3">
        <a href="movies?page=series" class="btn btn-outline-secondary">Back to Series Movies</a>
    </div>
</div>
</body>
</html>

