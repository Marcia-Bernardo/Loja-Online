/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BLL.ClienteBLL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import proj2lojaonline.ClienteJpaController;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.Codpostal;

/**
 *
 * @author marci
 */
public class ListaCliController extends AbstractController {
    
    public ListaCliController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //throw new UnsupportedOperationException("Not yet implemented");
        Cliente cliente = new Cliente();
//            cliente.setNome(request.getParameter("nome"));
//            cliente.setNif(request.getParameter("nif"));
//            cliente.setMorada(request.getParameter("morada"));
//            
//            Codpostal codpostal=new Codpostal("4990-339");
//            
//            cliente.setCpostal(codpostal);
//            cliente.setTelefone(request.getParameter("telefone"));
//            cliente.setEmail(request.getParameter("email"));



 List<Cliente> listaCli = new ArrayList<>(ClienteBLL.readAll());
 
    ModelAndView mv = new ModelAndView();
    
                mv.setViewName("dadosCli");
		mv.addObject("listaCli", listaCli);
         
         return mv;
    }
    
}
