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
import proj2lojaonline.DAL.Fornecedor;

/**
 *
 * @author marci
 */
public class FornecedorBLL {
    private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Fornecedor forn){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(forn);
        em.getTransaction().commit();
    }
    
     public static Fornecedor read(int codForn){
        Fornecedor forn = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Fornecedor.findByCodforn");
        q1.setParameter("codforn", codForn);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            forn = ((Fornecedor)obj);
        }
        else
            return null;
        
        
        return forn;
    }
     public static List<Fornecedor> readAll(){
        List<Fornecedor> listaForn = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Fornecedor.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object forn : result){
            listaForn.add((Fornecedor)forn);
        }
        
        return listaForn;
    }

    public static List<Fornecedor> readAll(String nome){
        List<Fornecedor> listaFornecedores = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Fornecedor.findAllByNome");
        q1.setParameter("nome", "%"+nome+"%");
        List<Object> result = q1.getResultList();
        
        for(Object forn : result){
            listaFornecedores.add((Fornecedor)forn);
        }
        
        return listaFornecedores;
    }
     
    public static void update(Fornecedor forn){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(forn);
        em.getTransaction().commit();
    }
    
    public static void delete(Fornecedor forn){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(forn);
        em.getTransaction().commit();
    }
}
