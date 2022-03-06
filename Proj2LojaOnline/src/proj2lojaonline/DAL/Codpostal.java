/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marci
 */
@Entity
@Table(name = "CODPOSTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codpostal.findAll", query = "SELECT c FROM Codpostal c")
    , @NamedQuery(name = "Codpostal.findByCpostal", query = "SELECT c FROM Codpostal c WHERE c.cpostal = :cpostal")
    , @NamedQuery(name = "Codpostal.findByLocalizacao", query = "SELECT c FROM Codpostal c WHERE c.localizacao = :localizacao")})
public class Codpostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CPOSTAL")
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    public String cpostal;
    @Basic(optional = false)
    @Column(name = "LOCALIZACAO")
    private String localizacao;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cpostal")
    private List<MoradaDeEntrega> moradaDeEntregaList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cpostal")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cpostal")
    private List<Fornecedor> fornecedorList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cpostal")
    private List<Transportadora> transportadoraList;

    public Codpostal() {
    }

    public Codpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public Codpostal(String cpostal, String localizacao) {
        this.cpostal = cpostal;
        this.localizacao = localizacao;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @XmlTransient
    public List<MoradaDeEntrega> getMoradaDeEntregaList() {
        return moradaDeEntregaList;
    }

    public void setMoradaDeEntregaList(List<MoradaDeEntrega> moradaDeEntregaList) {
        this.moradaDeEntregaList = moradaDeEntregaList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

    @XmlTransient
    public List<Transportadora> getTransportadoraList() {
        return transportadoraList;
    }

    public void setTransportadoraList(List<Transportadora> transportadoraList) {
        this.transportadoraList = transportadoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpostal != null ? cpostal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codpostal)) {
            return false;
        }
        Codpostal other = (Codpostal) object;
        if ((this.cpostal == null && other.cpostal != null) || (this.cpostal != null && !this.cpostal.equals(other.cpostal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Codpostal[ cpostal=" + cpostal + " ]";
    }
    
}
