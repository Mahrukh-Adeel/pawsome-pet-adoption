<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Adopt</title>
    <link rel="stylesheet" href="adopt.css">
    <link rel="stylesheet" href="welcome.css">
    <link rel="stylesheet" href="pets.css"><!--
-->    <link rel="stylesheet" href="style.css">
  
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@100;400;900&family=Jersey+10&family=Kanit:wght@100;900&family=Pixelify+Sans:wght@400;700&display=swap" rel="stylesheet">
    <style>
        #cardss{
            margin-top: 8%;
        }
    </style>
</head>
<body>
<header class="header">
    <div class="header_in">
        <button type="button" class="toggle" id="button">
            <span></span>
        </button>
    </div>
    <button class="comic-button" onclick="logout()">Log Out!</button>
</header>

<div class="sidebar" id="sidebar">
    <ul>
        <li><a href="ViewAdoptPetsServlet">Adopt</a></li>
      <li><a href="ViewMyPetsServlet2">Return</a></li>
      <li><a href="ViewMyPetsServlet">Yours</a></li>
      <li><a href="surrender.jsp">Surrender</a></li>
      <li><a href="account.jsp">Account</a></li>
    </ul>
</div>

<!--    <section class="hero" id="tobe">
            <div>
                <h1>Choose the best one fur you</h1>
            </div>
            
        </section>-->
    <section class="pet" id="cardss">
        <%
    List<Map<String, Object>> pets = (List<Map<String, Object>>) request.getAttribute("pets");
    if (pets != null) {
        for (Map<String, Object> pet : pets) {
        int petId = (Integer) pet.get("petId");
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
                <div class="button-container">
                    <form action="AdoptPetServlet" method="post">
                        <input type="hidden" name="petId" value="<%= petId %>">
                        <button type="submit" class="bttn">Adopt me</button>
                    </form>
                </div>
            </div>
<%
        }
    } else {
        out.println("<p>No pets available.</p>");
    }
%>
    </section>

<script src="portal.js"></script>

</body>
</html>
