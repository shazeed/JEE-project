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
@Table(name = "BANKINGUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bankinguser.findAll", query = "SELECT b FROM Bankinguser b")
    , @NamedQuery(name = "Bankinguser.findByUserId", query = "SELECT b FROM Bankinguser b WHERE b.userId = :userId")
    , @NamedQuery(name = "Bankinguser.findByEmail", query = "SELECT b FROM Bankinguser b WHERE b.email = :email")
    , @NamedQuery(name = "Bankinguser.findByFirstname", query = "SELECT b FROM Bankinguser b WHERE b.firstname = :firstname")
    , @NamedQuery(name = "Bankinguser.findByLastname", query = "SELECT b FROM Bankinguser b WHERE b.lastname = :lastname")
    , @NamedQuery(name = "Bankinguser.findByPassword", query = "SELECT b FROM Bankinguser b WHERE b.password = :password")
    , @NamedQuery(name = "Bankinguser.findByPhonenumber", query = "SELECT b FROM Bankinguser b WHERE b.phonenumber = :phonenumber")
    , @NamedQuery(name = "Bankinguser.findByUsertype", query = "SELECT b FROM Bankinguser b WHERE b.usertype = :usertype")
    , @NamedQuery(name = "Bankinguser.findByPostcode", query = "SELECT b FROM Bankinguser b WHERE b.postcode = :postcode")
    , @NamedQuery(name = "Bankinguser.findByState", query = "SELECT b FROM Bankinguser b WHERE b.state = :state")
    , @NamedQuery(name = "Bankinguser.findByStreetaddress", query = "SELECT b FROM Bankinguser b WHERE b.streetaddress = :streetaddress")
    , @NamedQuery(name = "Bankinguser.findByStreetnumber", query = "SELECT b FROM Bankinguser b WHERE b.streetnumber = :streetnumber")
    , @NamedQuery(name = "Bankinguser.findBySuburb", query = "SELECT b FROM Bankinguser b WHERE b.suburb = :suburb")
    , @NamedQuery(name = "Bankinguser.findByBalance", query = "SELECT b FROM Bankinguser b WHERE b.balance = :balance")})
public class Bankinguser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "PHONENUMBER")
    private String phonenumber;
    @Size(max = 255)
    @Column(name = "USERTYPE")
    private String usertype;
    @Size(max = 255)
    @Column(name = "POSTCODE")
    private String postcode;
    @Size(max = 255)
    @Column(name = "STATE")
    private String state;
    @Size(max = 255)
    @Column(name = "STREETADDRESS")
    private String streetaddress;
    @Size(max = 255)
    @Column(name = "STREETNUMBER")
    private String streetnumber;
    @Size(max = 255)
    @Column(name = "SUBURB")
    private String suburb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALANCE")
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionUserId")
    private Collection<Bankingtransaction> bankingtransactionCollection;

    public Bankinguser() {
    }

    public Bankinguser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bankinguser)) {
            return false;
        }
        Bankinguser other = (Bankinguser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hd.entities.Bankinguser[ userId=" + userId + " ]";
    }
    
}
