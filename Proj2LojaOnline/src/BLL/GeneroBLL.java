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
import proj2lojaonline.DAL.Genero;

/**
 *
 * @author marci
 */
public class GeneroBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
    }
    
     public static Genero read(int codGenero){
       Genero genero = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findByCodgenero");
        q1.setParameter("codgenero", codGenero);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            genero = ((Genero)obj);
        }
        else
            return null;
        
        
        return genero;
    }
     public static List<Genero> readAll(){
        List<Genero> listaGenero= new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object genero : result){
            listaGenero.add((Genero)genero);
        }
        
        return listaGenero;
    }

    public static List<Genero> readAll(String nome){
        List<Genero> listaGeneros = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object genero : result){
            listaGeneros.add((Genero)genero);
        }
        
        return listaGeneros;
    }
     
    public static void update(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(genero);
        em.getTransaction().commit();
    }
    
    public static void delete(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(genero);
        em.getTransaction().commit();
    }
}
