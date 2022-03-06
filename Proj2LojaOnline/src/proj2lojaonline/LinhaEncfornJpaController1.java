/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import proj2lojaonline.DAL.EncomForn;
import proj2lojaonline.DAL.LinhaEncforn;
import proj2lojaonline.DAL.LinhaEncfornPK;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class LinhaEncfornJpaController1 implements Serializable {

    public LinhaEncfornJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LinhaEncforn linhaEncforn) throws PreexistingEntityException, Exception {
        if (linhaEncforn.getLinhaEncfornPK() == null) {
            linhaEncforn.setLinhaEncfornPK(new LinhaEncfornPK());
        }
        linhaEncforn.getLinhaEncfornPK().setCodencforn(linhaEncforn.getEncomForn().getCodencforn());
        linhaEncforn.getLinhaEncfornPK().setCodprod(linhaEncforn.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncomForn encomForn = linhaEncforn.getEncomForn();
            if (encomForn != null) {
                encomForn = em.getReference(encomForn.getClass(), encomForn.getCodencforn());
                linhaEncforn.setEncomForn(encomForn);
            }
            Produto produto = linhaEncforn.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                linhaEncforn.setProduto(produto);
            }
            em.persist(linhaEncforn);
            if (encomForn != null) {
                encomForn.getLinhaEncfornList().add(linhaEncforn);
                encomForn = em.merge(encomForn);
            }
            if (produto != null) {
                produto.getLinhaEncfornList().add(linhaEncforn);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLinhaEncforn(linhaEncforn.getLinhaEncfornPK()) != null) {
                throw new PreexistingEntityException("LinhaEncforn " + linhaEncforn + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LinhaEncforn linhaEncforn) throws NonexistentEntityException, Exception {
        linhaEncforn.getLinhaEncfornPK().setCodencforn(linhaEncforn.getEncomForn().getCodencforn());
        linhaEncforn.getLinhaEncfornPK().setCodprod(linhaEncforn.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinhaEncforn persistentLinhaEncforn = em.find(LinhaEncforn.class, linhaEncforn.getLinhaEncfornPK());
            EncomForn encomFornOld = persistentLinhaEncforn.getEncomForn();
            EncomForn encomFornNew = linhaEncforn.getEncomForn();
            Produto produtoOld = persistentLinhaEncforn.getProduto();
            Produto produtoNew = linhaEncforn.getProduto();
            if (encomFornNew != null) {
                encomFornNew = em.getReference(encomFornNew.getClass(), encomFornNew.getCodencforn());
                linhaEncforn.setEncomForn(encomFornNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                linhaEncforn.setProduto(produtoNew);
            }
            linhaEncforn = em.merge(linhaEncforn);
            if (encomFornOld != null && !encomFornOld.equals(encomFornNew)) {
                encomFornOld.getLinhaEncfornList().remove(linhaEncforn);
                encomFornOld = em.merge(encomFornOld);
            }
            if (encomFornNew != null && !encomFornNew.equals(encomFornOld)) {
                encomFornNew.getLinhaEncfornList().add(linhaEncforn);
                encomFornNew = em.merge(encomFornNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getLinhaEncfornList().remove(linhaEncforn);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getLinhaEncfornList().add(linhaEncforn);
                produtoNew = em.merge(produtoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                LinhaEncfornPK id = linhaEncforn.getLinhaEncfornPK();
                if (findLinhaEncforn(id) == null) {
                    throw new NonexistentEntityException("The linhaEncforn with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(LinhaEncfornPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinhaEncforn linhaEncforn;
            try {
                linhaEncforn = em.getReference(LinhaEncforn.class, id);
                linhaEncforn.getLinhaEncfornPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The linhaEncforn with id " + id + " no longer exists.", enfe);
            }
            EncomForn encomForn = linhaEncforn.getEncomForn();
            if (encomForn != null) {
                encomForn.getLinhaEncfornList().remove(linhaEncforn);
                encomForn = em.merge(encomForn);
            }
            Produto produto = linhaEncforn.getProduto();
            if (produto != null) {
                produto.getLinhaEncfornList().remove(linhaEncforn);
                produto = em.merge(produto);
            }
            em.remove(linhaEncforn);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LinhaEncforn> findLinhaEncfornEntities() {
        return findLinhaEncfornEntities(true, -1, -1);
    }

    public List<LinhaEncforn> findLinhaEncfornEntities(int maxResults, int firstResult) {
        return findLinhaEncfornEntities(false, maxResults, firstResult);
    }

    private List<LinhaEncforn> findLinhaEncfornEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LinhaEncforn.class));
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

    public LinhaEncforn findLinhaEncforn(LinhaEncfornPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LinhaEncforn.class, id);
        } finally {
            em.close();
        }
    }

    public int getLinhaEncfornCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LinhaEncforn> rt = cq.from(LinhaEncforn.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
