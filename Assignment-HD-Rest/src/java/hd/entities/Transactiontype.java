/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shazeed ahsan
 */
@Entity
@Table(name = "TRANSACTIONTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactiontype.findAll", query = "SELECT t FROM Transactiontype t")
    , @NamedQuery(name = "Transactiontype.findByTypeid", query = "SELECT t FROM Transactiontype t WHERE t.typeid = :typeid")
    , @NamedQuery(name = "Transactiontype.findByTypename", query = "SELECT t FROM Transactiontype t WHERE t.typename = :typename")})
public class Transactiontype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPEID")
    private Integer typeid;
    @Size(max = 255)
    @Column(name = "TYPENAME")
    private String typename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionTypeId")
    private Collection<Bankingtransaction> bankingtransactionCollection;

    public Transactiontype() {
    }

    public Transactiontype(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @XmlTransient
    public Collection<Bankingtransaction> getBankingtransactionCollection() {
        return bankingtransactionCollection;
    }

    public void setBankingtransactionCollection(Collection<Bankingtransaction> bankingtransactionCollection) {
        this.bankingtransactionCollection = bankingtransactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactiontype)) {
            return false;
        }
        Transactiontype other = (Transactiontype) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entities.Transactiontype[ typeid=" + typeid + " ]";
    }
    
}
