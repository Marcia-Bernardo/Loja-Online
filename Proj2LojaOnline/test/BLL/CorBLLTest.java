/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import proj2lojaonline.DAL.Cor;

/**
 *
 * @author marci
 */
public class CorBLLTest {
    
    public CorBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class CorBLL.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Cor cor1 = new Cor();
        cor1.setNome("Verde");
        CorBLL.create(cor1);
      
    }

     /**
     * Test of readAll method, of class CorBLL.
     */
    @Test
    public void testReadAll_0args() {
        System.out.println("readAll");
        List<Cor> listaCores = null;
        listaCores = CorBLL.readAll();
        List<Cor> expResult = listaCores;
        List<Cor> result = CorBLL.readAll();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of update method, of class CorBLL.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        List<Cor> listaCor = null;
        listaCor = CorBLL.readAll();
        
        for(Cor cor1 : listaCor){
        CorBLL.update(cor1);
        }
        
    }

//    /**
//     * Test of delete method, of class CorBLL.
//     */
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        Cor cor1 = null;
//        CorBLL.delete(cor1);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
