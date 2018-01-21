/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author shazeed ahsan
 */
@Stateless
public class UserRepositoryImpl implements UserRepositoryImplRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BankingUser> getAllUsers() throws Exception {
        return entityManager.createNamedQuery(BankingUser.GET_ALL_QUERY_NAME).getResultList();
        //return entityManager.createQuery("SELECT u from PublicUser u").getResultList();
    }

    @Override
    public String getUserType(int userId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BankingUser searchUserById(int userId) throws Exception {
        BankingUser newUser = entityManager.find(BankingUser.class, userId);
        return newUser;
    }

    @Override
    public PublicUser searchPublicUserById(int userID) throws Exception {
        PublicUser newUser = entityManager.find(PublicUser.class, userID);
        newUser.getTransactions().size();
        this.entityManager.refresh(newUser);
        return newUser;
    }

    @Override
    public void addUser(BankingUser newUser) throws Exception {
        entityManager.persist(newUser);
    }

    @Override
    public void editUser(BankingUser selectedUser) throws Exception {
        entityManager.merge(selectedUser);
    }

    @Override
    public void removeUser(int userId) throws Exception {

        BankingUser toBeRemoved = entityManager.find(BankingUser.class, userId);
        System.out.println("ejb"+ toBeRemoved.getId_number());

        if (toBeRemoved != null) {
            entityManager.remove(toBeRemoved);
        }

    }

    @Override
    public void removePublicUser(int userId)throws Exception{
        PublicUser toBeRemoved = entityManager.find(PublicUser.class, userId);
        if (toBeRemoved != null) {
            System.out.println("ejbP"+ toBeRemoved.getId_number());
            entityManager.remove(toBeRemoved);
        }
    }
    
    @Override
    public List<BankingUser> searchUsers(String userID, String userFName, String userLName, String userType, String userEmail) {
        List<BankingUser> userList = new ArrayList<>();
        System.out.println(userID + userFName + userLName + userType + userEmail);
        if (userID.equals("")) {
            userID = null;
        }

        if ("".equals(userFName)) {
            userFName = null;
        }

        if ("".equals(userLName)) {
            userLName = null;
        }

        if ("".equals(userType)) {
            userType = null;
        }

        if ("".equals(userEmail)) {
            userEmail = null;
        }

        Query query = this.entityManager.createQuery("select u " + " from BankingUser as u " + "where u.id_number='" + userID + "' or u.firstName LIKE '%" + userFName + "%' or u.lastName LIKE '%" + userLName + "%' or u.email LIKE '%" + userEmail + "%' or u.userType LIKE '%" + userType + "%'");
        System.out.println(query.toString());
        userList = query.getResultList();
        System.out.println(userList.size());
        for (BankingUser auser : userList) {
            System.out.println(auser.getFirstName());
        }
        return userList;
    }

    @Override
    public List<BankingUser> getSelectedUsers(String userID, String userFName, String userLName, String userType, String uEmail) {
        if ("".equals(userFName)) {
            userFName = "NA";
        }

        if ("".equals(userLName)) {
            userLName = "NA";
        }

        if ("".equals(userType)) {
            userType = "NA";
        }

        if ("".equals(uEmail)) {
            uEmail = "NA";
        }

        try {
            int newId;
            if (userID.trim().isEmpty()) {
                newId = 0;
                return entityManager.createNamedQuery(BankingUser.GET_SELECTED_NAME)
                        .setParameter("userId", newId)
                        .setParameter("fname", "%" + userFName + "%")
                        .setParameter("lName", "%" + userLName + "%")
                        .setParameter("userEmail", "%" + uEmail + "%")
                        .setParameter("uType", userType).getResultList();
            } else {
                newId = Integer.parseInt(userID);
                return entityManager.createNamedQuery(BankingUser.GET_SELECTED_NAME)
                        .setParameter("userId", newId)
                        .setParameter("fname", "%" + userFName + "%")
                        .setParameter("lName", "%" + userLName + "%")
                        .setParameter("userEmail", "%" + uEmail + "%")
                        .setParameter("uType", userType).getResultList();
            }

        } catch (NumberFormatException ex) {
            return null;
        }

    }

    @Override
    public List<BankingTransaction> searchTransactionsByUser(int userID) throws Exception {
        try {
            PublicUser newUser = entityManager.find(PublicUser.class, userID);
            newUser.getTransactions().size();
            this.entityManager.refresh(newUser);

            return newUser.getTransactions();
        } catch (Exception ex) {
            return null;
        }

    }
}
