<%-- 
    Document   : signup
    Created on : May 23, 2024, 12:03:51 PM
    Author     : Mkh_A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="signup.css">
</head>
<body>
    <form class="form" action="SignUpServlet" method="post">
        <span class="heading">Sign Up</span>
        <span class="Name">Full Name</span>
        <input name="name" placeholder="Enter Full Name" type="text" class="input" />
        <span class="Mail">E-Mail</span>
        <input name="email" placeholder="Enter E-Mail" type="email" class="input" />
        <span class="Password">Password</span>
        <input name="password" placeholder="Enter Password" type="password" class="input" />
        <button>Submit</button>
      </form>
      
</body>
</html>
