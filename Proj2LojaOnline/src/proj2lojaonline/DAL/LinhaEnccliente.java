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
@Table(name = "LINHA_ENCCLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LinhaEnccliente.findAll", query = "SELECT l FROM LinhaEnccliente l")
    , @NamedQuery(name = "LinhaEnccliente.findByCodenccliente", query = "SELECT l FROM LinhaEnccliente l WHERE l.linhaEncclientePK.codenccliente = :codenccliente")
    , @NamedQuery(name = "LinhaEnccliente.findByCodprod", query = "SELECT l FROM LinhaEnccliente l WHERE l.linhaEncclientePK.codprod = :codprod")
    , @NamedQuery(name = "LinhaEnccliente.findByQtd", query = "SELECT l FROM LinhaEnccliente l WHERE l.qtd = :qtd")
    , @NamedQuery(name = "LinhaEnccliente.findByPrecounit", query = "SELECT l FROM LinhaEnccliente l WHERE l.precounit = :precounit")})
public class LinhaEnccliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhaEncclientePK linhaEncclientePK;
    @Basic(optional = false)
    @Column(name = "QTD")
    private int qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRECOUNIT")
    private BigDecimal precounit;
    @JoinColumn(name = "CODENCCLIENTE", referencedColumnName = "CODENCCLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EncomCliente encomCliente;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public LinhaEnccliente() {
    }

    public LinhaEnccliente(LinhaEncclientePK linhaEncclientePK) {
        this.linhaEncclientePK = linhaEncclientePK;
    }

    public LinhaEnccliente(LinhaEncclientePK linhaEncclientePK, int qtd, BigDecimal precounit) {
        this.linhaEncclientePK = linhaEncclientePK;
        this.qtd = qtd;
        this.precounit = precounit;
    }

    public LinhaEnccliente(int codenccliente, int codprod) {
        this.linhaEncclientePK = new LinhaEncclientePK(codenccliente, codprod);
    }

    public LinhaEncclientePK getLinhaEncclientePK() {
        return linhaEncclientePK;
    }

    public void setLinhaEncclientePK(LinhaEncclientePK linhaEncclientePK) {
        this.linhaEncclientePK = linhaEncclientePK;
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

    public EncomCliente getEncomCliente() {
        return encomCliente;
    }

    public void setEncomCliente(EncomCliente encomCliente) {
        this.encomCliente = encomCliente;
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
        hash += (linhaEncclientePK != null ? linhaEncclientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaEnccliente)) {
            return false;
        }
        LinhaEnccliente other = (LinhaEnccliente) object;
        if ((this.linhaEncclientePK == null && other.linhaEncclientePK != null) || (this.linhaEncclientePK != null && !this.linhaEncclientePK.equals(other.linhaEncclientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.LinhaEnccliente[ linhaEncclientePK=" + linhaEncclientePK + " ]";
    }
    
}
