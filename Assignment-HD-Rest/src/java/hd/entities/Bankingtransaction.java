/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shazeed ahsan
 */
@Entity
@Table(name = "BANKINGTRANSACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bankingtransaction.findAll", query = "SELECT b FROM Bankingtransaction b")
    , @NamedQuery(name = "Bankingtransaction.findByTransactionId", query = "SELECT b FROM Bankingtransaction b WHERE b.transactionId = :transactionId")
    , @NamedQuery(name = "Bankingtransaction.findByTransactiondesc", query = "SELECT b FROM Bankingtransaction b WHERE b.transactiondesc = :transactiondesc")
    , @NamedQuery(name = "Bankingtransaction.findByTransactionname", query = "SELECT b FROM Bankingtransaction b WHERE b.transactionname = :transactionname")})
public class Bankingtransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Size(max = 255)
    @Column(name = "TRANSACTIONDESC")
    private String transactiondesc;
    @Size(max = 255)
    @Column(name = "TRANSACTIONNAME")
    private String transactionname;
    @JoinColumn(name = "TRANSACTION_USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Bankinguser transactionUserId;
    @JoinColumn(name = "TRANSACTION_TYPE_ID", referencedColumnName = "TYPEID")
    @ManyToOne(optional = false)
    private Transactiontype transactionTypeId;

    public Bankingtransaction() {
    }

    public Bankingtransaction(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactiondesc() {
        return transactiondesc;
    }

    public void setTransactiondesc(String transactiondesc) {
        this.transactiondesc = transactiondesc;
    }

    public String getTransactionname() {
        return transactionname;
    }

    public void setTransactionname(String transactionname) {
        this.transactionname = transactionname;
    }

    public Bankinguser getTransactionUserId() {
        return transactionUserId;
    }

    public void setTransactionUserId(Bankinguser transactionUserId) {
        this.transactionUserId = transactionUserId;
    }

    public Transactiontype getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Transactiontype transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bankingtransaction)) {
            return false;
        }
        Bankingtransaction other = (Bankingtransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entities.Bankingtransaction[ transactionId=" + transactionId + " ]";
    }
    
}
