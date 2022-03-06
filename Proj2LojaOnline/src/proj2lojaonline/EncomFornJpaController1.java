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
import proj2lojaonline.DAL.Fornecedor;
import proj2lojaonline.DAL.LinhaEncforn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.EncomForn;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class EncomFornJpaController1 implements Serializable {

    public EncomFornJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EncomForn encomForn) {
        if (encomForn.getLinhaEncfornList() == null) {
            encomForn.setLinhaEncfornList(new ArrayList<LinhaEncforn>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor codforn = encomForn.getCodforn();
            if (codforn != null) {
                codforn = em.getReference(codforn.getClass(), codforn.getCodforn());
                encomForn.setCodforn(codforn);
            }
            List<LinhaEncforn> attachedLinhaEncfornList = new ArrayList<LinhaEncforn>();
            for (LinhaEncforn linhaEncfornListLinhaEncfornToAttach : encomForn.getLinhaEncfornList()) {
                linhaEncfornListLinhaEncfornToAttach = em.getReference(linhaEncfornListLinhaEncfornToAttach.getClass(), linhaEncfornListLinhaEncfornToAttach.getLinhaEncfornPK());
                attachedLinhaEncfornList.add(linhaEncfornListLinhaEncfornToAttach);
            }
            encomForn.setLinhaEncfornList(attachedLinhaEncfornList);
            em.persist(encomForn);
            if (codforn != null) {
                codforn.getEncomFornList().add(encomForn);
                codforn = em.merge(codforn);
            }
            for (LinhaEncforn linhaEncfornListLinhaEncforn : encomForn.getLinhaEncfornList()) {
                EncomForn oldEncomFornOfLinhaEncfornListLinhaEncforn = linhaEncfornListLinhaEncforn.getEncomForn();
                linhaEncfornListLinhaEncforn.setEncomForn(encomForn);
                linhaEncfornListLinhaEncforn = em.merge(linhaEncfornListLinhaEncforn);
                if (oldEncomFornOfLinhaEncfornListLinhaEncforn != null) {
                    oldEncomFornOfLinhaEncfornListLinhaEncforn.getLinhaEncfornList().remove(linhaEncfornListLinhaEncforn);
                    oldEncomFornOfLinhaEncfornListLinhaEncforn = em.merge(oldEncomFornOfLinhaEncfornListLinhaEncforn);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EncomForn encomForn) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncomForn persistentEncomForn = em.find(EncomForn.class, encomForn.getCodencforn());
            Fornecedor codfornOld = persistentEncomForn.getCodforn();
            Fornecedor codfornNew = encomForn.getCodforn();
            List<LinhaEncforn> linhaEncfornListOld = persistentEncomForn.getLinhaEncfornList();
            List<LinhaEncforn> linhaEncfornListNew = encomForn.getLinhaEncfornList();
            List<String> illegalOrphanMessages = null;
            for (LinhaEncforn linhaEncfornListOldLinhaEncforn : linhaEncfornListOld) {
                if (!linhaEncfornListNew.contains(linhaEncfornListOldLinhaEncforn)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LinhaEncforn " + linhaEncfornListOldLinhaEncforn + " since its encomForn field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfornNew != null) {
                codfornNew = em.getReference(codfornNew.getClass(), codfornNew.getCodforn());
                encomForn.setCodforn(codfornNew);
            }
            List<LinhaEncforn> attachedLinhaEncfornListNew = new ArrayList<LinhaEncforn>();
            for (LinhaEncforn linhaEncfornListNewLinhaEncfornToAttach : linhaEncfornListNew) {
                linhaEncfornListNewLinhaEncfornToAttach = em.getReference(linhaEncfornListNewLinhaEncfornToAttach.getClass(), linhaEncfornListNewLinhaEncfornToAttach.getLinhaEncfornPK());
                attachedLinhaEncfornListNew.add(linhaEncfornListNewLinhaEncfornToAttach);
            }
            linhaEncfornListNew = attachedLinhaEncfornListNew;
            encomForn.setLinhaEncfornList(linhaEncfornListNew);
            encomForn = em.merge(encomForn);
            if (codfornOld != null && !codfornOld.equals(codfornNew)) {
                codfornOld.getEncomFornList().remove(encomForn);
                codfornOld = em.merge(codfornOld);
            }
            if (codfornNew != null && !codfornNew.equals(codfornOld)) {
                codfornNew.getEncomFornList().add(encomForn);
                codfornNew = em.merge(codfornNew);
            }
            for (LinhaEncforn linhaEncfornListNewLinhaEncforn : linhaEncfornListNew) {
                if (!linhaEncfornListOld.contains(linhaEncfornListNewLinhaEncforn)) {
                    EncomForn oldEncomFornOfLinhaEncfornListNewLinhaEncforn = linhaEncfornListNewLinhaEncforn.getEncomForn();
                    linhaEncfornListNewLinhaEncforn.setEncomForn(encomForn);
                    linhaEncfornListNewLinhaEncforn = em.merge(linhaEncfornListNewLinhaEncforn);
                    if (oldEncomFornOfLinhaEncfornListNewLinhaEncforn != null && !oldEncomFornOfLinhaEncfornListNewLinhaEncforn.equals(encomForn)) {
                        oldEncomFornOfLinhaEncfornListNewLinhaEncforn.getLinhaEncfornList().remove(linhaEncfornListNewLinhaEncforn);
                        oldEncomFornOfLinhaEncfornListNewLinhaEncforn = em.merge(oldEncomFornOfLinhaEncfornListNewLinhaEncforn);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encomForn.getCodencforn();
                if (findEncomForn(id) == null) {
                    throw new NonexistentEntityException("The encomForn with id " + id + " no longer exists.");
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
            EncomForn encomForn;
            try {
                encomForn = em.getReference(EncomForn.class, id);
                encomForn.getCodencforn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encomForn with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<LinhaEncforn> linhaEncfornListOrphanCheck = encomForn.getLinhaEncfornList();
            for (LinhaEncforn linhaEncfornListOrphanCheckLinhaEncforn : linhaEncfornListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EncomForn (" + encomForn + ") cannot be destroyed since the LinhaEncforn " + linhaEncfornListOrphanCheckLinhaEncforn + " in its linhaEncfornList field has a non-nullable encomForn field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fornecedor codforn = encomForn.getCodforn();
            if (codforn != null) {
                codforn.getEncomFornList().remove(encomForn);
                codforn = em.merge(codforn);
            }
            em.remove(encomForn);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EncomForn> findEncomFornEntities() {
        return findEncomFornEntities(true, -1, -1);
    }

    public List<EncomForn> findEncomFornEntities(int maxResults, int firstResult) {
        return findEncomFornEntities(false, maxResults, firstResult);
    }

    private List<EncomForn> findEncomFornEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EncomForn.class));
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

    public EncomForn findEncomForn(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EncomForn.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncomFornCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EncomForn> rt = cq.from(EncomForn.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
