<%-- 
    Document   : nome
    Created on : 20/06/2021, 06:20:07
    Author     : marci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Input form</title>
    </head>
    <body>
        <h1>Hello,</h1>
        <form action="cliente.jsp" method="post">
            Enter your name: <input type="text" name="nome"/>
            <input type="submit"/>
        </form>
    </body>
</html>
