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
@Table(name = "ENCOM_CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncomCliente.findAll", query = "SELECT e FROM EncomCliente e")
    , @NamedQuery(name = "EncomCliente.findByCodenccliente", query = "SELECT e FROM EncomCliente e WHERE e.codenccliente = :codenccliente")
    , @NamedQuery(name = "EncomCliente.findByValortransporte", query = "SELECT e FROM EncomCliente e WHERE e.valortransporte = :valortransporte")
    , @NamedQuery(name = "EncomCliente.findByValortot", query = "SELECT e FROM EncomCliente e WHERE e.valortot = :valortot")
    , @NamedQuery(name = "EncomCliente.findByPago", query = "SELECT e FROM EncomCliente e WHERE e.pago = :pago")
    , @NamedQuery(name = "EncomCliente.findByEstado", query = "SELECT e FROM EncomCliente e WHERE e.estado = :estado")
    , @NamedQuery(name = "EncomCliente.findByValorencom", query = "SELECT e FROM EncomCliente e WHERE e.valorencom = :valorencom")
    , @NamedQuery(name = "EncomCliente.findByDatacompra", query = "SELECT e FROM EncomCliente e WHERE e.datacompra = :datacompra")
    , @NamedQuery(name = "EncomCliente.findByDataentr", query = "SELECT e FROM EncomCliente e WHERE e.dataentr = :dataentr")})
public class EncomCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODENCCLIENTE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codenccliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALORTRANSPORTE")
    private BigDecimal valortransporte;
    @Basic(optional = false)
    @Column(name = "VALORTOT")
    private BigDecimal valortot;
    @Basic(optional = false)
    @Column(name = "PAGO")
    private short pago;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private short estado;
    @Basic(optional = false)
    @Column(name = "VALORENCOM")
    private BigDecimal valorencom;
    @Column(name = "DATACOMPRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacompra;
    @Column(name = "DATAENTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataentr;
    @JoinColumn(name = "CODCLIENTE", referencedColumnName = "CODCLIENTE")
    @ManyToOne(optional = false)
    private Cliente codcliente;
    @JoinColumn(name = "CODMORADAENTR", referencedColumnName = "CODMORADAENTR")
    @ManyToOne(optional = false)
    private MoradaDeEntrega codmoradaentr;
    @JoinColumn(name = "CODTRANSP", referencedColumnName = "CODTRANSP")
    @ManyToOne(optional = false)
    private Transportadora codtransp;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "encomCliente")
    private List<LinhaEnccliente> linhaEncclienteList;

    public EncomCliente() {
    }

    public EncomCliente(Integer codenccliente) {
        this.codenccliente = codenccliente;
    }

    public EncomCliente(Integer codenccliente, BigDecimal valortransporte, BigDecimal valortot, short pago, short estado, BigDecimal valorencom) {
        this.codenccliente = codenccliente;
        this.valortransporte = valortransporte;
        this.valortot = valortot;
        this.pago = pago;
        this.estado = estado;
        this.valorencom = valorencom;
    }

    public Integer getCodenccliente() {
        return codenccliente;
    }

    public void setCodenccliente(Integer codenccliente) {
        this.codenccliente = codenccliente;
    }

    public BigDecimal getValortransporte() {
        return valortransporte;
    }

    public void setValortransporte(BigDecimal valortransporte) {
        this.valortransporte = valortransporte;
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

    public BigDecimal getValorencom() {
        return valorencom;
    }

    public void setValorencom(BigDecimal valorencom) {
        this.valorencom = valorencom;
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

    public Cliente getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Cliente codcliente) {
        this.codcliente = codcliente;
    }

    public MoradaDeEntrega getCodmoradaentr() {
        return codmoradaentr;
    }

    public void setCodmoradaentr(MoradaDeEntrega codmoradaentr) {
        this.codmoradaentr = codmoradaentr;
    }

    public Transportadora getCodtransp() {
        return codtransp;
    }

    public void setCodtransp(Transportadora codtransp) {
        this.codtransp = codtransp;
    }

    @XmlTransient
    public List<LinhaEnccliente> getLinhaEncclienteList() {
        return linhaEncclienteList;
    }

    public void setLinhaEncclienteList(List<LinhaEnccliente> linhaEncclienteList) {
        this.linhaEncclienteList = linhaEncclienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codenccliente != null ? codenccliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncomCliente)) {
            return false;
        }
        EncomCliente other = (EncomCliente) object;
        if ((this.codenccliente == null && other.codenccliente != null) || (this.codenccliente != null && !this.codenccliente.equals(other.codenccliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.EncomCliente[ codenccliente=" + codenccliente + " ]";
    }
    
}
