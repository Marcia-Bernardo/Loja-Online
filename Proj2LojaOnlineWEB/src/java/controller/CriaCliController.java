/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import BLL.ClienteBLL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.Codpostal;

/**
 *
 * @author marci
 */
public class CriaCliController extends AbstractController {
    
    public CriaCliController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
       
                   Cliente cliente = new Cliente();
           cliente.setNome(request.getParameter("nome"));
           cliente.setNif(request.getParameter("nif"));
            cliente.setMorada(request.getParameter("morada"));
            
            Codpostal codpostal=new Codpostal(request.getParameter("cpostal"));
            
            cliente.setCpostal(codpostal);
            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setEmail(request.getParameter("email"));
            
            ClienteBLL.create(cliente);
            
        //throw new UnsupportedOperationException("Not yet implemented");
        ModelAndView mv = new ModelAndView();
            mv.setViewName("dados");
		mv.addObject("listaCli", cliente);
         
         return mv;
    }
    
}
