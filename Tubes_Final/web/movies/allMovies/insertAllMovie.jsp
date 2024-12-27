<%-- 
    Document   : insertAllMovie
    Created on : Dec 18, 2024, 9:37:32 PM
    Author     : ASUS
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tambah Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
    <body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Tambah Movie</h1>
        <base href="/Tubes_Final/">
        <form action="all-movies" method="post">
            <input type="hidden" name="action" value="create">
            <div class="mb-3">
                <label for="title" class="form-label">Judul</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Masukkan judul film" required>
            </div>
            <div class="mb-3">
                <label for="releaseYear" class="form-label">Tahun Rilis</label>
                <input type="number" class="form-control" id="releaseYear" name="releaseYear" placeholder="Masukkan tahun rilis" required>
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">Genre</label>
                <select name="genre" id="genre" class="form-select" required>
                    <option value="Action">Action</option>
                    <option value="Animation">Animation</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Drama">Drama</option>
                    <option value="Horror">Horror</option>
                    <option value="Science Fiction">Science Fiction</option>
                    <option value="Adventure">Adventure</option>
                    <option value="Fantasy">Fantasy</option>
                    <option value="Series">Series</option>
                    <option value="Thriller">Thriller</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="director" class="form-label">Sutradara</label>
                <input type="text" class="form-control" id="director" name="director" placeholder="Masukkan sutradara" required>
            </div>
            <div class="mb-3">
                <label for="duration" class="form-label">Durasi (Menit)</label>
                <input type="number" class="form-control" id="duration" name="duration" placeholder="Masukkan durasi film (menit)" required>
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">Rating</label>
                <input type="number" step="0.1" class="form-control" id="rating" name="rating" placeholder="Masukkan rating film" required>
            </div>
            <button type="submit" class="btn btn-primary">Simpan</button>
            <a href="movies" class="btn btn-secondary">Batal</a>
        </form>
        <div class="text-center mt-3">
            <a href="movies?action=all" class="btn btn-outline-secondary">Back to All Movies</a>
        </div>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

