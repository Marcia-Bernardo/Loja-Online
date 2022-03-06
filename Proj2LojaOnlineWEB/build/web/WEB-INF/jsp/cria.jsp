<%-- 
    Document   : cria
    Created on : 19/06/2021, 20:56:02
    Author     : marci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CriaCliente Page</title>
    </head>
    <body>
          
        <div>
          <p>Nome:      ${listaCli[0].nome}</p>
        <p>NIF:         ${listaCli[0].nif}</p>
        <p>Morada:      ${listaCli[0].morada}</p>
        <p>codPostal:   ${listaCli[0].cpostal.cpostal}</p>
        <p>Telefone:    ${listaCli[0].telefone}</p>
        <p>Email:       ${listaCli[0].email}</p>
    </body>
</html>
