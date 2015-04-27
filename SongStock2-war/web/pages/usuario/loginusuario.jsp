<%-- 
    Document   : index
    Created on : 4/03/2015, 07:44:23 PM
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Song stock - Unimonitoss</title>
        <link rel="stylesheet" type="text/css" href="/SongStock/css/style.css" />
        <link rel="stylesheet" type="text/css" href="/SongStock/css/stylecss3.css" />
    </head>
    <body>
        <h2>Autenticacion de usuario</h2>
        <div align="left">
            <a href="registrar.jsp">Registrar</a>
        </div>
        <form action="../../LoginServlet" method="post">  

            <label for="email">Email:</label>
            <input type="text" name="email" required="true"/><br/>
            <br/>  
            <label for="password">Contraseña:</label>
            <input type="password" name="contrasena" required="true"/><br/><br/>  
            <input type="submit" value="login"/>  
        </form>  
    </body>
</html>
