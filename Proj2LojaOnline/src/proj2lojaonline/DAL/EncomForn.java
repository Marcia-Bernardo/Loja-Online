/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marci
 */
@Entity
@Table(name = "ENCOM_FORN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncomForn.findAll", query = "SELECT e FROM EncomForn e")
    , @NamedQuery(name = "EncomForn.findByCodencforn", query = "SELECT e FROM EncomForn e WHERE e.codencforn = :codencforn")
    , @NamedQuery(name = "EncomForn.findByValortot", query = "SELECT e FROM EncomForn e WHERE e.valortot = :valortot")
    , @NamedQuery(name = "EncomForn.findByPago", query = "SELECT e FROM EncomForn e WHERE e.pago = :pago")
    , @NamedQuery(name = "EncomForn.findByEstado", query = "SELECT e FROM EncomForn e WHERE e.estado = :estado")
    , @NamedQuery(name = "EncomForn.findByDatacompra", query = "SELECT e FROM EncomForn e WHERE e.datacompra = :datacompra")
    , @NamedQuery(name = "EncomForn.findByDataentr", query = "SELECT e FROM EncomForn e WHERE e.dataentr = :dataentr")})
public class EncomForn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODENCFORN")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codencforn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALORTOT")
    private BigDecimal valortot;
    @Basic(optional = false)
    @Column(name = "PAGO")
    private short pago;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    @Column(name = "DATACOMPRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacompra;
    @Column(name = "DATAENTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataentr;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "encomForn")
    private List<LinhaEncforn> linhaEncfornList;
    @JoinColumn(name = "CODFORN", referencedColumnName = "CODFORN")
    @ManyToOne(optional = false)
    private Fornecedor codforn;

    public EncomForn() {
    }

    public EncomForn(Integer codencforn) {
        this.codencforn = codencforn;
    }

    public EncomForn(Integer codencforn, BigDecimal valortot, short pago, short estado) {
        this.codencforn = codencforn;
        this.valortot = valortot;
        this.pago = pago;
        this.estado = estado;
    }

    public Integer getCodencforn() {
        return codencforn;
    }

    public void setCodencforn(Integer codencforn) {
        this.codencforn = codencforn;
    }

    public BigDecimal getValortot() {
        return valortot;
    }

    public void setValortot(BigDecimal valortot) {
        this.valortot = valortot;
    }

    public short getPago() {
        return pago;
    }

    public void setPago(short pago) {
        this.pago = pago;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getDatacompra() {
        return datacompra;
    }

    public void setDatacompra(Date datacompra) {
        this.datacompra = datacompra;
    }

    public Date getDataentr() {
        return dataentr;
    }

    public void setDataentr(Date dataentr) {
        this.dataentr = dataentr;
    }

    @XmlTransient
    public List<LinhaEncforn> getLinhaEncfornList() {
        return linhaEncfornList;
    }

    public void setLinhaEncfornList(List<LinhaEncforn> linhaEncfornList) {
        this.linhaEncfornList = linhaEncfornList;
    }

    public Fornecedor getCodforn() {
        return codforn;
    }

    public void setCodforn(Fornecedor codforn) {
        this.codforn = codforn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codencforn != null ? codencforn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncomForn)) {
            return false;
        }
        EncomForn other = (EncomForn) object;
        if ((this.codencforn == null && other.codencforn != null) || (this.codencforn != null && !this.codencforn.equals(other.codencforn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.EncomForn[ codencforn=" + codencforn + " ]";
    }
    
}
