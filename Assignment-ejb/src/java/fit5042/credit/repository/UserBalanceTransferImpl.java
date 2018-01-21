/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shazeed ahsan
 */
@Stateless
public class UserBalanceTransferImpl implements UserBalanceTransferImplRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public void adjustBalance(double newBalance, int uid){
        PublicUser bankuser = entityManager.find(PublicUser.class, uid);
        bankuser.setBalance(newBalance);
        //entityManager.merge(bankuser);
        
    }
    
    @Override
    public List<BankingTransaction> getUserTransactions(int uid){
        PublicUser bankuser = entityManager.find(PublicUser.class, uid);
        bankuser.getTransactions();
        bankuser.getTransactions().size();
        entityManager.refresh(bankuser);
        return bankuser.getTransactions(); 
    }
}
