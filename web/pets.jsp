<%-- 
    Document   : pets
    Created on : May 23, 2024, 10:26:42 PM
    Author     : Mkh_A
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pawsome Adoptions</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="pets.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jersey+10&family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Pixelify+Sans:wght@400..700&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Fira+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Jersey+10&family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Pixelify+Sans:wght@400..700&display=swap" rel="stylesheet">

    </head>
    <body>
        <header>
        <nav>
            <a href="index.jsp"><h3>Pawsome Adoptions</h3></a>
            <ul class="nav-links">
              <li><a href="signup.jsp">SignUp</a></li>
              <li><a href="login.html">LogIn</a></li>
            </ul>
        </nav>       
    </header>
    <main>
        
        <section class="hero" id="tobe">
            <div>
                <h1>Pets are here waiting just fur you</h1>
                <span>PS: Just SignUp to have them</span>
            </div>
            
        </section>
        <section class="pet">
            <%
        Map<Integer, Map<String, Object>> pets = (Map<Integer, Map<String, Object>>) request.getAttribute("pets");
        if (pets != null) {
            for (Map.Entry<Integer, Map<String, Object>> entry : pets.entrySet()) {
                Map<String, Object> pet = entry.getValue();
                String img = (String) pet.get("img");
                String species = (String) pet.get("species");
                String name = (String) pet.get("name");
                int age = (Integer) pet.get("age");
                String description = (String) pet.get("description");
                boolean available = (Boolean) pet.get("available");
    %>
        <div class="card">
            <div class="card-image" style="background-image: url('<%= img %>');"></div>
            <div class="category"><%= species %></div>
            <div class="heading"><%= name %>
<div class="author"> Age: <span class="name"><%= age %></span></div>
                <div class="author"> Available: <span class="name"><%= available ? "Yes" : "No" %></span></div>
            </div>
            <div class="heading">Description: <%= description %></div>
        </div>
    <%
            }
        } else {
            out.println("<p>No pets available.</p>");
        }
    %>

        </section>
        <script src="script.js"></script>
    </main>
    </body>
</html>
