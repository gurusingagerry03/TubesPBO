<%-- 
    Document   : insertAnimatedMovie
    Created on : Dec 19, 2024, 1:03:17?AM
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
        <title>Insert Animated Movie</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Insert Animated Movie</h1>
        <form action="animated-movies" method="post" class="shadow p-4 rounded">
            <input type="hidden" name="action" value="create">
            <div class="mb-3">
                <label for="movie" class="form-label">Select Movie</label>
                <select name="movie_id" id="movie" class="form-select" required>
                    <option value="">-- Select a Movie --</option>
                        <% for (Movie movie : movies) { %>
                            <option value="<%= movie.getId() %>">
                                <%= movie.getTitle() %> (<%= movie.getReleaseYear() %>)
                            </option>
                        <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="animationStudio" class="form-label">Animation Studio</label>
                <input type="text" name="animation_studio" id="animationStudio" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="ageRecommendation" class="form-label">Age Recommendation</label>
                <select name="age_recommendation" id="ageRecommendation" class="form-select" required>
                    <option value="">-- Select Age Recommendation --</option>
                    <option value="All Ages">All Ages</option>
                    <option value="7+">7+</option>
                    <option value="13+">13+</option>
                    <option value="18+">18+</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Insert Animated Movie</button>
        </form>
        <div class="text-center mt-3">
            <a href="movies?page=animation" class="btn btn-outline-secondary">Back to Animated Movies</a>
        </div>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

