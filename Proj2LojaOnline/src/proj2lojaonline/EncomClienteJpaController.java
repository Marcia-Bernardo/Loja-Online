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
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.MoradaDeEntrega;
import proj2lojaonline.DAL.Transportadora;
import proj2lojaonline.DAL.LinhaEnccliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.EncomCliente;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class EncomClienteJpaController implements Serializable {

    public EncomClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EncomCliente encomCliente) throws PreexistingEntityException, Exception {
        if (encomCliente.getLinhaEncclienteList() == null) {
            encomCliente.setLinhaEncclienteList(new ArrayList<LinhaEnccliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente codcliente = encomCliente.getCodcliente();
            if (codcliente != null) {
                codcliente = em.getReference(codcliente.getClass(), codcliente.getCodcliente());
                encomCliente.setCodcliente(codcliente);
            }
            MoradaDeEntrega codmoradaentr = encomCliente.getCodmoradaentr();
            if (codmoradaentr != null) {
                codmoradaentr = em.getReference(codmoradaentr.getClass(), codmoradaentr.getCodmoradaentr());
                encomCliente.setCodmoradaentr(codmoradaentr);
            }
            Transportadora codtransp = encomCliente.getCodtransp();
            if (codtransp != null) {
                codtransp = em.getReference(codtransp.getClass(), codtransp.getCodtransp());
                encomCliente.setCodtransp(codtransp);
            }
            List<LinhaEnccliente> attachedLinhaEncclienteList = new ArrayList<LinhaEnccliente>();
            for (LinhaEnccliente linhaEncclienteListLinhaEncclienteToAttach : encomCliente.getLinhaEncclienteList()) {
                linhaEncclienteListLinhaEncclienteToAttach = em.getReference(linhaEncclienteListLinhaEncclienteToAttach.getClass(), linhaEncclienteListLinhaEncclienteToAttach.getLinhaEncclientePK());
                attachedLinhaEncclienteList.add(linhaEncclienteListLinhaEncclienteToAttach);
            }
            encomCliente.setLinhaEncclienteList(attachedLinhaEncclienteList);
            em.persist(encomCliente);
            if (codcliente != null) {
                codcliente.getEncomClienteList().add(encomCliente);
                codcliente = em.merge(codcliente);
            }
            if (codmoradaentr != null) {
                codmoradaentr.getEncomClienteList().add(encomCliente);
                codmoradaentr = em.merge(codmoradaentr);
            }
            if (codtransp != null) {
                codtransp.getEncomClienteList().add(encomCliente);
                codtransp = em.merge(codtransp);
            }
            for (LinhaEnccliente linhaEncclienteListLinhaEnccliente : encomCliente.getLinhaEncclienteList()) {
                EncomCliente oldEncomClienteOfLinhaEncclienteListLinhaEnccliente = linhaEncclienteListLinhaEnccliente.getEncomCliente();
                linhaEncclienteListLinhaEnccliente.setEncomCliente(encomCliente);
                linhaEncclienteListLinhaEnccliente = em.merge(linhaEncclienteListLinhaEnccliente);
                if (oldEncomClienteOfLinhaEncclienteListLinhaEnccliente != null) {
                    oldEncomClienteOfLinhaEncclienteListLinhaEnccliente.getLinhaEncclienteList().remove(linhaEncclienteListLinhaEnccliente);
                    oldEncomClienteOfLinhaEncclienteListLinhaEnccliente = em.merge(oldEncomClienteOfLinhaEncclienteListLinhaEnccliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncomCliente(encomCliente.getCodenccliente()) != null) {
                throw new PreexistingEntityException("EncomCliente " + encomCliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EncomCliente encomCliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EncomCliente persistentEncomCliente = em.find(EncomCliente.class, encomCliente.getCodenccliente());
            Cliente codclienteOld = persistentEncomCliente.getCodcliente();
            Cliente codclienteNew = encomCliente.getCodcliente();
            MoradaDeEntrega codmoradaentrOld = persistentEncomCliente.getCodmoradaentr();
            MoradaDeEntrega codmoradaentrNew = encomCliente.getCodmoradaentr();
            Transportadora codtranspOld = persistentEncomCliente.getCodtransp();
            Transportadora codtranspNew = encomCliente.getCodtransp();
            List<LinhaEnccliente> linhaEncclienteListOld = persistentEncomCliente.getLinhaEncclienteList();
            List<LinhaEnccliente> linhaEncclienteListNew = encomCliente.getLinhaEncclienteList();
            List<String> illegalOrphanMessages = null;
            for (LinhaEnccliente linhaEncclienteListOldLinhaEnccliente : linhaEncclienteListOld) {
                if (!linhaEncclienteListNew.contains(linhaEncclienteListOldLinhaEnccliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LinhaEnccliente " + linhaEncclienteListOldLinhaEnccliente + " since its encomCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codclienteNew != null) {
                codclienteNew = em.getReference(codclienteNew.getClass(), codclienteNew.getCodcliente());
                encomCliente.setCodcliente(codclienteNew);
            }
            if (codmoradaentrNew != null) {
                codmoradaentrNew = em.getReference(codmoradaentrNew.getClass(), codmoradaentrNew.getCodmoradaentr());
                encomCliente.setCodmoradaentr(codmoradaentrNew);
            }
            if (codtranspNew != null) {
                codtranspNew = em.getReference(codtranspNew.getClass(), codtranspNew.getCodtransp());
                encomCliente.setCodtransp(codtranspNew);
            }
            List<LinhaEnccliente> attachedLinhaEncclienteListNew = new ArrayList<LinhaEnccliente>();
            for (LinhaEnccliente linhaEncclienteListNewLinhaEncclienteToAttach : linhaEncclienteListNew) {
                linhaEncclienteListNewLinhaEncclienteToAttach = em.getReference(linhaEncclienteListNewLinhaEncclienteToAttach.getClass(), linhaEncclienteListNewLinhaEncclienteToAttach.getLinhaEncclientePK());
                attachedLinhaEncclienteListNew.add(linhaEncclienteListNewLinhaEncclienteToAttach);
            }
            linhaEncclienteListNew = attachedLinhaEncclienteListNew;
            encomCliente.setLinhaEncclienteList(linhaEncclienteListNew);
            encomCliente = em.merge(encomCliente);
            if (codclienteOld != null && !codclienteOld.equals(codclienteNew)) {
                codclienteOld.getEncomClienteList().remove(encomCliente);
                codclienteOld = em.merge(codclienteOld);
            }
            if (codclienteNew != null && !codclienteNew.equals(codclienteOld)) {
                codclienteNew.getEncomClienteList().add(encomCliente);
                codclienteNew = em.merge(codclienteNew);
            }
            if (codmoradaentrOld != null && !codmoradaentrOld.equals(codmoradaentrNew)) {
                codmoradaentrOld.getEncomClienteList().remove(encomCliente);
                codmoradaentrOld = em.merge(codmoradaentrOld);
            }
            if (codmoradaentrNew != null && !codmoradaentrNew.equals(codmoradaentrOld)) {
                codmoradaentrNew.getEncomClienteList().add(encomCliente);
                codmoradaentrNew = em.merge(codmoradaentrNew);
            }
            if (codtranspOld != null && !codtranspOld.equals(codtranspNew)) {
                codtranspOld.getEncomClienteList().remove(encomCliente);
                codtranspOld = em.merge(codtranspOld);
            }
            if (codtranspNew != null && !codtranspNew.equals(codtranspOld)) {
                codtranspNew.getEncomClienteList().add(encomCliente);
                codtranspNew = em.merge(codtranspNew);
            }
            for (LinhaEnccliente linhaEncclienteListNewLinhaEnccliente : linhaEncclienteListNew) {
                if (!linhaEncclienteListOld.contains(linhaEncclienteListNewLinhaEnccliente)) {
                    EncomCliente oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente = linhaEncclienteListNewLinhaEnccliente.getEncomCliente();
                    linhaEncclienteListNewLinhaEnccliente.setEncomCliente(encomCliente);
                    linhaEncclienteListNewLinhaEnccliente = em.merge(linhaEncclienteListNewLinhaEnccliente);
                    if (oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente != null && !oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente.equals(encomCliente)) {
                        oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente.getLinhaEncclienteList().remove(linhaEncclienteListNewLinhaEnccliente);
                        oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente = em.merge(oldEncomClienteOfLinhaEncclienteListNewLinhaEnccliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encomCliente.getCodenccliente();
                if (findEncomCliente(id) == null) {
                    throw new NonexistentEntityException("The encomCliente with id " + id + " no longer exists.");
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
            EncomCliente encomCliente;
            try {
                encomCliente = em.getReference(EncomCliente.class, id);
                encomCliente.getCodenccliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encomCliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<LinhaEnccliente> linhaEncclienteListOrphanCheck = encomCliente.getLinhaEncclienteList();
            for (LinhaEnccliente linhaEncclienteListOrphanCheckLinhaEnccliente : linhaEncclienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EncomCliente (" + encomCliente + ") cannot be destroyed since the LinhaEnccliente " + linhaEncclienteListOrphanCheckLinhaEnccliente + " in its linhaEncclienteList field has a non-nullable encomCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente codcliente = encomCliente.getCodcliente();
            if (codcliente != null) {
                codcliente.getEncomClienteList().remove(encomCliente);
                codcliente = em.merge(codcliente);
            }
            MoradaDeEntrega codmoradaentr = encomCliente.getCodmoradaentr();
            if (codmoradaentr != null) {
                codmoradaentr.getEncomClienteList().remove(encomCliente);
                codmoradaentr = em.merge(codmoradaentr);
            }
            Transportadora codtransp = encomCliente.getCodtransp();
            if (codtransp != null) {
                codtransp.getEncomClienteList().remove(encomCliente);
                codtransp = em.merge(codtransp);
            }
            em.remove(encomCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EncomCliente> findEncomClienteEntities() {
        return findEncomClienteEntities(true, -1, -1);
    }

    public List<EncomCliente> findEncomClienteEntities(int maxResults, int firstResult) {
        return findEncomClienteEntities(false, maxResults, firstResult);
    }

    private List<EncomCliente> findEncomClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EncomCliente.class));
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

    public EncomCliente findEncomCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EncomCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncomClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EncomCliente> rt = cq.from(EncomCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
