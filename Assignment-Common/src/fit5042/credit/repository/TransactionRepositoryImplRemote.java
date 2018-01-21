/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.TransactionType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author shazeed ahsan
 */
@Remote
public interface TransactionRepositoryImplRemote {
    
    public void addTransaction(BankingTransaction transaction) throws Exception;
    
    public void removeTransaction(BankingTransaction transaction) throws Exception;
    
    public TransactionType getTransactionType(int typeId) throws Exception;
    
    public List<BankingTransaction> getAllTransactions() throws Exception;
    
    public List<TransactionType> getAllTypes() throws Exception;
    
    public BankingTransaction searchTransactionById(int transactionId) throws Exception;
    
    public List<BankingTransaction> searchTransactionsByType(int typeId) throws Exception;
    
    public List<BankingTransaction> searchTransactionsByName(String Name) throws Exception;
    
}
