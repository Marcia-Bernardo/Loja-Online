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
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.EncomForn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.Fornecedor;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class FornecedorJpaController1 implements Serializable {

    public FornecedorJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fornecedor fornecedor) {
        if (fornecedor.getEncomFornList() == null) {
            fornecedor.setEncomFornList(new ArrayList<EncomForn>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal cpostal = fornecedor.getCpostal();
            if (cpostal != null) {
                cpostal = em.getReference(cpostal.getClass(), cpostal.getCpostal());
                fornecedor.setCpostal(cpostal);
            }
            List<EncomForn> attachedEncomFornList = new ArrayList<EncomForn>();
            for (EncomForn encomFornListEncomFornToAttach : fornecedor.getEncomFornList()) {
                encomFornListEncomFornToAttach = em.getReference(encomFornListEncomFornToAttach.getClass(), encomFornListEncomFornToAttach.getCodencforn());
                attachedEncomFornList.add(encomFornListEncomFornToAttach);
            }
            fornecedor.setEncomFornList(attachedEncomFornList);
            em.persist(fornecedor);
            if (cpostal != null) {
                cpostal.getFornecedorList().add(fornecedor);
                cpostal = em.merge(cpostal);
            }
            for (EncomForn encomFornListEncomForn : fornecedor.getEncomFornList()) {
                Fornecedor oldCodfornOfEncomFornListEncomForn = encomFornListEncomForn.getCodforn();
                encomFornListEncomForn.setCodforn(fornecedor);
                encomFornListEncomForn = em.merge(encomFornListEncomForn);
                if (oldCodfornOfEncomFornListEncomForn != null) {
                    oldCodfornOfEncomFornListEncomForn.getEncomFornList().remove(encomFornListEncomForn);
                    oldCodfornOfEncomFornListEncomForn = em.merge(oldCodfornOfEncomFornListEncomForn);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fornecedor fornecedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor persistentFornecedor = em.find(Fornecedor.class, fornecedor.getCodforn());
            Codpostal cpostalOld = persistentFornecedor.getCpostal();
            Codpostal cpostalNew = fornecedor.getCpostal();
            List<EncomForn> encomFornListOld = persistentFornecedor.getEncomFornList();
            List<EncomForn> encomFornListNew = fornecedor.getEncomFornList();
            List<String> illegalOrphanMessages = null;
            for (EncomForn encomFornListOldEncomForn : encomFornListOld) {
                if (!encomFornListNew.contains(encomFornListOldEncomForn)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EncomForn " + encomFornListOldEncomForn + " since its codforn field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cpostalNew != null) {
                cpostalNew = em.getReference(cpostalNew.getClass(), cpostalNew.getCpostal());
                fornecedor.setCpostal(cpostalNew);
            }
            List<EncomForn> attachedEncomFornListNew = new ArrayList<EncomForn>();
            for (EncomForn encomFornListNewEncomFornToAttach : encomFornListNew) {
                encomFornListNewEncomFornToAttach = em.getReference(encomFornListNewEncomFornToAttach.getClass(), encomFornListNewEncomFornToAttach.getCodencforn());
                attachedEncomFornListNew.add(encomFornListNewEncomFornToAttach);
            }
            encomFornListNew = attachedEncomFornListNew;
            fornecedor.setEncomFornList(encomFornListNew);
            fornecedor = em.merge(fornecedor);
            if (cpostalOld != null && !cpostalOld.equals(cpostalNew)) {
                cpostalOld.getFornecedorList().remove(fornecedor);
                cpostalOld = em.merge(cpostalOld);
            }
            if (cpostalNew != null && !cpostalNew.equals(cpostalOld)) {
                cpostalNew.getFornecedorList().add(fornecedor);
                cpostalNew = em.merge(cpostalNew);
            }
            for (EncomForn encomFornListNewEncomForn : encomFornListNew) {
                if (!encomFornListOld.contains(encomFornListNewEncomForn)) {
                    Fornecedor oldCodfornOfEncomFornListNewEncomForn = encomFornListNewEncomForn.getCodforn();
                    encomFornListNewEncomForn.setCodforn(fornecedor);
                    encomFornListNewEncomForn = em.merge(encomFornListNewEncomForn);
                    if (oldCodfornOfEncomFornListNewEncomForn != null && !oldCodfornOfEncomFornListNewEncomForn.equals(fornecedor)) {
                        oldCodfornOfEncomFornListNewEncomForn.getEncomFornList().remove(encomFornListNewEncomForn);
                        oldCodfornOfEncomFornListNewEncomForn = em.merge(oldCodfornOfEncomFornListNewEncomForn);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fornecedor.getCodforn();
                if (findFornecedor(id) == null) {
                    throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.");
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
            Fornecedor fornecedor;
            try {
                fornecedor = em.getReference(Fornecedor.class, id);
                fornecedor.getCodforn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EncomForn> encomFornListOrphanCheck = fornecedor.getEncomFornList();
            for (EncomForn encomFornListOrphanCheckEncomForn : encomFornListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fornecedor (" + fornecedor + ") cannot be destroyed since the EncomForn " + encomFornListOrphanCheckEncomForn + " in its encomFornList field has a non-nullable codforn field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codpostal cpostal = fornecedor.getCpostal();
            if (cpostal != null) {
                cpostal.getFornecedorList().remove(fornecedor);
                cpostal = em.merge(cpostal);
            }
            em.remove(fornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fornecedor> findFornecedorEntities() {
        return findFornecedorEntities(true, -1, -1);
    }

    public List<Fornecedor> findFornecedorEntities(int maxResults, int firstResult) {
        return findFornecedorEntities(false, maxResults, firstResult);
    }

    private List<Fornecedor> findFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
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

    public Fornecedor findFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fornecedor> rt = cq.from(Fornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
