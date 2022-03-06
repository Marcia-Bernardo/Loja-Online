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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TRANSPORTADORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportadora.findAll", query = "SELECT t FROM Transportadora t")
    , @NamedQuery(name = "Transportadora.findByCodtransp", query = "SELECT t FROM Transportadora t WHERE t.codtransp = :codtransp")
    , @NamedQuery(name = "Transportadora.findByNome", query = "SELECT t FROM Transportadora t WHERE t.nome = :nome")
    , @NamedQuery(name = "Transportadora.findByMorada", query = "SELECT t FROM Transportadora t WHERE t.morada = :morada")
    , @NamedQuery(name = "Transportadora.findByTelefone", query = "SELECT t FROM Transportadora t WHERE t.telefone = :telefone")
    , @NamedQuery(name = "Transportadora.findByEmail", query = "SELECT t FROM Transportadora t WHERE t.email = :email")
    , @NamedQuery(name = "Transportadora.findByNipc", query = "SELECT t FROM Transportadora t WHERE t.nipc = :nipc")
    , @NamedQuery(name = "Transportadora.findByAtivo", query = "SELECT t FROM Transportadora t WHERE t.ativo = :ativo")})
public class Transportadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTRANSP")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codtransp;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "MORADA")
    private String morada;
    @Basic(optional = false)
    @Column(name = "TELEFONE")
    private String telefone;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "NIPC")
    private String nipc;
    @Basic(optional = false)
    @Column(name = "ATIVO")
    private short ativo;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "codtransp")
    private List<EncomCliente> encomClienteList;
    @JoinColumn(name = "CPOSTAL", referencedColumnName = "CPOSTAL")
    @ManyToOne(optional = false)
    private Codpostal cpostal;

    public Transportadora() {
    }

    public Transportadora(Integer codtransp) {
        this.codtransp = codtransp;
    }

    public Transportadora(Integer codtransp, String nome, String morada, String telefone, String email, String nipc, short ativo) {
        this.codtransp = codtransp;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.nipc = nipc;
        this.ativo = ativo;
    }

    public Integer getCodtransp() {
        return codtransp;
    }

    public void setCodtransp(Integer codtransp) {
        this.codtransp = codtransp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNipc() {
        return nipc;
    }

    public void setNipc(String nipc) {
        this.nipc = nipc;
    }

    public short getAtivo() {
        return ativo;
    }

    public void setAtivo(short ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<EncomCliente> getEncomClienteList() {
        return encomClienteList;
    }

    public void setEncomClienteList(List<EncomCliente> encomClienteList) {
        this.encomClienteList = encomClienteList;
    }

    public Codpostal getCpostal() {
        return cpostal;
    }

    public void setCpostal(Codpostal cpostal) {
        this.cpostal = cpostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtransp != null ? codtransp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transportadora)) {
            return false;
        }
        Transportadora other = (Transportadora) object;
        if ((this.codtransp == null && other.codtransp != null) || (this.codtransp != null && !this.codtransp.equals(other.codtransp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Transportadora[ codtransp=" + codtransp + " ]";
    }
    
}
