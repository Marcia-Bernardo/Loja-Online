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
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getEncomClienteList() == null) {
            cliente.setEncomClienteList(new ArrayList<EncomCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal cpostal = cliente.getCpostal();
            if (cpostal != null) {
                cpostal = em.getReference(cpostal.getClass(), cpostal.getCpostal());
                cliente.setCpostal(cpostal);
            }
            List<EncomCliente> attachedEncomClienteList = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListEncomClienteToAttach : cliente.getEncomClienteList()) {
                encomClienteListEncomClienteToAttach = em.getReference(encomClienteListEncomClienteToAttach.getClass(), encomClienteListEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteList.add(encomClienteListEncomClienteToAttach);
            }
            cliente.setEncomClienteList(attachedEncomClienteList);
            em.persist(cliente);
            if (cpostal != null) {
                cpostal.getClienteList().add(cliente);
                cpostal = em.merge(cpostal);
            }
            for (EncomCliente encomClienteListEncomCliente : cliente.getEncomClienteList()) {
                Cliente oldCodclienteOfEncomClienteListEncomCliente = encomClienteListEncomCliente.getCodcliente();
                encomClienteListEncomCliente.setCodcliente(cliente);
                encomClienteListEncomCliente = em.merge(encomClienteListEncomCliente);
                if (oldCodclienteOfEncomClienteListEncomCliente != null) {
                    oldCodclienteOfEncomClienteListEncomCliente.getEncomClienteList().remove(encomClienteListEncomCliente);
                    oldCodclienteOfEncomClienteListEncomCliente = em.merge(oldCodclienteOfEncomClienteListEncomCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getCodcliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getCodcliente());
            Codpostal cpostalOld = persistentCliente.getCpostal();
            Codpostal cpostalNew = cliente.getCpostal();
            List<EncomCliente> encomClienteListOld = persistentCliente.getEncomClienteList();
            List<EncomCliente> encomClienteListNew = cliente.getEncomClienteList();
            List<String> illegalOrphanMessages = null;
            for (EncomCliente encomClienteListOldEncomCliente : encomClienteListOld) {
                if (!encomClienteListNew.contains(encomClienteListOldEncomCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EncomCliente " + encomClienteListOldEncomCliente + " since its codcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cpostalNew != null) {
                cpostalNew = em.getReference(cpostalNew.getClass(), cpostalNew.getCpostal());
                cliente.setCpostal(cpostalNew);
            }
            List<EncomCliente> attachedEncomClienteListNew = new ArrayList<EncomCliente>();
            for (EncomCliente encomClienteListNewEncomClienteToAttach : encomClienteListNew) {
                encomClienteListNewEncomClienteToAttach = em.getReference(encomClienteListNewEncomClienteToAttach.getClass(), encomClienteListNewEncomClienteToAttach.getCodenccliente());
                attachedEncomClienteListNew.add(encomClienteListNewEncomClienteToAttach);
            }
            encomClienteListNew = attachedEncomClienteListNew;
            cliente.setEncomClienteList(encomClienteListNew);
            cliente = em.merge(cliente);
            if (cpostalOld != null && !cpostalOld.equals(cpostalNew)) {
                cpostalOld.getClienteList().remove(cliente);
                cpostalOld = em.merge(cpostalOld);
            }
            if (cpostalNew != null && !cpostalNew.equals(cpostalOld)) {
                cpostalNew.getClienteList().add(cliente);
                cpostalNew = em.merge(cpostalNew);
            }
            for (EncomCliente encomClienteListNewEncomCliente : encomClienteListNew) {
                if (!encomClienteListOld.contains(encomClienteListNewEncomCliente)) {
                    Cliente oldCodclienteOfEncomClienteListNewEncomCliente = encomClienteListNewEncomCliente.getCodcliente();
                    encomClienteListNewEncomCliente.setCodcliente(cliente);
                    encomClienteListNewEncomCliente = em.merge(encomClienteListNewEncomCliente);
                    if (oldCodclienteOfEncomClienteListNewEncomCliente != null && !oldCodclienteOfEncomClienteListNewEncomCliente.equals(cliente)) {
                        oldCodclienteOfEncomClienteListNewEncomCliente.getEncomClienteList().remove(encomClienteListNewEncomCliente);
                        oldCodclienteOfEncomClienteListNewEncomCliente = em.merge(oldCodclienteOfEncomClienteListNewEncomCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getCodcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getCodcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EncomCliente> encomClienteListOrphanCheck = cliente.getEncomClienteList();
            for (EncomCliente encomClienteListOrphanCheckEncomCliente : encomClienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the EncomCliente " + encomClienteListOrphanCheckEncomCliente + " in its encomClienteList field has a non-nullable codcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codpostal cpostal = cliente.getCpostal();
            if (cpostal != null) {
                cpostal.getClienteList().remove(cliente);
                cpostal = em.merge(cpostal);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
