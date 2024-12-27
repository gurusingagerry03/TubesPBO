<%-- 
    Document   : editAllMovie
    Created on : Dec 18, 2024, 9:55:32 PM
    Author     : ASUS
--%>

<%@ page import="model.Movie" %>
<%
    Movie movie = (Movie) request.getAttribute("movie");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Edit Movie</h2>
    <form action="all-movies" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="movieId" value="<%= movie.getId() %>">
        <div class="mb-3">
            <label for="title" class="form-label">Judul</label>
            <input type="text" class="form-control" id="title" name="title" value="${movie.title}" required>
        </div>
        <div class="mb-3">
            <label for="releaseYear" class="form-label">Tahun Rilis</label>
            <input type="number" class="form-control" id="releaseYear" name="releaseYear" value="${movie.releaseYear}" required>
        </div>
        <div class="mb-3">
            <label for="genre" class="form-label">Genre</label>
            <select class="form-select" id="genre" name="genre" required>
                <option value="Action" ${movie.genre == 'Action' ? 'selected' : ''}>Action</option>
                <option value="Comedy" ${movie.genre == 'Comedy' ? 'selected' : ''}>Comedy</option>
                <option value="Drama" ${movie.genre == 'Drama' ? 'selected' : ''}>Drama</option>
                <option value="Horror" ${movie.genre == 'Horror' ? 'selected' : ''}>Horror</option>
                <option value="Science Fiction" ${movie.genre == 'Science Fiction' ? 'selected' : ''}>Science Fiction</option>
                <option value="Adventure" ${movie.genre == 'Adventure' ? 'selected' : ''}>Adventure</option>
                <option value="Fantasy" ${movie.genre == 'Fantasy' ? 'selected' : ''}>Fantasy</option>
                <option value="Thriller" ${movie.genre == 'Thriller' ? 'selected' : ''}>Thriller</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="director" class="form-label">Sutradara</label>
            <input type="text" class="form-control" id="director" name="director" value="${movie.director}" required>
        </div>
        <div class="mb-3">
            <label for="duration" class="form-label">Durasi (Menit)</label>
            <input type="number" class="form-control" id="duration" name="duration" value="${movie.duration}" required>
        </div>
        <div class="mb-3">
            <label for="rating" class="form-label">Rating</label>
            <input type="number" step="0.1" class="form-control" id="rating" name="rating" value="${movie.rating}" required>
        </div>
        <button type="submit" class="btn btn-success">Simpan Perubahan</button>
        <a href="movies?action=list" class="btn btn-secondary">Batal</a>
    </form>
    <div class="text-center mt-3">
        <a href="movies" class="btn btn-outline-secondary">Back to Animated Movies</a>
    </div>
</div>
>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


