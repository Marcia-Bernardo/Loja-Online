/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marci
 */
@WebServlet(name = "Dados", urlPatterns = {"/Dados"})
public class Dados extends HttpServlet {

        private static final long serialVersionUID =1L;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher req= request.getRequestDispatcher("dados.jsp");
            req.forward(request, response);
    }
    
    @Override
          protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
       String userName = request.getParameter("userName");
       String nif = request.getParameter("nif");
       String cPostal = request.getParameter("cPostal");
       String morada = request.getParameter("morada");
       String telefone = request.getParameter("telefone");
       String email = request.getParameter("email");           
            
        if (userName.isEmpty() || nif.isEmpty() || morada.isEmpty() || cPostal.isEmpty() ||
                telefone.isEmpty() || email.isEmpty()){
            RequestDispatcher req= request.getRequestDispatcher("dados");
            req.include(request, response);
        } else {
            RequestDispatcher req= request.getRequestDispatcher("registo");
            req.include(request, response);
            
            }
        }
    
    }

