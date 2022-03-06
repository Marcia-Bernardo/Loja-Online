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
import proj2lojaonline.DAL.EncomCliente;
import proj2lojaonline.DAL.LinhaEnccliente;
import proj2lojaonline.DAL.LinhaEncclientePK;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class LinhaEncclienteJpaController1 implements Serializable {

    public LinhaEncclienteJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LinhaEnccliente linhaEnccliente) throws PreexistingEntityException, Exception {
        if (linhaEnccliente.getLinhaEncclientePK() == null) {
            linhaEnccliente.setLinhaEncclientePK(new LinhaEncclientePK());
        }
        linhaEnccliente.getLinhaEncclientePK().setCodprod(linhaEnccliente.getProduto().getCodprod());
        linhaEnccliente.getLinhaEncclientePK().setCodenccliente(linhaEnccliente.getEncomCliente().getCodenccliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncomCliente encomCliente = linhaEnccliente.getEncomCliente();
            if (encomCliente != null) {
                encomCliente = em.getReference(encomCliente.getClass(), encomCliente.getCodenccliente());
                linhaEnccliente.setEncomCliente(encomCliente);
            }
            Produto produto = linhaEnccliente.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                linhaEnccliente.setProduto(produto);
            }
            em.persist(linhaEnccliente);
            if (encomCliente != null) {
                encomCliente.getLinhaEncclienteList().add(linhaEnccliente);
                encomCliente = em.merge(encomCliente);
            }
            if (produto != null) {
                produto.getLinhaEncclienteList().add(linhaEnccliente);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLinhaEnccliente(linhaEnccliente.getLinhaEncclientePK()) != null) {
                throw new PreexistingEntityException("LinhaEnccliente " + linhaEnccliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LinhaEnccliente linhaEnccliente) throws NonexistentEntityException, Exception {
        linhaEnccliente.getLinhaEncclientePK().setCodprod(linhaEnccliente.getProduto().getCodprod());
        linhaEnccliente.getLinhaEncclientePK().setCodenccliente(linhaEnccliente.getEncomCliente().getCodenccliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinhaEnccliente persistentLinhaEnccliente = em.find(LinhaEnccliente.class, linhaEnccliente.getLinhaEncclientePK());
            EncomCliente encomClienteOld = persistentLinhaEnccliente.getEncomCliente();
            EncomCliente encomClienteNew = linhaEnccliente.getEncomCliente();
            Produto produtoOld = persistentLinhaEnccliente.getProduto();
            Produto produtoNew = linhaEnccliente.getProduto();
            if (encomClienteNew != null) {
                encomClienteNew = em.getReference(encomClienteNew.getClass(), encomClienteNew.getCodenccliente());
                linhaEnccliente.setEncomCliente(encomClienteNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                linhaEnccliente.setProduto(produtoNew);
            }
            linhaEnccliente = em.merge(linhaEnccliente);
            if (encomClienteOld != null && !encomClienteOld.equals(encomClienteNew)) {
                encomClienteOld.getLinhaEncclienteList().remove(linhaEnccliente);
                encomClienteOld = em.merge(encomClienteOld);
            }
            if (encomClienteNew != null && !encomClienteNew.equals(encomClienteOld)) {
                encomClienteNew.getLinhaEncclienteList().add(linhaEnccliente);
                encomClienteNew = em.merge(encomClienteNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getLinhaEncclienteList().remove(linhaEnccliente);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getLinhaEncclienteList().add(linhaEnccliente);
                produtoNew = em.merge(produtoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                LinhaEncclientePK id = linhaEnccliente.getLinhaEncclientePK();
                if (findLinhaEnccliente(id) == null) {
                    throw new NonexistentEntityException("The linhaEnccliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(LinhaEncclientePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinhaEnccliente linhaEnccliente;
            try {
                linhaEnccliente = em.getReference(LinhaEnccliente.class, id);
                linhaEnccliente.getLinhaEncclientePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The linhaEnccliente with id " + id + " no longer exists.", enfe);
            }
            EncomCliente encomCliente = linhaEnccliente.getEncomCliente();
            if (encomCliente != null) {
                encomCliente.getLinhaEncclienteList().remove(linhaEnccliente);
                encomCliente = em.merge(encomCliente);
            }
            Produto produto = linhaEnccliente.getProduto();
            if (produto != null) {
                produto.getLinhaEncclienteList().remove(linhaEnccliente);
                produto = em.merge(produto);
            }
            em.remove(linhaEnccliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LinhaEnccliente> findLinhaEncclienteEntities() {
        return findLinhaEncclienteEntities(true, -1, -1);
    }

    public List<LinhaEnccliente> findLinhaEncclienteEntities(int maxResults, int firstResult) {
        return findLinhaEncclienteEntities(false, maxResults, firstResult);
    }

    private List<LinhaEnccliente> findLinhaEncclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LinhaEnccliente.class));
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

    public LinhaEnccliente findLinhaEnccliente(LinhaEncclientePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LinhaEnccliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getLinhaEncclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LinhaEnccliente> rt = cq.from(LinhaEnccliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
