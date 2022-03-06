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
import proj2lojaonline.DAL.Transportadora;

/**
 *
 * @author marci
 */
public class TransportadoraBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Transportadora transp){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(transp);
        em.getTransaction().commit();
    }
    
     public static Transportadora read(int codTransp){
        Transportadora transp = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Transportadora.findByCodtransp");
        q1.setParameter("codtransp", codTransp);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            transp = ((Transportadora)obj);
        }
        else
            return null;
        
        
        return transp;
    }
     public static List<Transportadora> readAll(){
        List<Transportadora> listaTransp = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Transportadora.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object transp : result){
            listaTransp.add((Transportadora)transp);
        }
        
        return listaTransp;
    }

    public static List<Transportadora> readAll(String nome){
        List<Transportadora> listaTransportadoras = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Transportadora.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object transp : result){
            listaTransportadoras.add((Transportadora)transp);
        }
        
        return listaTransportadoras;
    }
     
    public static void update(Transportadora transp){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(transp);
        em.getTransaction().commit();
    }
    
    public static void delete(Transportadora transp){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(transp);
        em.getTransaction().commit();
    }
    
}
