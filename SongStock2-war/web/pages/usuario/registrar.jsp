<%-- 
    Document   : registrar
    Created on : 7/03/2015, 10:59:35 PM
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Song stock - Unimonito</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../../css/stylecss3.css" />
    </head>
    <body>
        <h1>Regisrar usuario</h1>
        <form id="registrausuario" action="../../UsuarioServlet" method="post">
            <input type="hidden" name="action" id="action" value="registrar">
            <label for="nombre">Nombre : </label>
            <input type="text" name="nombre" id="nombre" required="true" />
            <br />
            <label for="apellido">Apellido : </label>
            <input type="text" name="apellido" id="apellido" required="true" />
            <br />
            <label for="correo">Correo : </label>
            <input type="email" name="correo" id="correo" required="true" />
            <br />
            <label for="correo">Contraseña : </label>
            <input type="password" name="contrasena" id="contrasena" required="true" />
            <br />
            <input type="submit" value="Registrarse" />  
        </form>
    </body>
</html>
