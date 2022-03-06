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
@Table(name = "TAMANHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tamanho.findAll", query = "SELECT t FROM Tamanho t")
    , @NamedQuery(name = "Tamanho.findByCodtam", query = "SELECT t FROM Tamanho t WHERE t.codtam = :codtam")
    , @NamedQuery(name = "Tamanho.findByNome", query = "SELECT t FROM Tamanho t WHERE t.nome = :nome")})
public class Tamanho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTAM")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codtam;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "codtamanho")
    private List<Produto> produtoList;

    public Tamanho() {
    }

    public Tamanho(Integer codtam) {
        this.codtam = codtam;
    }

    public Tamanho(Integer codtam, String nome) {
        this.codtam = codtam;
        this.nome = nome;
    }

    public Integer getCodtam() {
        return codtam;
    }

    public void setCodtam(Integer codtam) {
        this.codtam = codtam;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtam != null ? codtam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tamanho)) {
            return false;
        }
        Tamanho other = (Tamanho) object;
        if ((this.codtam == null && other.codtam != null) || (this.codtam != null && !this.codtam.equals(other.codtam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Tamanho[ codtam=" + codtam + " ]";
    }
    
}
