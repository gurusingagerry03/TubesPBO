<%-- 
    Document   : editActor
    Created on : Dec 19, 2024, 8:32:52?PM
    Author     : ASUS
--%>

<%@ page import="model.Actor" %>
<%
    Actor actor = (Actor) request.getAttribute("actor");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Actor</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Edit Actor</h1>
    
    <form action="actors" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="actorId" value="<%= actor.getId() %>">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" name="name" id="name" class="form-control" value="<%= actor.getName() %>" required>
        </div>

        <div class="mb-3">
            <label for="birthDate" class="form-label">Birth Date</label>
            <input type="date" name="birthDate" id="birthDate" class="form-control" value="<%= actor.getBirthDate() %>" required>
        </div>

        <div class="mb-3">
            <label for="gender" class="form-label">Gender</label>
            <select name="gender" id="gender" class="form-select" required>
                <option value="Male" <%= "Male".equals(actor.getGender()) ? "selected" : "" %>>Male</option>
                <option value="Female" <%= "Female".equals(actor.getGender()) ? "selected" : "" %>>Female</option>
            </select>
        </div>

        <button type="submit" class="btn btn-warning w-100">Update Actor</button>
    </form>
</div>
</body>
</html>

