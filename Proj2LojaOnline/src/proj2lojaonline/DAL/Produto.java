/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PRODUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findByCodprod", query = "SELECT p FROM Produto p WHERE p.codprod = :codprod")
    , @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.nome = :nome")
    , @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Produto.findByPrecovenda", query = "SELECT p FROM Produto p WHERE p.precovenda = :precovenda")
    , @NamedQuery(name = "Produto.findByAtivo", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo")
    , @NamedQuery(name = "Produto.findByQtdstock", query = "SELECT p FROM Produto p WHERE p.qtdstock = :qtdstock")
    , @NamedQuery(name = "Produto.findByAlarmestock", query = "SELECT p FROM Produto p WHERE p.alarmestock = :alarmestock")
    , @NamedQuery(name = "Produto.findByPrecoatualvenda", query = "SELECT p FROM Produto p WHERE p.precoatualvenda = :precoatualvenda")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPROD")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codprod;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRECOVENDA")
    private BigDecimal precovenda;
    @Basic(optional = false)
    @Column(name = "ATIVO")
    private short ativo;
    @Basic(optional = false)
    @Column(name = "QTDSTOCK")
    private int qtdstock;
    @Basic(optional = false)
    @Column(name = "ALARMESTOCK")
    private short alarmestock;
    @Basic(optional = false)
    @Column(name = "PRECOATUALVENDA")
    private BigDecimal precoatualvenda;
    @JoinColumn(name = "CODCOR", referencedColumnName = "CODCOR") 
    @ManyToOne(optional = false)
    private Cor codcor;
    @JoinColumn(name = "CODGENERO", referencedColumnName = "CODGENERO")
    @ManyToOne(optional = false)
    private Genero codgenero;
    @JoinColumn(name = "CODTAMANHO", referencedColumnName = "CODTAM")
    @ManyToOne(optional = false)
    private Tamanho codtamanho;
    @JoinColumn(name = "CODTIPO", referencedColumnName = "CODTIPO")
    @ManyToOne(optional = false)
    private Tipo codtipo;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produto")
    private List<LinhaEnccliente> linhaEncclienteList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produto")
    private List<LinhaEncforn> linhaEncfornList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produto")
    private List<Prodprom> prodpromList;

    public Produto() {
    }

    public Produto(Integer codprod) {
        this.codprod = codprod;
    }

    public Produto(Integer codprod, String nome, String descricao, BigDecimal precovenda, short ativo, int qtdstock, short alarmestock, BigDecimal precoatualvenda) {
        this.codprod = codprod;
        this.nome = nome;
        this.descricao = descricao;
        this.precovenda = precovenda;
        this.ativo = ativo;
        this.qtdstock = qtdstock;
        this.alarmestock = alarmestock;
        this.precoatualvenda = precoatualvenda;
    }

    public Integer getCodprod() {
        return codprod;
    }

    public void setCodprod(Integer codprod) {
        this.codprod = codprod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecovenda() {
        return precovenda;
    }

    public void setPrecovenda(BigDecimal precovenda) {
        this.precovenda = precovenda;
    }

    public short getAtivo() {
        return ativo;
    }

    public void setAtivo(short ativo) {
        this.ativo = ativo;
    }

    public int getQtdstock() {
        return qtdstock;
    }

    public void setQtdstock(int qtdstock) {
        this.qtdstock = qtdstock;
    }

    public short getAlarmestock() {
        return alarmestock;
    }

    public void setAlarmestock(short alarmestock) {
        this.alarmestock = alarmestock;
    }

    public BigDecimal getPrecoatualvenda() {
        return precoatualvenda;
    }

    public void setPrecoatualvenda(BigDecimal precoatualvenda) {
        this.precoatualvenda = precoatualvenda;
    }

    public Cor getCodcor() {
        return codcor;
    }

    public void setCodcor(Cor codcor) {
        this.codcor = codcor;
    }

    public Genero getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(Genero codgenero) {
        this.codgenero = codgenero;
    }

    public Tamanho getCodtamanho() {
        return codtamanho;
    }

    public void setCodtamanho(Tamanho codtamanho) {
        this.codtamanho = codtamanho;
    }

    public Tipo getCodtipo() {
        return codtipo;
    }

    public void setCodtipo(Tipo codtipo) {
        this.codtipo = codtipo;
    }

    @XmlTransient
    public List<LinhaEnccliente> getLinhaEncclienteList() {
        return linhaEncclienteList;
    }

    public void setLinhaEncclienteList(List<LinhaEnccliente> linhaEncclienteList) {
        this.linhaEncclienteList = linhaEncclienteList;
    }

    @XmlTransient
    public List<LinhaEncforn> getLinhaEncfornList() {
        return linhaEncfornList;
    }

    public void setLinhaEncfornList(List<LinhaEncforn> linhaEncfornList) {
        this.linhaEncfornList = linhaEncfornList;
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
        hash += (codprod != null ? codprod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.codprod == null && other.codprod != null) || (this.codprod != null && !this.codprod.equals(other.codprod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Produto[ codprod=" + codprod + " ]";
    }
    
}
