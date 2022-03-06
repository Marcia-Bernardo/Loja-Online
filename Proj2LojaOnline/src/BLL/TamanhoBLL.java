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
import proj2lojaonline.DAL.Tamanho;

/**
 *
 * @author marci
 */
public class TamanhoBLL {
     private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Tamanho tam){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(tam);
        em.getTransaction().commit();
    }
    
     public static Tamanho read(int codTam){
       Tamanho tam = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tamanho.findByCodtam");
        q1.setParameter("codtam", codTam);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            tam = ((Tamanho)obj);
        }
        else
            return null;
        
        
        return tam;
    }
     public static List<Tamanho> readAll(){
        List<Tamanho> listaTamanho= new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tamanho.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object tam : result){
            listaTamanho.add((Tamanho)tam);
        }
        
        return listaTamanho;
    }

    public static List<Tamanho> readAll(String nome){
        List<Tamanho> listaTamanhos = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tamanho.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object tam : result){
            listaTamanhos.add((Tamanho)tam);
        }
        
        return listaTamanhos;
    }
     
    public static void update(Tamanho tam){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(tam);
        em.getTransaction().commit();
    }
    
    public static void delete(Tamanho tam){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(tam);
        em.getTransaction().commit();
    }
}
