<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%
    HttpSession userSession = request.getSession(false);
    Object userObj = null;

    if (userSession != null) {
        userObj = userSession.getAttribute("user");
    }

    User user = null;
    if (userObj instanceof User) {
        user = (User) userObj;
    } else if (userObj instanceof String) {
        user = new User(0, (String) userObj, (String) userObj, "");
    } else {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apps-SoC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #373b3e;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }
        .app-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
            text-align: center;
        }
        .app-button {
            background-color: #1e1e2d;
            color: #ffffff;
            border: none;
            margin: 10px 0;
            width: 100%;
        }
        .app-button:hover {
            background-color: #343a40;
        }
        .footer {
            margin-top: 20px;
            font-size: 0.9rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="app-container">
        <p>List of Applications</p>
        <h2>Welcome, <%= user.getFullName() %>!</h2>
        <div>
            <a href="movies?action=all" class="btn app-button">List Movie</a>
            <a href="actors" class="btn app-button">List Actor</a>
            <a href="movie-actors" class="btn app-button">List Movie Actor</a>
        </div>
        <form method="POST" action="login" style="display: inline;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="mt-3 btn btn-danger">Logout</button>
        </form>
    </div>
</body>
</html>
