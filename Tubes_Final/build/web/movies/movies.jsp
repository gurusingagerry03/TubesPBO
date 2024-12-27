<%-- 
    Document   : movies
    Created on : Dec 18, 2024, 9:19:58 PM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Movies List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Daftar Film</h1>
            <div class="mb-4">
                <a href="movies?page=all" class="btn btn-outline-primary btn-sm">All Movies</a>
                <a href="movies?page=series" class="btn btn-outline-primary btn-sm">Series Movies</a>
                <a href="movies?page=animation" class="btn btn-outline-primary btn-sm">Animated Movies</a>
            </div>
            <div class="mt-3">
                <% 
                    String includePage = "allMovies/listAllMovies.jsp";
                    if (request.getParameter("page") != null && request.getParameter("page").equals("series")) {
                        includePage = "seriesMovie/listSeriesMovie.jsp";
                    }else if (request.getParameter("page") != null && request.getParameter("page").equals("animation")) {
                        includePage = "animatedMovie/listAnimatedMovie.jsp";
                    }
                %>

                <jsp:include page="<%= includePage %>" />

            </div>
            <div class="text-center mt-5">
                <a href="/Tubes_Final/" class="btn btn-outline-secondary">Back to index</a>
            </div>
        </div>>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

