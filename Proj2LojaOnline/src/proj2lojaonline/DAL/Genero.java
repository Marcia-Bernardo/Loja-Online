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
@Table(name = "GENERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g")
    , @NamedQuery(name = "Genero.findByCodgenero", query = "SELECT g FROM Genero g WHERE g.codgenero = :codgenero")
    , @NamedQuery(name = "Genero.findByNome", query = "SELECT g FROM Genero g WHERE g.nome = :nome")})
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODGENERO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer codgenero;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "codgenero")
    private List<Produto> produtoList;

    public Genero() {
    }

    public Genero(Integer codgenero) {
        this.codgenero = codgenero;
    }

    public Genero(Integer codgenero, String nome) {
        this.codgenero = codgenero;
        this.nome = nome;
    }

    public Integer getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(Integer codgenero) {
        this.codgenero = codgenero;
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
        hash += (codgenero != null ? codgenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.codgenero == null && other.codgenero != null) || (this.codgenero != null && !this.codgenero.equals(other.codgenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.Genero[ codgenero=" + codgenero + " ]";
    }
    
}
