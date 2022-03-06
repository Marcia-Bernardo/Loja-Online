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
import proj2lojaonline.DAL.Prodprom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.Promocao;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class PromocaoJpaController1 implements Serializable {

    public PromocaoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Promocao promocao) {
        if (promocao.getProdpromList() == null) {
            promocao.setProdpromList(new ArrayList<Prodprom>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prodprom> attachedProdpromList = new ArrayList<Prodprom>();
            for (Prodprom prodpromListProdpromToAttach : promocao.getProdpromList()) {
                prodpromListProdpromToAttach = em.getReference(prodpromListProdpromToAttach.getClass(), prodpromListProdpromToAttach.getProdpromPK());
                attachedProdpromList.add(prodpromListProdpromToAttach);
            }
            promocao.setProdpromList(attachedProdpromList);
            em.persist(promocao);
            for (Prodprom prodpromListProdprom : promocao.getProdpromList()) {
                Promocao oldPromocaoOfProdpromListProdprom = prodpromListProdprom.getPromocao();
                prodpromListProdprom.setPromocao(promocao);
                prodpromListProdprom = em.merge(prodpromListProdprom);
                if (oldPromocaoOfProdpromListProdprom != null) {
                    oldPromocaoOfProdpromListProdprom.getProdpromList().remove(prodpromListProdprom);
                    oldPromocaoOfProdpromListProdprom = em.merge(oldPromocaoOfProdpromListProdprom);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Promocao promocao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Promocao persistentPromocao = em.find(Promocao.class, promocao.getCodpromocao());
            List<Prodprom> prodpromListOld = persistentPromocao.getProdpromList();
            List<Prodprom> prodpromListNew = promocao.getProdpromList();
            List<String> illegalOrphanMessages = null;
            for (Prodprom prodpromListOldProdprom : prodpromListOld) {
                if (!prodpromListNew.contains(prodpromListOldProdprom)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prodprom " + prodpromListOldProdprom + " since its promocao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prodprom> attachedProdpromListNew = new ArrayList<Prodprom>();
            for (Prodprom prodpromListNewProdpromToAttach : prodpromListNew) {
                prodpromListNewProdpromToAttach = em.getReference(prodpromListNewProdpromToAttach.getClass(), prodpromListNewProdpromToAttach.getProdpromPK());
                attachedProdpromListNew.add(prodpromListNewProdpromToAttach);
            }
            prodpromListNew = attachedProdpromListNew;
            promocao.setProdpromList(prodpromListNew);
            promocao = em.merge(promocao);
            for (Prodprom prodpromListNewProdprom : prodpromListNew) {
                if (!prodpromListOld.contains(prodpromListNewProdprom)) {
                    Promocao oldPromocaoOfProdpromListNewProdprom = prodpromListNewProdprom.getPromocao();
                    prodpromListNewProdprom.setPromocao(promocao);
                    prodpromListNewProdprom = em.merge(prodpromListNewProdprom);
                    if (oldPromocaoOfProdpromListNewProdprom != null && !oldPromocaoOfProdpromListNewProdprom.equals(promocao)) {
                        oldPromocaoOfProdpromListNewProdprom.getProdpromList().remove(prodpromListNewProdprom);
                        oldPromocaoOfProdpromListNewProdprom = em.merge(oldPromocaoOfProdpromListNewProdprom);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = promocao.getCodpromocao();
                if (findPromocao(id) == null) {
                    throw new NonexistentEntityException("The promocao with id " + id + " no longer exists.");
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
            Promocao promocao;
            try {
                promocao = em.getReference(Promocao.class, id);
                promocao.getCodpromocao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The promocao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prodprom> prodpromListOrphanCheck = promocao.getProdpromList();
            for (Prodprom prodpromListOrphanCheckProdprom : prodpromListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Promocao (" + promocao + ") cannot be destroyed since the Prodprom " + prodpromListOrphanCheckProdprom + " in its prodpromList field has a non-nullable promocao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(promocao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Promocao> findPromocaoEntities() {
        return findPromocaoEntities(true, -1, -1);
    }

    public List<Promocao> findPromocaoEntities(int maxResults, int firstResult) {
        return findPromocaoEntities(false, maxResults, firstResult);
    }

    private List<Promocao> findPromocaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Promocao.class));
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

    public Promocao findPromocao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Promocao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPromocaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Promocao> rt = cq.from(Promocao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
