/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj2lojaonline.DAL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marci
 */
@Embeddable
public class ProdpromPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;
    @Basic(optional = false)
    @Column(name = "CODPROMOCAO")
    private int codpromocao;

    public ProdpromPK() {
    }

    public ProdpromPK(int codprod, int codpromocao) {
        this.codprod = codprod;
        this.codpromocao = codpromocao;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
    }

    public int getCodpromocao() {
        return codpromocao;
    }

    public void setCodpromocao(int codpromocao) {
        this.codpromocao = codpromocao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codprod;
        hash += (int) codpromocao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdpromPK)) {
            return false;
        }
        ProdpromPK other = (ProdpromPK) object;
        if (this.codprod != other.codprod) {
            return false;
        }
        if (this.codpromocao != other.codpromocao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.ProdpromPK[ codprod=" + codprod + ", codpromocao=" + codpromocao + " ]";
    }
    
}
