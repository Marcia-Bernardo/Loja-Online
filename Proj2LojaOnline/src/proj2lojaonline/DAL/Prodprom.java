/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marci
 */
@Entity
@Table(name = "PRODPROM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prodprom.findAll", query = "SELECT p FROM Prodprom p")
    , @NamedQuery(name = "Prodprom.findByCodprod", query = "SELECT p FROM Prodprom p WHERE p.prodpromPK.codprod = :codprod")
    , @NamedQuery(name = "Prodprom.findByCodpromocao", query = "SELECT p FROM Prodprom p WHERE p.prodpromPK.codpromocao = :codpromocao")
    , @NamedQuery(name = "Prodprom.findByPercdesc", query = "SELECT p FROM Prodprom p WHERE p.percdesc = :percdesc")})
public class Prodprom implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProdpromPK prodpromPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PERCDESC")
    private BigDecimal percdesc;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "CODPROMOCAO", referencedColumnName = "CODPROMOCAO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promocao promocao;

    public Prodprom() {
    }

    public Prodprom(ProdpromPK prodpromPK) {
        this.prodpromPK = prodpromPK;
    }

    public Prodprom(ProdpromPK prodpromPK, BigDecimal percdesc) {
        this.prodpromPK = prodpromPK;
        this.percdesc = percdesc;
    }

    public Prodprom(int codprod, int codpromocao) {
        this.prodpromPK = new ProdpromPK(codprod, codpromocao);
    }

    public ProdpromPK getProdpromPK() {
        return prodpromPK;
    }

    public void setProdpromPK(ProdpromPK prodpromPK) {
        this.prodpromPK = prodpromPK;
    }

    public BigDecimal getPercdesc() {
        return percdesc;
    }

    public void setPercdesc(BigDecimal percdesc) {
        this.percdesc = percdesc;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodpromPK != null ? prodpromPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodprom)) {
            return false;
        }
        Prodprom other = (Prodprom) object;
        if ((this.prodpromPK == null && other.prodpromPK != null) || (this.prodpromPK != null && !this.prodpromPK.equals(other.prodpromPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Prodprom[ prodpromPK=" + prodpromPK + " ]";
    }
    
}
