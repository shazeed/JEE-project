/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author shazeed ahsan
 */
@Entity
@NamedQueries({@NamedQuery(name = TransactionType.GET_ALL_QUERY_NAME, query="SELECT tt from TransactionType tt")})
public class TransactionType implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "Transaction.getAll";
    private int typeId;
    private String typeName;
    private List<BankingTransaction> typeBasedTransactions;

    public TransactionType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeBasedTransactions = new ArrayList<>();
    }
    
    public TransactionType(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @OneToMany(mappedBy = "transactionType")
    public List<BankingTransaction> getTypeBasedTransactions() {
        return typeBasedTransactions;
    }

    public void setTypeBasedTransactions(List<BankingTransaction> typeBasedTransactions) {
        this.typeBasedTransactions = typeBasedTransactions;
    }
    
    
    
}
