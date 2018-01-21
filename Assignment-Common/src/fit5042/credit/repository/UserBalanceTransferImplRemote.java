/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingTransaction;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author shazeed ahsan
 */
@Remote
public interface UserBalanceTransferImplRemote {
    
    public void adjustBalance(double newBalance, int uid);
    
    public List<BankingTransaction> getUserTransactions(int uid);
    
}
