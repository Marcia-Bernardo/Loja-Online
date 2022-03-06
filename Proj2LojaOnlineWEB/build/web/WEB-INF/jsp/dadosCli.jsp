<%-- 
    Document   : dadosCli
    Created on : 19/06/2021, 18:32:00
    Author     : marci
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados Cliente</title>
        
    </head>
    <body>
        <h1>Dados CLiente!</h1>
      
        
        
  <c:forEach var = "i" begin = "1" end = "${listaCli.size()}">
        <h1>Cliente: ${i}</h1>
        <p>Nome:        ${listaCli[0].nome}</p>
        <p>NIF:         ${listaCli[0].nif}</p>
        <p>Morada:      ${listaCli[0].morada}</p>
        <p>codPostal:   ${listaCli[0].cpostal.cpostal}</p>
        <p>Telefone:    ${listaCli[0].telefone}</p>
        <p>Email:       ${listaCli[0].email}</p>
  </c:forEach>

    </body>
          
</html>
