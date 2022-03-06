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
import proj2lojaonline.DAL.Produto;

/**
 *
 * @author marci
 */
public class ProdutoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Produto prod){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(prod);
        em.getTransaction().commit();
    }
    
     public static Produto read(int codProd){
        Produto prod = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Produto.findByCodprod");
        q1.setParameter("codprod", codProd);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            prod = ((Produto)obj);
        }
        else
            return null;
        
        
        return prod;
    }
    
     public static List<Produto> readAll(){
        List<Produto> listaProd = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Produto.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object prod : result){
            listaProd.add((Produto)prod);
        }
        
        return listaProd;
    }

    public static List<Produto> readAll(String nome){
        List<Produto> listaProdutos = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Produto.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object prod : result){
            listaProdutos.add((Produto)prod);
        }
        
        return listaProdutos;
    }
     
    public static void update(Produto prod){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(prod);
        em.getTransaction().commit();
    }
    
    public static void delete(Produto prod){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(prod);
        em.getTransaction().commit();
    }
}
