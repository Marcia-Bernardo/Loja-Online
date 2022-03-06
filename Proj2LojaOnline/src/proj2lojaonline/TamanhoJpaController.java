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
import proj2lojaonline.DAL.Tamanho;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class TamanhoJpaController implements Serializable {

    public TamanhoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tamanho tamanho) throws PreexistingEntityException, Exception {
        if (tamanho.getProdutoList() == null) {
            tamanho.setProdutoList(new ArrayList<Produto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Produto> attachedProdutoList = new ArrayList<Produto>();
            for (Produto produtoListProdutoToAttach : tamanho.getProdutoList()) {
                produtoListProdutoToAttach = em.getReference(produtoListProdutoToAttach.getClass(), produtoListProdutoToAttach.getCodprod());
                attachedProdutoList.add(produtoListProdutoToAttach);
            }
            tamanho.setProdutoList(attachedProdutoList);
            em.persist(tamanho);
            for (Produto produtoListProduto : tamanho.getProdutoList()) {
                Tamanho oldCodtamanhoOfProdutoListProduto = produtoListProduto.getCodtamanho();
                produtoListProduto.setCodtamanho(tamanho);
                produtoListProduto = em.merge(produtoListProduto);
                if (oldCodtamanhoOfProdutoListProduto != null) {
                    oldCodtamanhoOfProdutoListProduto.getProdutoList().remove(produtoListProduto);
                    oldCodtamanhoOfProdutoListProduto = em.merge(oldCodtamanhoOfProdutoListProduto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTamanho(tamanho.getCodtam()) != null) {
                throw new PreexistingEntityException("Tamanho " + tamanho + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tamanho tamanho) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tamanho persistentTamanho = em.find(Tamanho.class, tamanho.getCodtam());
            List<Produto> produtoListOld = persistentTamanho.getProdutoList();
            List<Produto> produtoListNew = tamanho.getProdutoList();
            List<String> illegalOrphanMessages = null;
            for (Produto produtoListOldProduto : produtoListOld) {
                if (!produtoListNew.contains(produtoListOldProduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Produto " + produtoListOldProduto + " since its codtamanho field is not nullable.");
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
            tamanho.setProdutoList(produtoListNew);
            tamanho = em.merge(tamanho);
            for (Produto produtoListNewProduto : produtoListNew) {
                if (!produtoListOld.contains(produtoListNewProduto)) {
                    Tamanho oldCodtamanhoOfProdutoListNewProduto = produtoListNewProduto.getCodtamanho();
                    produtoListNewProduto.setCodtamanho(tamanho);
                    produtoListNewProduto = em.merge(produtoListNewProduto);
                    if (oldCodtamanhoOfProdutoListNewProduto != null && !oldCodtamanhoOfProdutoListNewProduto.equals(tamanho)) {
                        oldCodtamanhoOfProdutoListNewProduto.getProdutoList().remove(produtoListNewProduto);
                        oldCodtamanhoOfProdutoListNewProduto = em.merge(oldCodtamanhoOfProdutoListNewProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tamanho.getCodtam();
                if (findTamanho(id) == null) {
                    throw new NonexistentEntityException("The tamanho with id " + id + " no longer exists.");
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
            Tamanho tamanho;
            try {
                tamanho = em.getReference(Tamanho.class, id);
                tamanho.getCodtam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tamanho with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Produto> produtoListOrphanCheck = tamanho.getProdutoList();
            for (Produto produtoListOrphanCheckProduto : produtoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tamanho (" + tamanho + ") cannot be destroyed since the Produto " + produtoListOrphanCheckProduto + " in its produtoList field has a non-nullable codtamanho field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tamanho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tamanho> findTamanhoEntities() {
        return findTamanhoEntities(true, -1, -1);
    }

    public List<Tamanho> findTamanhoEntities(int maxResults, int firstResult) {
        return findTamanhoEntities(false, maxResults, firstResult);
    }

    private List<Tamanho> findTamanhoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tamanho.class));
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

    public Tamanho findTamanho(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tamanho.class, id);
        } finally {
            em.close();
        }
    }

    public int getTamanhoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tamanho> rt = cq.from(Tamanho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
