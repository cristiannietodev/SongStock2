<%-- 
    Document   : index
    Created on : 4/03/2015, 07:44:23 PM
    Author     : Cristian
<jsp:forward page="/cancionesServlet?action=listarcanciones" />
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Song Stock</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/stylecss3.css" />
    </head>
    <body class="no-js">
        <div align="right">
            <a href="${pageContext.request.contextPath}/logout"><img alt="Logout" src="img/descarga.png" /></a>
        </div>
        <h1>Songstock Unimonito</h1>
        <nav id="topNav">
            <ul>
                <li><a href="cancionesServlet?action=listarcanciones">Listar canciones</a></li>
                <li><a href="ViniloServlet?action=listarvinilos">Listar vinilo</a></li>
                <li><a href="PedidoServlet?action=verpedidos">Ver pedidos</a></li>
                <li><a href="PedidoServlet?action=vercompras">Ver compras</a></li>
                <li><a href="pages/vinilo/nuevovinilo.jsp">Registrar vinilo</a></li>
            </ul>
        </nav>
    </body>
</html>