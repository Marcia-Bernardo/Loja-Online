<%-- 
    Document   : cliente
    Created on : 19/06/2021, 20:55:10
    Author     : marci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Form</title>
    </head>
    <body>
        <jsp:useBean id="user" scope="session" class="cliente.Cliente" />
        <jsp:setProperty name="user" property="*"/>
        <h1> Ola, <jsp:getProperty name="user" property="nome"/>!</h1>
    </body>
</html>
