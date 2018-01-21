/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import java.util.List;
import javax.ejb.Remote;

/**
 * more comments
 * @author shazeed ahsan
 */
@Remote
public interface UserRepositoryImplRemote {
    
    public List<BankingUser> getAllUsers() throws Exception;
    
    public String getUserType(int userId) throws Exception;
    
    public BankingUser searchUserById(int userId) throws Exception;
    
    public PublicUser searchPublicUserById(int userId) throws Exception;
    
    public void addUser(BankingUser newUser) throws Exception;
    
    public void editUser(BankingUser selectedUser) throws Exception;
    
    public void removeUser(int userId) throws Exception;
    
    public List<BankingUser> searchUsers(String userID, String userFName, String userLName, String userType, String userEmail) throws Exception;
    
    public List<BankingUser> getSelectedUsers(String userID, String userFName, String userLName, String userType, String uEmail)throws Exception;
    
}
