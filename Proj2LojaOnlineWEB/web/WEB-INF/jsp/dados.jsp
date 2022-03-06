<%-- 
    Document   : dados
    Created on : 20/06/2021, 12:47:58
    Author     : marci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            
        <div>
            <h1>Criar conta - Cliente</h1>
            <p></p>
            <form action="Client" method="post">
                <table>
                    <tr>
                    <td>Nome:</td><td><input type="text" name="nome"/></td><!-- nome do cliente -->
                    <td>NIF:</td><td><input type="text" name="nif"/></td><!-- NIF -->
                    </tr>
                     <tr>
                        <td>Morada:</td><td><input type="text" name="morada"/></td><!-- morada -->
                        <td>Código Postal:</td><td><input type="text" name="cpostal"/></td><!-- código postal -->
                    </tr>
                    <tr>                        
                        <td>Telefone:</td><td><input type="text" name="telefone"/></td><!-- telefone -->
                        <td>Email:</td><td><input type="text" name="email"/></td><!-- email -->
                    </tr>  
                </table>
                <p></p>
              
                <input type="submit" value="Confirma" />
            </form>
        </div>
    </body>
</html>
