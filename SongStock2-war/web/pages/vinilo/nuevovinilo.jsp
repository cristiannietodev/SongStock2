<%-- 
    Document   : nuevocatalogo
    Created on : 8/03/2015, 12:55:00 PM
    Author     : Cristian
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo vinilo</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../../css/stylecss3.css" />
        
    </head>
    <body style="">
        <form id="registravinilo" action="../../ViniloServlet" method="post">
            <h1>Registrar nuevo vinilo</h1>
            <input type="hidden" name="action" value="registrar" />
            <br />
            <label for="nombre" >Nombre vinilo</label>
            <input type="text" name="nombre" id="nombre" required="required" />
            <br />
            <label for="artista" >Artista</label>
            <input type="text" name="artista" id="artista" required="required" />
            <br />
            <label for="ano" >Año</label>
            <input type="number" name="ano" id="ano" required="required" min="1900"  />
            <br />
            <label for="precio" >Precio</label>
            <input type="number" name="precio" id="precio" required="required" min="1" step="any"/>
            <br />
            <label for="unidades" >Cant unidades</label>
            <input type="number" name="unidades" id="unidades" required="required" min="1"/>
            <br />
            <label for="canciones" >Cant canciones</label>
            <input type="number" name="canciones" id="canciones" required="required" min="1"/>
            <br />
            <input type="submit" value="Registrar disco" />  
        </form>
    </body>
</html>
