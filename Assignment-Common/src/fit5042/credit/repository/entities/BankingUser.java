/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author shazeed ahsan
 */

@Entity(name="BankingUser")
@Table(name="BankingUser")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BANKUSER_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("admin")
@NamedQueries({@NamedQuery(name = BankingUser.GET_ALL_QUERY_NAME, query="SELECT u from BankingUser u"),
        @NamedQuery(name = BankingUser.GET_SELECTED_NAME, query="SELECT u FROM BankingUser u "+
                   " WHERE u.id_number = :userId OR u.firstName LIKE :fname OR u.lastName LIKE :lName "
                + "OR u.email LIKE :userEmail OR u.userType =:uType")
})

public class BankingUser implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "BankingUser.getAll";
    public static final String GET_SELECTED_NAME = "BankingUser.getSelectedUser";
    
    private int id_number;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String userType;
    
    private Address address;
    private String phoneNumber;
    private double balance;
    
    
    public BankingUser(int id_number, String lastName, String firstName, String email, String password, String userType, Address address, String phoneNumber,List<BankingTransaction> transactions) {
        this.id_number = id_number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }
    
    public BankingUser(int id_number, String lastName, String firstName, String email, String password, String userType) {
        this.id_number = id_number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = new Address();
        this.phoneNumber = "";
        this.balance = 0.0;
    }
    
    public BankingUser(int id_number, String lastName, String firstName, String email, String password, String userType, Address address, String phoneNumber) {
        this.id_number = id_number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }
    
    public BankingUser(){}
    
    @Id
    
    // @SequenceGenerator(name="userSeq" ,initialValue=10, allocationSize=1)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSeq")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    public int getId_number() {
        return id_number;
    }

    public void setId_number(int id_number) {
        this.id_number = id_number;
    }

    @Pattern(regexp = "^[a-zA-Z]+$")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Pattern(regexp = "^[a-zA-Z]+$")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    

    @Min(0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    

    @Override
    public String toString() {
        return "BankingUser{" + "id_number=" + id_number + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", userType=" + userType + ", address=" + address + ", phoneNumber=" + phoneNumber +  "}";
    }
    
    
    
    
    
    
    
}
