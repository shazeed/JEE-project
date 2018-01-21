/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author shazeed ahsan
 */
@Entity
@DiscriminatorValue("public")
public class PublicUser extends BankingUser implements Serializable {

    private List<BankingTransaction> transactions;
    
    public PublicUser( int id_number, String lastName, String firstName, String email, String password, String userType){
        super(id_number, lastName,firstName,email,password,userType);
        this.transactions = new ArrayList<>();
    }

    public PublicUser(){}
    
    @OneToMany(mappedBy="transactionUser")
    public List<BankingTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BankingTransaction> transactions) {
        this.transactions = transactions;
    }
    
    
    
}
