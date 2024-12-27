<%-- 
    Document   : editAnimatedMovie
    Created on : Dec 19, 2024, 1:03:25?AM
    Author     : ASUS
--%>

<%@ page import="model.AnimatedMovie" %>
<%
    AnimatedMovie animatedMovie = (AnimatedMovie) request.getAttribute("animatedMovie");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Animated Movie</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Edit Animated Movie</h1>
        <form action="animated-movies" method="post" class="shadow p-4 rounded">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= animatedMovie.getAnimatedMovieId() %>">
            <input type="hidden" name="movie_id" value="<%= animatedMovie.getId() %>">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" name="title" id="title" class="form-control" value="<%= animatedMovie.getTitle() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="animationStudio" class="form-label">Animation Studio</label>
                <input type="text" name="animation_studio"" id="animation_studio"" class="form-control" value="<%= animatedMovie.getAnimationStudio() %>" required>
            </div>
            <div class="mb-3">
                <label for="ageRecommendation" class="form-label">Age Recommendation</label>
                <select name="age_recommendation" id="ageRecommendation" class="form-select" required>
                    <option value="All Ages" <%= "All Ages".equals(animatedMovie.getAgeRecommendation()) ? "selected" : "" %>>All Ages</option>
                    <option value="7+" <%= "7+".equals(animatedMovie.getAgeRecommendation()) ? "selected" : "" %>>7+</option>
                    <option value="13+" <%= "13+".equals(animatedMovie.getAgeRecommendation()) ? "selected" : "" %>>13+</option>
                    <option value="18+" <%= "18+".equals(animatedMovie.getAgeRecommendation()) ? "selected" : "" %>>18+</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Update Animated Movie</button>
        </form>
        <div class="text-center mt-3">
            <a href="movies?page=animation" class="btn btn-outline-secondary">Back to Animated Movies</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

