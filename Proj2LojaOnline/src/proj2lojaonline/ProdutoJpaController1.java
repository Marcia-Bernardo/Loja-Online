/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import proj2lojaonline.DAL.Cor;
import proj2lojaonline.DAL.Genero;
import proj2lojaonline.DAL.Tamanho;
import proj2lojaonline.DAL.Tipo;
import proj2lojaonline.DAL.LinhaEnccliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.LinhaEncforn;
import proj2lojaonline.DAL.Prodprom;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class ProdutoJpaController1 implements Serializable {

    public ProdutoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) {
        if (produto.getLinhaEncclienteList() == null) {
            produto.setLinhaEncclienteList(new ArrayList<LinhaEnccliente>());
        }
        if (produto.getLinhaEncfornList() == null) {
            produto.setLinhaEncfornList(new ArrayList<LinhaEncforn>());
        }
        if (produto.getProdpromList() == null) {
            produto.setProdpromList(new ArrayList<Prodprom>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cor codcor = produto.getCodcor();
            if (codcor != null) {
                codcor = em.getReference(codcor.getClass(), codcor.getCodcor());
                produto.setCodcor(codcor);
            }
            Genero codgenero = produto.getCodgenero();
            if (codgenero != null) {
                codgenero = em.getReference(codgenero.getClass(), codgenero.getCodgenero());
                produto.setCodgenero(codgenero);
            }
            Tamanho codtamanho = produto.getCodtamanho();
            if (codtamanho != null) {
                codtamanho = em.getReference(codtamanho.getClass(), codtamanho.getCodtam());
                produto.setCodtamanho(codtamanho);
            }
            Tipo codtipo = produto.getCodtipo();
            if (codtipo != null) {
                codtipo = em.getReference(codtipo.getClass(), codtipo.getCodtipo());
                produto.setCodtipo(codtipo);
            }
            List<LinhaEnccliente> attachedLinhaEncclienteList = new ArrayList<LinhaEnccliente>();
            for (LinhaEnccliente linhaEncclienteListLinhaEncclienteToAttach : produto.getLinhaEncclienteList()) {
                linhaEncclienteListLinhaEncclienteToAttach = em.getReference(linhaEncclienteListLinhaEncclienteToAttach.getClass(), linhaEncclienteListLinhaEncclienteToAttach.getLinhaEncclientePK());
                attachedLinhaEncclienteList.add(linhaEncclienteListLinhaEncclienteToAttach);
            }
            produto.setLinhaEncclienteList(attachedLinhaEncclienteList);
            List<LinhaEncforn> attachedLinhaEncfornList = new ArrayList<LinhaEncforn>();
            for (LinhaEncforn linhaEncfornListLinhaEncfornToAttach : produto.getLinhaEncfornList()) {
                linhaEncfornListLinhaEncfornToAttach = em.getReference(linhaEncfornListLinhaEncfornToAttach.getClass(), linhaEncfornListLinhaEncfornToAttach.getLinhaEncfornPK());
                attachedLinhaEncfornList.add(linhaEncfornListLinhaEncfornToAttach);
            }
            produto.setLinhaEncfornList(attachedLinhaEncfornList);
            List<Prodprom> attachedProdpromList = new ArrayList<Prodprom>();
            for (Prodprom prodpromListProdpromToAttach : produto.getProdpromList()) {
                prodpromListProdpromToAttach = em.getReference(prodpromListProdpromToAttach.getClass(), prodpromListProdpromToAttach.getProdpromPK());
                attachedProdpromList.add(prodpromListProdpromToAttach);
            }
            produto.setProdpromList(attachedProdpromList);
            em.persist(produto);
            if (codcor != null) {
                codcor.getProdutoList().add(produto);
                codcor = em.merge(codcor);
            }
            if (codgenero != null) {
                codgenero.getProdutoList().add(produto);
                codgenero = em.merge(codgenero);
            }
            if (codtamanho != null) {
                codtamanho.getProdutoList().add(produto);
                codtamanho = em.merge(codtamanho);
            }
            if (codtipo != null) {
                codtipo.getProdutoList().add(produto);
                codtipo = em.merge(codtipo);
            }
            for (LinhaEnccliente linhaEncclienteListLinhaEnccliente : produto.getLinhaEncclienteList()) {
                Produto oldProdutoOfLinhaEncclienteListLinhaEnccliente = linhaEncclienteListLinhaEnccliente.getProduto();
                linhaEncclienteListLinhaEnccliente.setProduto(produto);
                linhaEncclienteListLinhaEnccliente = em.merge(linhaEncclienteListLinhaEnccliente);
                if (oldProdutoOfLinhaEncclienteListLinhaEnccliente != null) {
                    oldProdutoOfLinhaEncclienteListLinhaEnccliente.getLinhaEncclienteList().remove(linhaEncclienteListLinhaEnccliente);
                    oldProdutoOfLinhaEncclienteListLinhaEnccliente = em.merge(oldProdutoOfLinhaEncclienteListLinhaEnccliente);
                }
            }
            for (LinhaEncforn linhaEncfornListLinhaEncforn : produto.getLinhaEncfornList()) {
                Produto oldProdutoOfLinhaEncfornListLinhaEncforn = linhaEncfornListLinhaEncforn.getProduto();
                linhaEncfornListLinhaEncforn.setProduto(produto);
                linhaEncfornListLinhaEncforn = em.merge(linhaEncfornListLinhaEncforn);
                if (oldProdutoOfLinhaEncfornListLinhaEncforn != null) {
                    oldProdutoOfLinhaEncfornListLinhaEncforn.getLinhaEncfornList().remove(linhaEncfornListLinhaEncforn);
                    oldProdutoOfLinhaEncfornListLinhaEncforn = em.merge(oldProdutoOfLinhaEncfornListLinhaEncforn);
                }
            }
            for (Prodprom prodpromListProdprom : produto.getProdpromList()) {
                Produto oldProdutoOfProdpromListProdprom = prodpromListProdprom.getProduto();
                prodpromListProdprom.setProduto(produto);
                prodpromListProdprom = em.merge(prodpromListProdprom);
                if (oldProdutoOfProdpromListProdprom != null) {
                    oldProdutoOfProdpromListProdprom.getProdpromList().remove(prodpromListProdprom);
                    oldProdutoOfProdpromListProdprom = em.merge(oldProdutoOfProdpromListProdprom);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getCodprod());
            Cor codcorOld = persistentProduto.getCodcor();
            Cor codcorNew = produto.getCodcor();
            Genero codgeneroOld = persistentProduto.getCodgenero();
            Genero codgeneroNew = produto.getCodgenero();
            Tamanho codtamanhoOld = persistentProduto.getCodtamanho();
            Tamanho codtamanhoNew = produto.getCodtamanho();
            Tipo codtipoOld = persistentProduto.getCodtipo();
            Tipo codtipoNew = produto.getCodtipo();
            List<LinhaEnccliente> linhaEncclienteListOld = persistentProduto.getLinhaEncclienteList();
            List<LinhaEnccliente> linhaEncclienteListNew = produto.getLinhaEncclienteList();
            List<LinhaEncforn> linhaEncfornListOld = persistentProduto.getLinhaEncfornList();
            List<LinhaEncforn> linhaEncfornListNew = produto.getLinhaEncfornList();
            List<Prodprom> prodpromListOld = persistentProduto.getProdpromList();
            List<Prodprom> prodpromListNew = produto.getProdpromList();
            List<String> illegalOrphanMessages = null;
            for (LinhaEnccliente linhaEncclienteListOldLinhaEnccliente : linhaEncclienteListOld) {
                if (!linhaEncclienteListNew.contains(linhaEncclienteListOldLinhaEnccliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LinhaEnccliente " + linhaEncclienteListOldLinhaEnccliente + " since its produto field is not nullable.");
                }
            }
            for (LinhaEncforn linhaEncfornListOldLinhaEncforn : linhaEncfornListOld) {
                if (!linhaEncfornListNew.contains(linhaEncfornListOldLinhaEncforn)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LinhaEncforn " + linhaEncfornListOldLinhaEncforn + " since its produto field is not nullable.");
                }
            }
            for (Prodprom prodpromListOldProdprom : prodpromListOld) {
                if (!prodpromListNew.contains(prodpromListOldProdprom)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prodprom " + prodpromListOldProdprom + " since its produto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codcorNew != null) {
                codcorNew = em.getReference(codcorNew.getClass(), codcorNew.getCodcor());
                produto.setCodcor(codcorNew);
            }
            if (codgeneroNew != null) {
                codgeneroNew = em.getReference(codgeneroNew.getClass(), codgeneroNew.getCodgenero());
                produto.setCodgenero(codgeneroNew);
            }
            if (codtamanhoNew != null) {
                codtamanhoNew = em.getReference(codtamanhoNew.getClass(), codtamanhoNew.getCodtam());
                produto.setCodtamanho(codtamanhoNew);
            }
            if (codtipoNew != null) {
                codtipoNew = em.getReference(codtipoNew.getClass(), codtipoNew.getCodtipo());
                produto.setCodtipo(codtipoNew);
            }
            List<LinhaEnccliente> attachedLinhaEncclienteListNew = new ArrayList<LinhaEnccliente>();
            for (LinhaEnccliente linhaEncclienteListNewLinhaEncclienteToAttach : linhaEncclienteListNew) {
                linhaEncclienteListNewLinhaEncclienteToAttach = em.getReference(linhaEncclienteListNewLinhaEncclienteToAttach.getClass(), linhaEncclienteListNewLinhaEncclienteToAttach.getLinhaEncclientePK());
                attachedLinhaEncclienteListNew.add(linhaEncclienteListNewLinhaEncclienteToAttach);
            }
            linhaEncclienteListNew = attachedLinhaEncclienteListNew;
            produto.setLinhaEncclienteList(linhaEncclienteListNew);
            List<LinhaEncforn> attachedLinhaEncfornListNew = new ArrayList<LinhaEncforn>();
            for (LinhaEncforn linhaEncfornListNewLinhaEncfornToAttach : linhaEncfornListNew) {
                linhaEncfornListNewLinhaEncfornToAttach = em.getReference(linhaEncfornListNewLinhaEncfornToAttach.getClass(), linhaEncfornListNewLinhaEncfornToAttach.getLinhaEncfornPK());
                attachedLinhaEncfornListNew.add(linhaEncfornListNewLinhaEncfornToAttach);
            }
            linhaEncfornListNew = attachedLinhaEncfornListNew;
            produto.setLinhaEncfornList(linhaEncfornListNew);
            List<Prodprom> attachedProdpromListNew = new ArrayList<Prodprom>();
            for (Prodprom prodpromListNewProdpromToAttach : prodpromListNew) {
                prodpromListNewProdpromToAttach = em.getReference(prodpromListNewProdpromToAttach.getClass(), prodpromListNewProdpromToAttach.getProdpromPK());
                attachedProdpromListNew.add(prodpromListNewProdpromToAttach);
            }
            prodpromListNew = attachedProdpromListNew;
            produto.setProdpromList(prodpromListNew);
            produto = em.merge(produto);
            if (codcorOld != null && !codcorOld.equals(codcorNew)) {
                codcorOld.getProdutoList().remove(produto);
                codcorOld = em.merge(codcorOld);
            }
            if (codcorNew != null && !codcorNew.equals(codcorOld)) {
                codcorNew.getProdutoList().add(produto);
                codcorNew = em.merge(codcorNew);
            }
            if (codgeneroOld != null && !codgeneroOld.equals(codgeneroNew)) {
                codgeneroOld.getProdutoList().remove(produto);
                codgeneroOld = em.merge(codgeneroOld);
            }
            if (codgeneroNew != null && !codgeneroNew.equals(codgeneroOld)) {
                codgeneroNew.getProdutoList().add(produto);
                codgeneroNew = em.merge(codgeneroNew);
            }
            if (codtamanhoOld != null && !codtamanhoOld.equals(codtamanhoNew)) {
                codtamanhoOld.getProdutoList().remove(produto);
                codtamanhoOld = em.merge(codtamanhoOld);
            }
            if (codtamanhoNew != null && !codtamanhoNew.equals(codtamanhoOld)) {
                codtamanhoNew.getProdutoList().add(produto);
                codtamanhoNew = em.merge(codtamanhoNew);
            }
            if (codtipoOld != null && !codtipoOld.equals(codtipoNew)) {
                codtipoOld.getProdutoList().remove(produto);
                codtipoOld = em.merge(codtipoOld);
            }
            if (codtipoNew != null && !codtipoNew.equals(codtipoOld)) {
                codtipoNew.getProdutoList().add(produto);
                codtipoNew = em.merge(codtipoNew);
            }
            for (LinhaEnccliente linhaEncclienteListNewLinhaEnccliente : linhaEncclienteListNew) {
                if (!linhaEncclienteListOld.contains(linhaEncclienteListNewLinhaEnccliente)) {
                    Produto oldProdutoOfLinhaEncclienteListNewLinhaEnccliente = linhaEncclienteListNewLinhaEnccliente.getProduto();
                    linhaEncclienteListNewLinhaEnccliente.setProduto(produto);
                    linhaEncclienteListNewLinhaEnccliente = em.merge(linhaEncclienteListNewLinhaEnccliente);
                    if (oldProdutoOfLinhaEncclienteListNewLinhaEnccliente != null && !oldProdutoOfLinhaEncclienteListNewLinhaEnccliente.equals(produto)) {
                        oldProdutoOfLinhaEncclienteListNewLinhaEnccliente.getLinhaEncclienteList().remove(linhaEncclienteListNewLinhaEnccliente);
                        oldProdutoOfLinhaEncclienteListNewLinhaEnccliente = em.merge(oldProdutoOfLinhaEncclienteListNewLinhaEnccliente);
                    }
                }
            }
            for (LinhaEncforn linhaEncfornListNewLinhaEncforn : linhaEncfornListNew) {
                if (!linhaEncfornListOld.contains(linhaEncfornListNewLinhaEncforn)) {
                    Produto oldProdutoOfLinhaEncfornListNewLinhaEncforn = linhaEncfornListNewLinhaEncforn.getProduto();
                    linhaEncfornListNewLinhaEncforn.setProduto(produto);
                    linhaEncfornListNewLinhaEncforn = em.merge(linhaEncfornListNewLinhaEncforn);
                    if (oldProdutoOfLinhaEncfornListNewLinhaEncforn != null && !oldProdutoOfLinhaEncfornListNewLinhaEncforn.equals(produto)) {
                        oldProdutoOfLinhaEncfornListNewLinhaEncforn.getLinhaEncfornList().remove(linhaEncfornListNewLinhaEncforn);
                        oldProdutoOfLinhaEncfornListNewLinhaEncforn = em.merge(oldProdutoOfLinhaEncfornListNewLinhaEncforn);
                    }
                }
            }
            for (Prodprom prodpromListNewProdprom : prodpromListNew) {
                if (!prodpromListOld.contains(prodpromListNewProdprom)) {
                    Produto oldProdutoOfProdpromListNewProdprom = prodpromListNewProdprom.getProduto();
                    prodpromListNewProdprom.setProduto(produto);
                    prodpromListNewProdprom = em.merge(prodpromListNewProdprom);
                    if (oldProdutoOfProdpromListNewProdprom != null && !oldProdutoOfProdpromListNewProdprom.equals(produto)) {
                        oldProdutoOfProdpromListNewProdprom.getProdpromList().remove(prodpromListNewProdprom);
                        oldProdutoOfProdpromListNewProdprom = em.merge(oldProdutoOfProdpromListNewProdprom);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getCodprod();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getCodprod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<LinhaEnccliente> linhaEncclienteListOrphanCheck = produto.getLinhaEncclienteList();
            for (LinhaEnccliente linhaEncclienteListOrphanCheckLinhaEnccliente : linhaEncclienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the LinhaEnccliente " + linhaEncclienteListOrphanCheckLinhaEnccliente + " in its linhaEncclienteList field has a non-nullable produto field.");
            }
            List<LinhaEncforn> linhaEncfornListOrphanCheck = produto.getLinhaEncfornList();
            for (LinhaEncforn linhaEncfornListOrphanCheckLinhaEncforn : linhaEncfornListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the LinhaEncforn " + linhaEncfornListOrphanCheckLinhaEncforn + " in its linhaEncfornList field has a non-nullable produto field.");
            }
            List<Prodprom> prodpromListOrphanCheck = produto.getProdpromList();
            for (Prodprom prodpromListOrphanCheckProdprom : prodpromListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the Prodprom " + prodpromListOrphanCheckProdprom + " in its prodpromList field has a non-nullable produto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cor codcor = produto.getCodcor();
            if (codcor != null) {
                codcor.getProdutoList().remove(produto);
                codcor = em.merge(codcor);
            }
            Genero codgenero = produto.getCodgenero();
            if (codgenero != null) {
                codgenero.getProdutoList().remove(produto);
                codgenero = em.merge(codgenero);
            }
            Tamanho codtamanho = produto.getCodtamanho();
            if (codtamanho != null) {
                codtamanho.getProdutoList().remove(produto);
                codtamanho = em.merge(codtamanho);
            }
            Tipo codtipo = produto.getCodtipo();
            if (codtipo != null) {
                codtipo.getProdutoList().remove(produto);
                codtipo = em.merge(codtipo);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
