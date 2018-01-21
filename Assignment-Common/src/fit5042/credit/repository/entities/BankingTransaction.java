
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository.entities;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;

/**
 *
 * @author shazeed ahsan
 */

@Entity

@NamedQueries({
    @NamedQuery(name = BankingTransaction.GET_ALL_QUERY_NAME, query = "SELECT p FROM BankingTransaction p")})
public class BankingTransaction implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "BankingTransaction.getAll";
    
    private int transactionNum;
    private String transactionName;
    private TransactionType transactionType;
    private String transactionDesc;
    private PublicUser transactionUser;

    public BankingTransaction(int transactionNum, String transactionName, TransactionType transactionType, String transactionDesc, PublicUser transactionUser) {
        this.transactionNum = transactionNum;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.transactionDesc = transactionDesc;
        this.transactionUser = transactionUser;
    }
    
    public BankingTransaction(){}

    @Id
    @SequenceGenerator(name="transSeq" ,initialValue=4, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transSeq")
    @Column(name = "transaction_id")
    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "transaction_type_id", nullable = false)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name ="transaction_user_id", nullable = false)
    public PublicUser getTransactionUser() {
        return transactionUser;
    }
    
    public void setTransactionUser(PublicUser transactionUser) {
        this.transactionUser = transactionUser;
    }

    @Override
    public String toString() {
        return "BankingTransaction{" + "transactionNum=" + transactionNum + ", transactionName=" + transactionName + ", transactionType=" + transactionType + ", transactionDesc=" + transactionDesc + ", transactionUser=" + transactionUser + '}';
    }
    
    
    
    
}
