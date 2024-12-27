<%-- 
    Document   : insertActor
    Created on : Dec 19, 2024, 8:19:34?PM
    Author     : ASUS
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Actor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Add New Actor</h1>
    <base href="/Tubes_Final/">
    <form action="actors" method="post" class="shadow p-4 rounded">
        <input type="hidden" name="action" value="create">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" name="name" id="name" class="form-control" placeholder="Enter actor name" required>
        </div>

        <div class="mb-3">
            <label for="birthDate" class="form-label">Birth Date</label>
            <input type="date" name="birthDate" id="birthDate" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="gender" class="form-label">Gender</label>
            <select name="gender" id="gender" class="form-select" required>
                <option value="">-- Select Gender --</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success w-100">Add Actor</button>
    </form>
</div>
</body>
</html>

