/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import proj2lojaonline.DAL.Cliente;

/**
 *
 * @author marci
 */
public class ClienteBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Cliente cli){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(cli);
        em.getTransaction().commit();
    }
    
     public static Cliente read(int codCliente){
        Cliente cli = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cliente.findByCodcliente");
        q1.setParameter("codcliente", codCliente);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            cli = ((Cliente)obj);
        }
        else
            return null;
        
        
        return cli;
    }
     public static List<Cliente> readAll(){
        List<Cliente> listaCli = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cliente.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object cli : result){
            listaCli.add((Cliente)cli);
        }
        
        return listaCli;
    }

    public static List<Cliente> readAll(String nome){
        List<Cliente> listaClientes = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cliente.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object cli : result){
            listaClientes.add((Cliente)cli);
        }
        
        return listaClientes;
    }
     
    public static void update(Cliente cli){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(cli);
        em.getTransaction().commit();
    }
    
    public static void delete(Cliente cli) throws Exception{
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(cli);
        em.getTransaction().commit();
    }
}
