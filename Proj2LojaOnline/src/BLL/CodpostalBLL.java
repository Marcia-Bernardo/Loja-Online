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
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.Cor;

/**
 *
 * @author marci
 */
public class CodpostalBLL {
     private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Codpostal cpost){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(cpost);
        em.getTransaction().commit();
    }
    
     public static Codpostal read(int Codpostal){
        Codpostal cpost = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("CodPostal.findByCodpostal");
        q1.setParameter("codpostal", Codpostal);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            cpost = ((Codpostal)obj);
        }
        else
            return null;
        
        
        return cpost;
    }
     public static List<Codpostal> readAll(){
        List<Codpostal> listaCodpost = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Codpostal.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object cpost : result){
            listaCodpost.add((Codpostal)cpost);
        }
        
        return listaCodpost;
    }

    public static List<Codpostal> readAll(String nome){
        List<Codpostal> listaCodpostais = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Codpostal.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object cpost : result){
            listaCodpostais.add((Codpostal)cpost);
        }
        
        return listaCodpostais;
    }
     
    public static void update(Codpostal cpost){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(cpost);
        em.getTransaction().commit();
    }
    
    public static void delete(Codpostal cpost){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(cpost);
        em.getTransaction().commit();
    }
}
