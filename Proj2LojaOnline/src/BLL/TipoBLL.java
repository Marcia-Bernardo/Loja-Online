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
import proj2lojaonline.DAL.Tipo;


/**
 *
 * @author marci
 */
public class TipoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Tipo tipo1){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(tipo1);
        em.getTransaction().commit();
    }
    
     public static Tipo read(int codTipo){
       Tipo tipo1 = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tipo.findByCodtipo");
        q1.setParameter("codtipo", codTipo);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            tipo1 = ((Tipo)obj);
        }
        else
            return null;
        
        
        return tipo1;
    }
     public static List<Tipo> readAll(){
        List<Tipo> listaTipo= new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tipo.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object tipo1 : result){
            listaTipo.add((Tipo)tipo1);
        }
        
        return listaTipo;
    }

    public static List<Tipo> readAll(String nome){
        List<Tipo> listaTipos = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Tipo.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object tipo1 : result){
            listaTipos.add((Tipo)tipo1);
        }
        
        return listaTipos;
    }
     
    public static void update(Tipo tipo1){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(tipo1);
        em.getTransaction().commit();
    }
    
    public static void delete(Tipo tipo1){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(tipo1);
        em.getTransaction().commit();
    }
}
