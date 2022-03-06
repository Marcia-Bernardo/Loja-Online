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
@Table(name = "FORNECEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
    , @NamedQuery(name = "Fornecedor.findByCodforn", query = "SELECT f FROM Fornecedor f WHERE f.codforn = :codforn")
    , @NamedQuery(name = "Fornecedor.findByNome", query = "SELECT f FROM Fornecedor f WHERE f.nome = :nome")
    , @NamedQuery(name = "Fornecedor.findByTelefone", query = "SELECT f FROM Fornecedor f WHERE f.telefone = :telefone")
    , @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email")
    , @NamedQuery(name = "Fornecedor.findByNipc", query = "SELECT f FROM Fornecedor f WHERE f.nipc = :nipc")
    , @NamedQuery(name = "Fornecedor.findByAtivo", query = "SELECT f FROM Fornecedor f WHERE f.ativo = :ativo")
    , @NamedQuery(name = "Fornecedor.findByMorada", query = "SELECT f FROM Fornecedor f WHERE f.morada = :morada")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFORN")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codforn;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
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
    @Basic(optional = false)
    @Column(name = "MORADA")
    private String morada;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "codforn")
    private List<EncomForn> encomFornList;
    @JoinColumn(name = "CPOSTAL", referencedColumnName = "CPOSTAL")
    @ManyToOne(optional = false)
    private Codpostal cpostal;

    public Fornecedor() {
    }

    public Fornecedor(Integer codforn) {
        this.codforn = codforn;
    }

    public Fornecedor(Integer codforn, String nome, String telefone, String email, String nipc, short ativo, String morada) {
        this.codforn = codforn;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.nipc = nipc;
        this.ativo = ativo;
        this.morada = morada;
    }

    public Integer getCodforn() {
        return codforn;
    }

    public void setCodforn(Integer codforn) {
        this.codforn = codforn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    @XmlTransient
    public List<EncomForn> getEncomFornList() {
        return encomFornList;
    }

    public void setEncomFornList(List<EncomForn> encomFornList) {
        this.encomFornList = encomFornList;
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
        hash += (codforn != null ? codforn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.codforn == null && other.codforn != null) || (this.codforn != null && !this.codforn.equals(other.codforn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Fornecedor[ codforn=" + codforn + " ]";
    }
    
}
