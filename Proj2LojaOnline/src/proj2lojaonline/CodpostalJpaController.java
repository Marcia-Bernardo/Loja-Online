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
import proj2lojaonline.DAL.MoradaDeEntrega;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.Fornecedor;
import proj2lojaonline.DAL.Transportadora;
import proj2lojaonline.exceptions.IllegalOrphanException;
import proj2lojaonline.exceptions.NonexistentEntityException;
import proj2lojaonline.exceptions.PreexistingEntityException;

/**
 *
 * @author marci
 */
public class CodpostalJpaController implements Serializable {

    public CodpostalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Codpostal codpostal) throws PreexistingEntityException, Exception {
        if (codpostal.getMoradaDeEntregaList() == null) {
            codpostal.setMoradaDeEntregaList(new ArrayList<MoradaDeEntrega>());
        }
        if (codpostal.getClienteList() == null) {
            codpostal.setClienteList(new ArrayList<Cliente>());
        }
        if (codpostal.getFornecedorList() == null) {
            codpostal.setFornecedorList(new ArrayList<Fornecedor>());
        }
        if (codpostal.getTransportadoraList() == null) {
            codpostal.setTransportadoraList(new ArrayList<Transportadora>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MoradaDeEntrega> attachedMoradaDeEntregaList = new ArrayList<MoradaDeEntrega>();
            for (MoradaDeEntrega moradaDeEntregaListMoradaDeEntregaToAttach : codpostal.getMoradaDeEntregaList()) {
                moradaDeEntregaListMoradaDeEntregaToAttach = em.getReference(moradaDeEntregaListMoradaDeEntregaToAttach.getClass(), moradaDeEntregaListMoradaDeEntregaToAttach.getCodmoradaentr());
                attachedMoradaDeEntregaList.add(moradaDeEntregaListMoradaDeEntregaToAttach);
            }
            codpostal.setMoradaDeEntregaList(attachedMoradaDeEntregaList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : codpostal.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getCodcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            codpostal.setClienteList(attachedClienteList);
            List<Fornecedor> attachedFornecedorList = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorListFornecedorToAttach : codpostal.getFornecedorList()) {
                fornecedorListFornecedorToAttach = em.getReference(fornecedorListFornecedorToAttach.getClass(), fornecedorListFornecedorToAttach.getCodforn());
                attachedFornecedorList.add(fornecedorListFornecedorToAttach);
            }
            codpostal.setFornecedorList(attachedFornecedorList);
            List<Transportadora> attachedTransportadoraList = new ArrayList<Transportadora>();
            for (Transportadora transportadoraListTransportadoraToAttach : codpostal.getTransportadoraList()) {
                transportadoraListTransportadoraToAttach = em.getReference(transportadoraListTransportadoraToAttach.getClass(), transportadoraListTransportadoraToAttach.getCodtransp());
                attachedTransportadoraList.add(transportadoraListTransportadoraToAttach);
            }
            codpostal.setTransportadoraList(attachedTransportadoraList);
            em.persist(codpostal);
            for (MoradaDeEntrega moradaDeEntregaListMoradaDeEntrega : codpostal.getMoradaDeEntregaList()) {
                Codpostal oldCpostalOfMoradaDeEntregaListMoradaDeEntrega = moradaDeEntregaListMoradaDeEntrega.getCpostal();
                moradaDeEntregaListMoradaDeEntrega.setCpostal(codpostal);
                moradaDeEntregaListMoradaDeEntrega = em.merge(moradaDeEntregaListMoradaDeEntrega);
                if (oldCpostalOfMoradaDeEntregaListMoradaDeEntrega != null) {
                    oldCpostalOfMoradaDeEntregaListMoradaDeEntrega.getMoradaDeEntregaList().remove(moradaDeEntregaListMoradaDeEntrega);
                    oldCpostalOfMoradaDeEntregaListMoradaDeEntrega = em.merge(oldCpostalOfMoradaDeEntregaListMoradaDeEntrega);
                }
            }
            for (Cliente clienteListCliente : codpostal.getClienteList()) {
                Codpostal oldCpostalOfClienteListCliente = clienteListCliente.getCpostal();
                clienteListCliente.setCpostal(codpostal);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldCpostalOfClienteListCliente != null) {
                    oldCpostalOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldCpostalOfClienteListCliente = em.merge(oldCpostalOfClienteListCliente);
                }
            }
            for (Fornecedor fornecedorListFornecedor : codpostal.getFornecedorList()) {
                Codpostal oldCpostalOfFornecedorListFornecedor = fornecedorListFornecedor.getCpostal();
                fornecedorListFornecedor.setCpostal(codpostal);
                fornecedorListFornecedor = em.merge(fornecedorListFornecedor);
                if (oldCpostalOfFornecedorListFornecedor != null) {
                    oldCpostalOfFornecedorListFornecedor.getFornecedorList().remove(fornecedorListFornecedor);
                    oldCpostalOfFornecedorListFornecedor = em.merge(oldCpostalOfFornecedorListFornecedor);
                }
            }
            for (Transportadora transportadoraListTransportadora : codpostal.getTransportadoraList()) {
                Codpostal oldCpostalOfTransportadoraListTransportadora = transportadoraListTransportadora.getCpostal();
                transportadoraListTransportadora.setCpostal(codpostal);
                transportadoraListTransportadora = em.merge(transportadoraListTransportadora);
                if (oldCpostalOfTransportadoraListTransportadora != null) {
                    oldCpostalOfTransportadoraListTransportadora.getTransportadoraList().remove(transportadoraListTransportadora);
                    oldCpostalOfTransportadoraListTransportadora = em.merge(oldCpostalOfTransportadoraListTransportadora);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCodpostal(codpostal.getCpostal()) != null) {
                throw new PreexistingEntityException("Codpostal " + codpostal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Codpostal codpostal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal persistentCodpostal = em.find(Codpostal.class, codpostal.getCpostal());
            List<MoradaDeEntrega> moradaDeEntregaListOld = persistentCodpostal.getMoradaDeEntregaList();
            List<MoradaDeEntrega> moradaDeEntregaListNew = codpostal.getMoradaDeEntregaList();
            List<Cliente> clienteListOld = persistentCodpostal.getClienteList();
            List<Cliente> clienteListNew = codpostal.getClienteList();
            List<Fornecedor> fornecedorListOld = persistentCodpostal.getFornecedorList();
            List<Fornecedor> fornecedorListNew = codpostal.getFornecedorList();
            List<Transportadora> transportadoraListOld = persistentCodpostal.getTransportadoraList();
            List<Transportadora> transportadoraListNew = codpostal.getTransportadoraList();
            List<String> illegalOrphanMessages = null;
            for (MoradaDeEntrega moradaDeEntregaListOldMoradaDeEntrega : moradaDeEntregaListOld) {
                if (!moradaDeEntregaListNew.contains(moradaDeEntregaListOldMoradaDeEntrega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MoradaDeEntrega " + moradaDeEntregaListOldMoradaDeEntrega + " since its cpostal field is not nullable.");
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its cpostal field is not nullable.");
                }
            }
            for (Fornecedor fornecedorListOldFornecedor : fornecedorListOld) {
                if (!fornecedorListNew.contains(fornecedorListOldFornecedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fornecedor " + fornecedorListOldFornecedor + " since its cpostal field is not nullable.");
                }
            }
            for (Transportadora transportadoraListOldTransportadora : transportadoraListOld) {
                if (!transportadoraListNew.contains(transportadoraListOldTransportadora)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transportadora " + transportadoraListOldTransportadora + " since its cpostal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MoradaDeEntrega> attachedMoradaDeEntregaListNew = new ArrayList<MoradaDeEntrega>();
            for (MoradaDeEntrega moradaDeEntregaListNewMoradaDeEntregaToAttach : moradaDeEntregaListNew) {
                moradaDeEntregaListNewMoradaDeEntregaToAttach = em.getReference(moradaDeEntregaListNewMoradaDeEntregaToAttach.getClass(), moradaDeEntregaListNewMoradaDeEntregaToAttach.getCodmoradaentr());
                attachedMoradaDeEntregaListNew.add(moradaDeEntregaListNewMoradaDeEntregaToAttach);
            }
            moradaDeEntregaListNew = attachedMoradaDeEntregaListNew;
            codpostal.setMoradaDeEntregaList(moradaDeEntregaListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getCodcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            codpostal.setClienteList(clienteListNew);
            List<Fornecedor> attachedFornecedorListNew = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorListNewFornecedorToAttach : fornecedorListNew) {
                fornecedorListNewFornecedorToAttach = em.getReference(fornecedorListNewFornecedorToAttach.getClass(), fornecedorListNewFornecedorToAttach.getCodforn());
                attachedFornecedorListNew.add(fornecedorListNewFornecedorToAttach);
            }
            fornecedorListNew = attachedFornecedorListNew;
            codpostal.setFornecedorList(fornecedorListNew);
            List<Transportadora> attachedTransportadoraListNew = new ArrayList<Transportadora>();
            for (Transportadora transportadoraListNewTransportadoraToAttach : transportadoraListNew) {
                transportadoraListNewTransportadoraToAttach = em.getReference(transportadoraListNewTransportadoraToAttach.getClass(), transportadoraListNewTransportadoraToAttach.getCodtransp());
                attachedTransportadoraListNew.add(transportadoraListNewTransportadoraToAttach);
            }
            transportadoraListNew = attachedTransportadoraListNew;
            codpostal.setTransportadoraList(transportadoraListNew);
            codpostal = em.merge(codpostal);
            for (MoradaDeEntrega moradaDeEntregaListNewMoradaDeEntrega : moradaDeEntregaListNew) {
                if (!moradaDeEntregaListOld.contains(moradaDeEntregaListNewMoradaDeEntrega)) {
                    Codpostal oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega = moradaDeEntregaListNewMoradaDeEntrega.getCpostal();
                    moradaDeEntregaListNewMoradaDeEntrega.setCpostal(codpostal);
                    moradaDeEntregaListNewMoradaDeEntrega = em.merge(moradaDeEntregaListNewMoradaDeEntrega);
                    if (oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega != null && !oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega.equals(codpostal)) {
                        oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega.getMoradaDeEntregaList().remove(moradaDeEntregaListNewMoradaDeEntrega);
                        oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega = em.merge(oldCpostalOfMoradaDeEntregaListNewMoradaDeEntrega);
                    }
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Codpostal oldCpostalOfClienteListNewCliente = clienteListNewCliente.getCpostal();
                    clienteListNewCliente.setCpostal(codpostal);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldCpostalOfClienteListNewCliente != null && !oldCpostalOfClienteListNewCliente.equals(codpostal)) {
                        oldCpostalOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldCpostalOfClienteListNewCliente = em.merge(oldCpostalOfClienteListNewCliente);
                    }
                }
            }
            for (Fornecedor fornecedorListNewFornecedor : fornecedorListNew) {
                if (!fornecedorListOld.contains(fornecedorListNewFornecedor)) {
                    Codpostal oldCpostalOfFornecedorListNewFornecedor = fornecedorListNewFornecedor.getCpostal();
                    fornecedorListNewFornecedor.setCpostal(codpostal);
                    fornecedorListNewFornecedor = em.merge(fornecedorListNewFornecedor);
                    if (oldCpostalOfFornecedorListNewFornecedor != null && !oldCpostalOfFornecedorListNewFornecedor.equals(codpostal)) {
                        oldCpostalOfFornecedorListNewFornecedor.getFornecedorList().remove(fornecedorListNewFornecedor);
                        oldCpostalOfFornecedorListNewFornecedor = em.merge(oldCpostalOfFornecedorListNewFornecedor);
                    }
                }
            }
            for (Transportadora transportadoraListNewTransportadora : transportadoraListNew) {
                if (!transportadoraListOld.contains(transportadoraListNewTransportadora)) {
                    Codpostal oldCpostalOfTransportadoraListNewTransportadora = transportadoraListNewTransportadora.getCpostal();
                    transportadoraListNewTransportadora.setCpostal(codpostal);
                    transportadoraListNewTransportadora = em.merge(transportadoraListNewTransportadora);
                    if (oldCpostalOfTransportadoraListNewTransportadora != null && !oldCpostalOfTransportadoraListNewTransportadora.equals(codpostal)) {
                        oldCpostalOfTransportadoraListNewTransportadora.getTransportadoraList().remove(transportadoraListNewTransportadora);
                        oldCpostalOfTransportadoraListNewTransportadora = em.merge(oldCpostalOfTransportadoraListNewTransportadora);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = codpostal.getCpostal();
                if (findCodpostal(id) == null) {
                    throw new NonexistentEntityException("The codpostal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codpostal;
            try {
                codpostal = em.getReference(Codpostal.class, id);
                codpostal.getCpostal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The codpostal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MoradaDeEntrega> moradaDeEntregaListOrphanCheck = codpostal.getMoradaDeEntregaList();
            for (MoradaDeEntrega moradaDeEntregaListOrphanCheckMoradaDeEntrega : moradaDeEntregaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Codpostal (" + codpostal + ") cannot be destroyed since the MoradaDeEntrega " + moradaDeEntregaListOrphanCheckMoradaDeEntrega + " in its moradaDeEntregaList field has a non-nullable cpostal field.");
            }
            List<Cliente> clienteListOrphanCheck = codpostal.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Codpostal (" + codpostal + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable cpostal field.");
            }
            List<Fornecedor> fornecedorListOrphanCheck = codpostal.getFornecedorList();
            for (Fornecedor fornecedorListOrphanCheckFornecedor : fornecedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Codpostal (" + codpostal + ") cannot be destroyed since the Fornecedor " + fornecedorListOrphanCheckFornecedor + " in its fornecedorList field has a non-nullable cpostal field.");
            }
            List<Transportadora> transportadoraListOrphanCheck = codpostal.getTransportadoraList();
            for (Transportadora transportadoraListOrphanCheckTransportadora : transportadoraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Codpostal (" + codpostal + ") cannot be destroyed since the Transportadora " + transportadoraListOrphanCheckTransportadora + " in its transportadoraList field has a non-nullable cpostal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(codpostal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Codpostal> findCodpostalEntities() {
        return findCodpostalEntities(true, -1, -1);
    }

    public List<Codpostal> findCodpostalEntities(int maxResults, int firstResult) {
        return findCodpostalEntities(false, maxResults, firstResult);
    }

    private List<Codpostal> findCodpostalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Codpostal.class));
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

    public Codpostal findCodpostal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Codpostal.class, id);
        } finally {
            em.close();
        }
    }

    public int getCodpostalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Codpostal> rt = cq.from(Codpostal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
