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
import proj2lojaonline.DAL.Prodprom;
import proj2lojaonline.DAL.ProdpromPK;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.DAL.Promocao;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class ProdpromJpaController implements Serializable {

    public ProdpromJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prodprom prodprom) throws PreexistingEntityException, Exception {
        if (prodprom.getProdpromPK() == null) {
            prodprom.setProdpromPK(new ProdpromPK());
        }
        prodprom.getProdpromPK().setCodpromocao(prodprom.getPromocao().getCodpromocao());
        prodprom.getProdpromPK().setCodprod(prodprom.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto = prodprom.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                prodprom.setProduto(produto);
            }
            Promocao promocao = prodprom.getPromocao();
            if (promocao != null) {
                promocao = em.getReference(promocao.getClass(), promocao.getCodpromocao());
                prodprom.setPromocao(promocao);
            }
            em.persist(prodprom);
            if (produto != null) {
                produto.getProdpromList().add(prodprom);
                produto = em.merge(produto);
            }
            if (promocao != null) {
                promocao.getProdpromList().add(prodprom);
                promocao = em.merge(promocao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProdprom(prodprom.getProdpromPK()) != null) {
                throw new PreexistingEntityException("Prodprom " + prodprom + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prodprom prodprom) throws NonexistentEntityException, Exception {
        prodprom.getProdpromPK().setCodpromocao(prodprom.getPromocao().getCodpromocao());
        prodprom.getProdpromPK().setCodprod(prodprom.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prodprom persistentProdprom = em.find(Prodprom.class, prodprom.getProdpromPK());
            Produto produtoOld = persistentProdprom.getProduto();
            Produto produtoNew = prodprom.getProduto();
            Promocao promocaoOld = persistentProdprom.getPromocao();
            Promocao promocaoNew = prodprom.getPromocao();
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                prodprom.setProduto(produtoNew);
            }
            if (promocaoNew != null) {
                promocaoNew = em.getReference(promocaoNew.getClass(), promocaoNew.getCodpromocao());
                prodprom.setPromocao(promocaoNew);
            }
            prodprom = em.merge(prodprom);
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getProdpromList().remove(prodprom);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getProdpromList().add(prodprom);
                produtoNew = em.merge(produtoNew);
            }
            if (promocaoOld != null && !promocaoOld.equals(promocaoNew)) {
                promocaoOld.getProdpromList().remove(prodprom);
                promocaoOld = em.merge(promocaoOld);
            }
            if (promocaoNew != null && !promocaoNew.equals(promocaoOld)) {
                promocaoNew.getProdpromList().add(prodprom);
                promocaoNew = em.merge(promocaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProdpromPK id = prodprom.getProdpromPK();
                if (findProdprom(id) == null) {
                    throw new NonexistentEntityException("The prodprom with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProdpromPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prodprom prodprom;
            try {
                prodprom = em.getReference(Prodprom.class, id);
                prodprom.getProdpromPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prodprom with id " + id + " no longer exists.", enfe);
            }
            Produto produto = prodprom.getProduto();
            if (produto != null) {
                produto.getProdpromList().remove(prodprom);
                produto = em.merge(produto);
            }
            Promocao promocao = prodprom.getPromocao();
            if (promocao != null) {
                promocao.getProdpromList().remove(prodprom);
                promocao = em.merge(promocao);
            }
            em.remove(prodprom);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prodprom> findProdpromEntities() {
        return findProdpromEntities(true, -1, -1);
    }

    public List<Prodprom> findProdpromEntities(int maxResults, int firstResult) {
        return findProdpromEntities(false, maxResults, firstResult);
    }

    private List<Prodprom> findProdpromEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prodprom.class));
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

    public Prodprom findProdprom(ProdpromPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prodprom.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdpromCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prodprom> rt = cq.from(Prodprom.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
