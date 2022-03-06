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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "LINHA_ENCFORN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinhaEncforn.findAll", query = "SELECT l FROM LinhaEncforn l")
    , @NamedQuery(name = "LinhaEncforn.findByCodencforn", query = "SELECT l FROM LinhaEncforn l WHERE l.linhaEncfornPK.codencforn = :codencforn")
    , @NamedQuery(name = "LinhaEncforn.findByCodprod", query = "SELECT l FROM LinhaEncforn l WHERE l.linhaEncfornPK.codprod = :codprod")
    , @NamedQuery(name = "LinhaEncforn.findByQtd", query = "SELECT l FROM LinhaEncforn l WHERE l.qtd = :qtd")
    , @NamedQuery(name = "LinhaEncforn.findByPrecounit", query = "SELECT l FROM LinhaEncforn l WHERE l.precounit = :precounit")})
public class LinhaEncforn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhaEncfornPK linhaEncfornPK;
    @Basic(optional = false)
    @Column(name = "QTD")
    private int qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRECOUNIT")
    private BigDecimal precounit;
    @JoinColumn(name = "CODENCFORN", referencedColumnName = "CODENCFORN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EncomForn encomForn;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public LinhaEncforn() {
    }

    public LinhaEncforn(LinhaEncfornPK linhaEncfornPK) {
        this.linhaEncfornPK = linhaEncfornPK;
    }

    public LinhaEncforn(LinhaEncfornPK linhaEncfornPK, int qtd, BigDecimal precounit) {
        this.linhaEncfornPK = linhaEncfornPK;
        this.qtd = qtd;
        this.precounit = precounit;
    }

    public LinhaEncforn(int codencforn, int codprod) {
        this.linhaEncfornPK = new LinhaEncfornPK(codencforn, codprod);
    }

    public LinhaEncfornPK getLinhaEncfornPK() {
        return linhaEncfornPK;
    }

    public void setLinhaEncfornPK(LinhaEncfornPK linhaEncfornPK) {
        this.linhaEncfornPK = linhaEncfornPK;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getPrecounit() {
        return precounit;
    }

    public void setPrecounit(BigDecimal precounit) {
        this.precounit = precounit;
    }

    public EncomForn getEncomForn() {
        return encomForn;
    }

    public void setEncomForn(EncomForn encomForn) {
        this.encomForn = encomForn;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linhaEncfornPK != null ? linhaEncfornPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaEncforn)) {
            return false;
        }
        LinhaEncforn other = (LinhaEncforn) object;
        if ((this.linhaEncfornPK == null && other.linhaEncfornPK != null) || (this.linhaEncfornPK != null && !this.linhaEncfornPK.equals(other.linhaEncfornPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.LinhaEncforn[ linhaEncfornPK=" + linhaEncfornPK + " ]";
    }
    
}
