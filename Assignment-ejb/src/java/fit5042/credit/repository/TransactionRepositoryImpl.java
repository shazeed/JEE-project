/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.TransactionType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author shazeed ahsan
 */
@Stateless
public class TransactionRepositoryImpl implements TransactionRepositoryImplRemote {

    @PersistenceContext
    private EntityManager entityManager;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addTransaction(BankingTransaction transaction) throws Exception {
        entityManager.persist(transaction);
    }

    @Override
    public void removeTransaction(BankingTransaction transaction) throws Exception{
        entityManager.remove(transaction);
    }
    
    @Override
    public TransactionType getTransactionType(int typeId) throws Exception{
        return entityManager.find(TransactionType.class, typeId);
    }
    
    @Override
    public List<BankingTransaction> getAllTransactions() throws Exception {
        return entityManager.createNamedQuery(BankingTransaction.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public List<TransactionType> getAllTypes() throws Exception{
        return entityManager.createNamedQuery(TransactionType.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public BankingTransaction searchTransactionById(int transactionId) throws Exception{
        BankingTransaction bankTrans = entityManager.find(BankingTransaction.class, transactionId);
        return bankTrans;
    }
    
    @Override
    public List<BankingTransaction> searchTransactionsByType(int typeId)throws Exception{
        TransactionType transType = entityManager.find(TransactionType.class, typeId);
        transType.getTypeBasedTransactions().size();
        entityManager.refresh(transType);
        return transType.getTypeBasedTransactions();
        
    }
    
    @Override
    public List<BankingTransaction> searchTransactionsByName(String name) throws Exception{
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankingTransaction> query = builder.createQuery(BankingTransaction.class);
        //Metamodel m = entityManager.getMetamodel();
        //EntityType<BankingTransaction> BankingTransaction_ = m.entity(BankingTransaction.class);
        Root<BankingTransaction> transactionRoot = query.from(BankingTransaction.class);
        query.where(builder.equal(transactionRoot.<String>get("transactionName"),
                            builder.parameter(String.class, "param")));
        
        TypedQuery<BankingTransaction> tq = entityManager.createQuery(query);
        tq.setParameter("param", name);
        List<BankingTransaction> transListByName = tq.getResultList();
        return transListByName;
        
        
        
        
    } 
}
