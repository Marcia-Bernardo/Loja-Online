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
import proj2lojaonline.DAL.Transportadora;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;

/**
 *
 * @author marci
 */
public class TransportadoraJpaController1 implements Serializable {

    public TransportadoraJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transportadora transportadora) {
        if (transportadora.getEncomClienteList() == null) {
            transportadora.setEncomClienteList(new ArrayList<EncomCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal cpostal = transportadora.getCpostal();
            if (cpostal != null) {
                cpostal = em.getReference(cpostal.getClass(), cpostal.getCpostal());
                transportadora.setCpostal(cpostal);
            }
            List<EncomCliente> attachedEncomClienteList = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListEncomClienteToAttach : transportadora.getEncomClienteList()) {
                encomClienteListEncomClienteToAttach = em.getReference(encomClienteListEncomClienteToAttach.getClass(), encomClienteListEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteList.add(encomClienteListEncomClienteToAttach);
            }
            transportadora.setEncomClienteList(attachedEncomClienteList);
            em.persist(transportadora);
            if (cpostal != null) {
                cpostal.getTransportadoraList().add(transportadora);
                cpostal = em.merge(cpostal);
            }
            for (EncomCliente encomClienteListEncomCliente : transportadora.getEncomClienteList()) {
                Transportadora oldCodtranspOfEncomClienteListEncomCliente = encomClienteListEncomCliente.getCodtransp();
                encomClienteListEncomCliente.setCodtransp(transportadora);
                encomClienteListEncomCliente = em.merge(encomClienteListEncomCliente);
                if (oldCodtranspOfEncomClienteListEncomCliente != null) {
                    oldCodtranspOfEncomClienteListEncomCliente.getEncomClienteList().remove(encomClienteListEncomCliente);
                    oldCodtranspOfEncomClienteListEncomCliente = em.merge(oldCodtranspOfEncomClienteListEncomCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transportadora transportadora) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transportadora persistentTransportadora = em.find(Transportadora.class, transportadora.getCodtransp());
            Codpostal cpostalOld = persistentTransportadora.getCpostal();
            Codpostal cpostalNew = transportadora.getCpostal();
            List<EncomCliente> encomClienteListOld = persistentTransportadora.getEncomClienteList();
            List<EncomCliente> encomClienteListNew = transportadora.getEncomClienteList();
            List<String> illegalOrphanMessages = null;
            for (EncomCliente encomClienteListOldEncomCliente : encomClienteListOld) {
                if (!encomClienteListNew.contains(encomClienteListOldEncomCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EncomCliente " + encomClienteListOldEncomCliente + " since its codtransp field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cpostalNew != null) {
                cpostalNew = em.getReference(cpostalNew.getClass(), cpostalNew.getCpostal());
                transportadora.setCpostal(cpostalNew);
            }
            List<EncomCliente> attachedEncomClienteListNew = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListNewEncomClienteToAttach : encomClienteListNew) {
                encomClienteListNewEncomClienteToAttach = em.getReference(encomClienteListNewEncomClienteToAttach.getClass(), encomClienteListNewEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteListNew.add(encomClienteListNewEncomClienteToAttach);
            }
            encomClienteListNew = attachedEncomClienteListNew;
            transportadora.setEncomClienteList(encomClienteListNew);
            transportadora = em.merge(transportadora);
            if (cpostalOld != null && !cpostalOld.equals(cpostalNew)) {
                cpostalOld.getTransportadoraList().remove(transportadora);
                cpostalOld = em.merge(cpostalOld);
            }
            if (cpostalNew != null && !cpostalNew.equals(cpostalOld)) {
                cpostalNew.getTransportadoraList().add(transportadora);
                cpostalNew = em.merge(cpostalNew);
            }
            for (EncomCliente encomClienteListNewEncomCliente : encomClienteListNew) {
                if (!encomClienteListOld.contains(encomClienteListNewEncomCliente)) {
                    Transportadora oldCodtranspOfEncomClienteListNewEncomCliente = encomClienteListNewEncomCliente.getCodtransp();
                    encomClienteListNewEncomCliente.setCodtransp(transportadora);
                    encomClienteListNewEncomCliente = em.merge(encomClienteListNewEncomCliente);
                    if (oldCodtranspOfEncomClienteListNewEncomCliente != null && !oldCodtranspOfEncomClienteListNewEncomCliente.equals(transportadora)) {
                        oldCodtranspOfEncomClienteListNewEncomCliente.getEncomClienteList().remove(encomClienteListNewEncomCliente);
                        oldCodtranspOfEncomClienteListNewEncomCliente = em.merge(oldCodtranspOfEncomClienteListNewEncomCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transportadora.getCodtransp();
                if (findTransportadora(id) == null) {
                    throw new NonexistentEntityException("The transportadora with id " + id + " no longer exists.");
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
            Transportadora transportadora;
            try {
                transportadora = em.getReference(Transportadora.class, id);
                transportadora.getCodtransp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transportadora with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EncomCliente> encomClienteListOrphanCheck = transportadora.getEncomClienteList();
            for (EncomCliente encomClienteListOrphanCheckEncomCliente : encomClienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transportadora (" + transportadora + ") cannot be destroyed since the EncomCliente " + encomClienteListOrphanCheckEncomCliente + " in its encomClienteList field has a non-nullable codtransp field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codpostal cpostal = transportadora.getCpostal();
            if (cpostal != null) {
                cpostal.getTransportadoraList().remove(transportadora);
                cpostal = em.merge(cpostal);
            }
            em.remove(transportadora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transportadora> findTransportadoraEntities() {
        return findTransportadoraEntities(true, -1, -1);
    }

    public List<Transportadora> findTransportadoraEntities(int maxResults, int firstResult) {
        return findTransportadoraEntities(false, maxResults, firstResult);
    }

    private List<Transportadora> findTransportadoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transportadora.class));
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

    public Transportadora findTransportadora(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transportadora.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransportadoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transportadora> rt = cq.from(Transportadora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
