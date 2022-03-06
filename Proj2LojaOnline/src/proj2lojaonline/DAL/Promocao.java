/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marci
 */
@Entity
@Table(name = "PROMOCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promocao.findAll", query = "SELECT p FROM Promocao p")
    , @NamedQuery(name = "Promocao.findByCodpromocao", query = "SELECT p FROM Promocao p WHERE p.codpromocao = :codpromocao")
    , @NamedQuery(name = "Promocao.findByDescricao", query = "SELECT p FROM Promocao p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Promocao.findByDataini", query = "SELECT p FROM Promocao p WHERE p.dataini = :dataini")
    , @NamedQuery(name = "Promocao.findByDatafim", query = "SELECT p FROM Promocao p WHERE p.datafim = :datafim")})
public class Promocao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPROMOCAO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codpromocao;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "DATAINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataini;
    @Basic(optional = false)
    @Column(name = "DATAFIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafim;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "promocao")
    private List<Prodprom> prodpromList;

    public Promocao() {
    }

    public Promocao(Integer codpromocao) {
        this.codpromocao = codpromocao;
    }

    public Promocao(Integer codpromocao, String descricao, Date dataini, Date datafim) {
        this.codpromocao = codpromocao;
        this.descricao = descricao;
        this.dataini = dataini;
        this.datafim = datafim;
    }

    public Integer getCodpromocao() {
        return codpromocao;
    }

    public void setCodpromocao(Integer codpromocao) {
        this.codpromocao = codpromocao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataini() {
        return dataini;
    }

    public void setDataini(Date dataini) {
        this.dataini = dataini;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    @XmlTransient
    public List<Prodprom> getProdpromList() {
        return prodpromList;
    }

    public void setProdpromList(List<Prodprom> prodpromList) {
        this.prodpromList = prodpromList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpromocao != null ? codpromocao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocao)) {
            return false;
        }
        Promocao other = (Promocao) object;
        if ((this.codpromocao == null && other.codpromocao != null) || (this.codpromocao != null && !this.codpromocao.equals(other.codpromocao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Promocao[ codpromocao=" + codpromocao + " ]";
    }
    
}
