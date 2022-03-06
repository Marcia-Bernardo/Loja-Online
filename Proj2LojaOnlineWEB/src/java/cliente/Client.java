/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import BLL.ClienteBLL;
import BLL.CodpostalBLL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.Codpostal;

/**
 *
 * @author marci
 */
@WebServlet(name = "Client", urlPatterns = {"/Client"})
public class Client extends HttpServlet {

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
       RequestDispatcher req= request.getRequestDispatcher("dados.jps");
            req.forward(request, response);
    }
    
    @Override
          protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String cPostal = request.getParameter("cpostal");
             String userName = request.getParameter("nome");
       String nif = request.getParameter("nif");
    
       String morada = request.getParameter("morada");
       String telefone = request.getParameter("telefone");
       String email = request.getParameter("email"); 
           
     
         
                  
        if (userName.isEmpty() || nif.isEmpty() || morada.isEmpty() || cPostal.isEmpty() ||
                telefone.isEmpty() || email.isEmpty()){
            RequestDispatcher req= request.getRequestDispatcher("/WEB-INF/jsp/dados.jsp");
            req.include(request, response);
        } else {
             List<Codpostal> listaCod = new ArrayList<>(CodpostalBLL.readAll());
           
             for (Codpostal codpostal : listaCod) {
               if(codpostal.cpostal.compareTo(cPostal)==0){
                 Cliente cliente = new Cliente();
           cliente.setNome(userName);
           cliente.setNif(nif);
            cliente.setMorada(morada);       
            
            
            cliente.setCpostal(codpostal);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            
            ClienteBLL.create(cliente);
               
               }
            
        }
            
           request.getRequestDispatcher("/WEB-INF/jsp/registo.jsp").forward(request, response);
            
            }
        }
    
    }

