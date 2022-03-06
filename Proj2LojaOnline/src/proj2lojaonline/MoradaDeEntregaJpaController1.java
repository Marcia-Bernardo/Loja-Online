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
import proj2lojaonline.DAL.EncomCliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.MoradaDeEntrega;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class MoradaDeEntregaJpaController1 implements Serializable {

    public MoradaDeEntregaJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MoradaDeEntrega moradaDeEntrega) {
        if (moradaDeEntrega.getEncomClienteList() == null) {
            moradaDeEntrega.setEncomClienteList(new ArrayList<EncomCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal cpostal = moradaDeEntrega.getCpostal();
            if (cpostal != null) {
                cpostal = em.getReference(cpostal.getClass(), cpostal.getCpostal());
                moradaDeEntrega.setCpostal(cpostal);
            }
            List<EncomCliente> attachedEncomClienteList = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListEncomClienteToAttach : moradaDeEntrega.getEncomClienteList()) {
                encomClienteListEncomClienteToAttach = em.getReference(encomClienteListEncomClienteToAttach.getClass(), encomClienteListEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteList.add(encomClienteListEncomClienteToAttach);
            }
            moradaDeEntrega.setEncomClienteList(attachedEncomClienteList);
            em.persist(moradaDeEntrega);
            if (cpostal != null) {
                cpostal.getMoradaDeEntregaList().add(moradaDeEntrega);
                cpostal = em.merge(cpostal);
            }
            for (EncomCliente encomClienteListEncomCliente : moradaDeEntrega.getEncomClienteList()) {
                MoradaDeEntrega oldCodmoradaentrOfEncomClienteListEncomCliente = encomClienteListEncomCliente.getCodmoradaentr();
                encomClienteListEncomCliente.setCodmoradaentr(moradaDeEntrega);
                encomClienteListEncomCliente = em.merge(encomClienteListEncomCliente);
                if (oldCodmoradaentrOfEncomClienteListEncomCliente != null) {
                    oldCodmoradaentrOfEncomClienteListEncomCliente.getEncomClienteList().remove(encomClienteListEncomCliente);
                    oldCodmoradaentrOfEncomClienteListEncomCliente = em.merge(oldCodmoradaentrOfEncomClienteListEncomCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MoradaDeEntrega moradaDeEntrega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MoradaDeEntrega persistentMoradaDeEntrega = em.find(MoradaDeEntrega.class, moradaDeEntrega.getCodmoradaentr());
            Codpostal cpostalOld = persistentMoradaDeEntrega.getCpostal();
            Codpostal cpostalNew = moradaDeEntrega.getCpostal();
            List<EncomCliente> encomClienteListOld = persistentMoradaDeEntrega.getEncomClienteList();
            List<EncomCliente> encomClienteListNew = moradaDeEntrega.getEncomClienteList();
            List<String> illegalOrphanMessages = null;
            for (EncomCliente encomClienteListOldEncomCliente : encomClienteListOld) {
                if (!encomClienteListNew.contains(encomClienteListOldEncomCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EncomCliente " + encomClienteListOldEncomCliente + " since its codmoradaentr field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cpostalNew != null) {
                cpostalNew = em.getReference(cpostalNew.getClass(), cpostalNew.getCpostal());
                moradaDeEntrega.setCpostal(cpostalNew);
            }
            List<EncomCliente> attachedEncomClienteListNew = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListNewEncomClienteToAttach : encomClienteListNew) {
                encomClienteListNewEncomClienteToAttach = em.getReference(encomClienteListNewEncomClienteToAttach.getClass(), encomClienteListNewEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteListNew.add(encomClienteListNewEncomClienteToAttach);
            }
            encomClienteListNew = attachedEncomClienteListNew;
            moradaDeEntrega.setEncomClienteList(encomClienteListNew);
            moradaDeEntrega = em.merge(moradaDeEntrega);
            if (cpostalOld != null && !cpostalOld.equals(cpostalNew)) {
                cpostalOld.getMoradaDeEntregaList().remove(moradaDeEntrega);
                cpostalOld = em.merge(cpostalOld);
            }
            if (cpostalNew != null && !cpostalNew.equals(cpostalOld)) {
                cpostalNew.getMoradaDeEntregaList().add(moradaDeEntrega);
                cpostalNew = em.merge(cpostalNew);
            }
            for (EncomCliente encomClienteListNewEncomCliente : encomClienteListNew) {
                if (!encomClienteListOld.contains(encomClienteListNewEncomCliente)) {
                    MoradaDeEntrega oldCodmoradaentrOfEncomClienteListNewEncomCliente = encomClienteListNewEncomCliente.getCodmoradaentr();
                    encomClienteListNewEncomCliente.setCodmoradaentr(moradaDeEntrega);
                    encomClienteListNewEncomCliente = em.merge(encomClienteListNewEncomCliente);
                    if (oldCodmoradaentrOfEncomClienteListNewEncomCliente != null && !oldCodmoradaentrOfEncomClienteListNewEncomCliente.equals(moradaDeEntrega)) {
                        oldCodmoradaentrOfEncomClienteListNewEncomCliente.getEncomClienteList().remove(encomClienteListNewEncomCliente);
                        oldCodmoradaentrOfEncomClienteListNewEncomCliente = em.merge(oldCodmoradaentrOfEncomClienteListNewEncomCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = moradaDeEntrega.getCodmoradaentr();
                if (findMoradaDeEntrega(id) == null) {
                    throw new NonexistentEntityException("The moradaDeEntrega with id " + id + " no longer exists.");
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
            MoradaDeEntrega moradaDeEntrega;
            try {
                moradaDeEntrega = em.getReference(MoradaDeEntrega.class, id);
                moradaDeEntrega.getCodmoradaentr();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moradaDeEntrega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EncomCliente> encomClienteListOrphanCheck = moradaDeEntrega.getEncomClienteList();
            for (EncomCliente encomClienteListOrphanCheckEncomCliente : encomClienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MoradaDeEntrega (" + moradaDeEntrega + ") cannot be destroyed since the EncomCliente " + encomClienteListOrphanCheckEncomCliente + " in its encomClienteList field has a non-nullable codmoradaentr field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codpostal cpostal = moradaDeEntrega.getCpostal();
            if (cpostal != null) {
                cpostal.getMoradaDeEntregaList().remove(moradaDeEntrega);
                cpostal = em.merge(cpostal);
            }
            em.remove(moradaDeEntrega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MoradaDeEntrega> findMoradaDeEntregaEntities() {
        return findMoradaDeEntregaEntities(true, -1, -1);
    }

    public List<MoradaDeEntrega> findMoradaDeEntregaEntities(int maxResults, int firstResult) {
        return findMoradaDeEntregaEntities(false, maxResults, firstResult);
    }

    private List<MoradaDeEntrega> findMoradaDeEntregaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MoradaDeEntrega.class));
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

    public MoradaDeEntrega findMoradaDeEntrega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MoradaDeEntrega.class, id);
        } finally {
            em.close();
        }
    }

    public int getMoradaDeEntregaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MoradaDeEntrega> rt = cq.from(MoradaDeEntrega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
