/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import proj2lojaonline.DAL.Cor;

/**
 *
 * @author marci
 */
public class CorBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Cor cor1){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(cor1);
        em.getTransaction().commit();
    }
    
     public static Cor read(int codCor){
        Cor cor1 = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cor.findByCodcor");
        q1.setParameter("codcor", codCor);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            cor1 = ((Cor)obj);
        }
        else
            return null;
        
        
        return cor1;
    }
     public static List<Cor> readAll(){
        List<Cor> listaCor1 = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cor.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object cor1 : result){
            listaCor1.add((Cor)cor1);
        }
        
        return listaCor1;
    }

    public static List<Cor> readAll(String nome){
        List<Cor> listaCores = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Cor.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object cor1 : result){
            listaCores.add((Cor)cor1);
        }
        
        return listaCores;
    }
     
    public static void update(Cor cor1){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(cor1);
        em.getTransaction().commit();
    }
    
    public static void delete(Cor cor1) throws Exception{
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(cor1);
        em.getTransaction().commit();
    }
}
