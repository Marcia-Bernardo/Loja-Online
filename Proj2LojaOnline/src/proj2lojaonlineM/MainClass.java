/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonlineM;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proj2lojaonline.CorJpaController;
import proj2lojaonline.DAL.Cor;

/**
 *
 * @author marci
 */
public class MainClass {private static final String PERSISTENCE_UNIT_NAME = "Proj2LojaOnlinePU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static void main(String[] args) throws Exception{
    
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
//        Cliente ncli = new Cliente();
//        ncli.setNome("Maria Rita Pinheiro ");
//        ncli.setMorada("Praça Gen. Barbosa, 44");
//        ncli.setNif("944376545");
//        ncli.setTelefone("9192323412");
//        ncli.setEmail("carlac@gmail.com");
//        Codpostal cpostal = new Codpostal("4900-347");
//              
//        ncli.setCpostal(cpostal);
   
        Cor cor = new Cor();
        cor.setNome("Vermelho");
        EntityManager em = factory.createEntityManager();
        
        //em.getTransaction().begin();
        //em.persist(cor);
        //em.getTransaction().commit();
        
        //ClienteJpaController cliBll = new ClienteJpaController(factory);
        CorJpaController corBll = new CorJpaController(factory);
        
        //Query q = em.createNamedQuery("Cor.findAll");
        //List<Cor> listaCores = q.getResultList();
        
        corBll.create(cor);
        
        //List<Cliente> lista = cliBll.findClienteEntities();
        List<Cor> listaCores = corBll.findCorEntities();
        
        //for(Cliente cli : lista)
        //    System.out.println(cli.getNome());

        for(Cor cor1 : listaCores)    //corBll.findCorEntities()
            System.out.println(cor1.getNome());

        System.out.println("FIM");
    }
    
    
}
