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
public class LinhaEncfornPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CODENCFORN")
    private int codencforn;
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;

    public LinhaEncfornPK() {
    }

    public LinhaEncfornPK(int codencforn, int codprod) {
        this.codencforn = codencforn;
        this.codprod = codprod;
    }

    public int getCodencforn() {
        return codencforn;
    }

    public void setCodencforn(int codencforn) {
        this.codencforn = codencforn;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codencforn;
        hash += (int) codprod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaEncfornPK)) {
            return false;
        }
        LinhaEncfornPK other = (LinhaEncfornPK) object;
        if (this.codencforn != other.codencforn) {
            return false;
        }
        if (this.codprod != other.codprod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.LinhaEncfornPK[ codencforn=" + codencforn + ", codprod=" + codprod + " ]";
    }
    
}
