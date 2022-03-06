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
@Table(name = "MORADA_DE_ENTREGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoradaDeEntrega.findAll", query = "SELECT m FROM MoradaDeEntrega m")
    , @NamedQuery(name = "MoradaDeEntrega.findByCodmoradaentr", query = "SELECT m FROM MoradaDeEntrega m WHERE m.codmoradaentr = :codmoradaentr")
    , @NamedQuery(name = "MoradaDeEntrega.findByNome", query = "SELECT m FROM MoradaDeEntrega m WHERE m.nome = :nome")
    , @NamedQuery(name = "MoradaDeEntrega.findByMorada", query = "SELECT m FROM MoradaDeEntrega m WHERE m.morada = :morada")})
public class MoradaDeEntrega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODMORADAENTR")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codmoradaentr;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "MORADA")
    private String morada;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "codmoradaentr")
    private List<EncomCliente> encomClienteList;
    @JoinColumn(name = "CPOSTAL", referencedColumnName = "CPOSTAL")
    @ManyToOne(optional = false)
    private Codpostal cpostal;

    public MoradaDeEntrega() {
    }

    public MoradaDeEntrega(Integer codmoradaentr) {
        this.codmoradaentr = codmoradaentr;
    }

    public MoradaDeEntrega(Integer codmoradaentr, String nome, String morada) {
        this.codmoradaentr = codmoradaentr;
        this.nome = nome;
        this.morada = morada;
    }

    public Integer getCodmoradaentr() {
        return codmoradaentr;
    }

    public void setCodmoradaentr(Integer codmoradaentr) {
        this.codmoradaentr = codmoradaentr;
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
        hash += (codmoradaentr != null ? codmoradaentr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoradaDeEntrega)) {
            return false;
        }
        MoradaDeEntrega other = (MoradaDeEntrega) object;
        if ((this.codmoradaentr == null && other.codmoradaentr != null) || (this.codmoradaentr != null && !this.codmoradaentr.equals(other.codmoradaentr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.MoradaDeEntrega[ codmoradaentr=" + codmoradaentr + " ]";
    }
    
}
