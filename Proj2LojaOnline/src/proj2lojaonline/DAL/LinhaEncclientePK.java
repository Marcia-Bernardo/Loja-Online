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
public class LinhaEncclientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CODENCCLIENTE")
    private int codenccliente;
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;

    public LinhaEncclientePK() {
    }

    public LinhaEncclientePK(int codenccliente, int codprod) {
        this.codenccliente = codenccliente;
        this.codprod = codprod;
    }

    public int getCodenccliente() {
        return codenccliente;
    }

    public void setCodenccliente(int codenccliente) {
        this.codenccliente = codenccliente;
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
        hash += (int) codenccliente;
        hash += (int) codprod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaEncclientePK)) {
            return false;
        }
        LinhaEncclientePK other = (LinhaEncclientePK) object;
        if (this.codenccliente != other.codenccliente) {
            return false;
        }
        if (this.codprod != other.codprod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proj2lojaonline.DAL.LinhaEncclientePK[ codenccliente=" + codenccliente + ", codprod=" + codprod + " ]";
    }
    
}
