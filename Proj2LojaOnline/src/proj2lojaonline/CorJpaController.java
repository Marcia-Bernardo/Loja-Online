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
import proj2lojaonline.DAL.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.Cor;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class CorJpaController implements Serializable {

    public CorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cor cor) throws PreexistingEntityException, Exception {
        if (cor.getProdutoList() == null) {
            cor.setProdutoList(new ArrayList<Produto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Produto> attachedProdutoList = new ArrayList<Produto>();
            for (Produto produtoListProdutoToAttach : cor.getProdutoList()) {
                produtoListProdutoToAttach = em.getReference(produtoListProdutoToAttach.getClass(), produtoListProdutoToAttach.getCodprod());
                attachedProdutoList.add(produtoListProdutoToAttach);
            }
            cor.setProdutoList(attachedProdutoList);
            em.persist(cor);
            for (Produto produtoListProduto : cor.getProdutoList()) {
                Cor oldCodcorOfProdutoListProduto = produtoListProduto.getCodcor();
                produtoListProduto.setCodcor(cor);
                produtoListProduto = em.merge(produtoListProduto);
                if (oldCodcorOfProdutoListProduto != null) {
                    oldCodcorOfProdutoListProduto.getProdutoList().remove(produtoListProduto);
                    oldCodcorOfProdutoListProduto = em.merge(oldCodcorOfProdutoListProduto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCor(cor.getCodcor()) != null) {
                throw new PreexistingEntityException("Cor " + cor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cor cor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cor persistentCor = em.find(Cor.class, cor.getCodcor());
            List<Produto> produtoListOld = persistentCor.getProdutoList();
            List<Produto> produtoListNew = cor.getProdutoList();
            List<String> illegalOrphanMessages = null;
            for (Produto produtoListOldProduto : produtoListOld) {
                if (!produtoListNew.contains(produtoListOldProduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Produto " + produtoListOldProduto + " since its codcor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Produto> attachedProdutoListNew = new ArrayList<Produto>();
            for (Produto produtoListNewProdutoToAttach : produtoListNew) {
                produtoListNewProdutoToAttach = em.getReference(produtoListNewProdutoToAttach.getClass(), produtoListNewProdutoToAttach.getCodprod());
                attachedProdutoListNew.add(produtoListNewProdutoToAttach);
            }
            produtoListNew = attachedProdutoListNew;
            cor.setProdutoList(produtoListNew);
            cor = em.merge(cor);
            for (Produto produtoListNewProduto : produtoListNew) {
                if (!produtoListOld.contains(produtoListNewProduto)) {
                    Cor oldCodcorOfProdutoListNewProduto = produtoListNewProduto.getCodcor();
                    produtoListNewProduto.setCodcor(cor);
                    produtoListNewProduto = em.merge(produtoListNewProduto);
                    if (oldCodcorOfProdutoListNewProduto != null && !oldCodcorOfProdutoListNewProduto.equals(cor)) {
                        oldCodcorOfProdutoListNewProduto.getProdutoList().remove(produtoListNewProduto);
                        oldCodcorOfProdutoListNewProduto = em.merge(oldCodcorOfProdutoListNewProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cor.getCodcor();
                if (findCor(id) == null) {
                    throw new NonexistentEntityException("The cor with id " + id + " no longer exists.");
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
            Cor cor;
            try {
                cor = em.getReference(Cor.class, id);
                cor.getCodcor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Produto> produtoListOrphanCheck = cor.getProdutoList();
            for (Produto produtoListOrphanCheckProduto : produtoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cor (" + cor + ") cannot be destroyed since the Produto " + produtoListOrphanCheckProduto + " in its produtoList field has a non-nullable codcor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cor> findCorEntities() {
        return findCorEntities(true, -1, -1);
    }

    public List<Cor> findCorEntities(int maxResults, int firstResult) {
        return findCorEntities(false, maxResults, firstResult);
    }

    private List<Cor> findCorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cor.class));
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

    public Cor findCor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cor> rt = cq.from(Cor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
