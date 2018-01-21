/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.TransactionType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author shazeed ahsan
 */
@Remote
public interface WebServiceBeanRemote {

    public String searchUserById(String userId);
    
    public String searchTransactionByType(String transType);
    
    /*
     public List<TransactionType> getAllTransactionTypes();
     
     public void addTransactionType(TransactionType transType);
*/
}
