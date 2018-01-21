/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.mbeans;

import fit5042.credit.repository.TransactionRepositoryImplRemote;
import fit5042.credit.repository.UserBalanceTransferImplRemote;
import fit5042.credit.repository.UserRepositoryImplRemote;
import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.BankingUser;
import fit5042.credit.repository.entities.PublicUser;
import fit5042.credit.repository.entities.TransactionType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author shazeed ahsan
 */
@Named(value = "adjustBalanceBean")
@SessionScoped
public class AdjustBalanceBean implements Serializable {

    private String fromUserId;
    private String toUserId;
    private String transactionType;
    private String amount;
    private String status;
    //private BankingTransaction transaction;
    private PublicUser publicUserTrans;
    
    private String fromUserBalance;

    @EJB
    private UserRepositoryImplRemote userRepo;

    @EJB
    private UserBalanceTransferImplRemote balanceTrans;

    @EJB
    private TransactionRepositoryImplRemote transRepo;

    /**
     * Creates a new instance of AdjustBalanceBean
     */
    public AdjustBalanceBean() {
        this.fromUserId = "";
        this.toUserId = "";
        this.transactionType = "";
        this.amount = "";
        this.status = "";
        this.fromUserBalance = "";
        this.publicUserTrans = new PublicUser();
        //this.transaction = new BankingTransaction();
    }

    public PublicUser getPublicUserTrans() {
        return publicUserTrans;
    }

    public void setPublicUserTrans(PublicUser publicUserTrans) {
        this.publicUserTrans = publicUserTrans;
    }

    
    
    public String getFromUserBalance() {
        return fromUserBalance;
    }

    public void setFromUserBalance(String fromUserBalance) {
        this.fromUserBalance = fromUserBalance;
    }

    
    public TransactionRepositoryImplRemote getTransRepo() {
        return transRepo;
    }

    public void setTransRepo(TransactionRepositoryImplRemote transRepo) {
        this.transRepo = transRepo;
    }

    public UserRepositoryImplRemote getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepositoryImplRemote userRepo) {
        this.userRepo = userRepo;
    }

    public UserBalanceTransferImplRemote getBalanceTrans() {
        return balanceTrans;
    }

    public void setBalanceTrans(UserBalanceTransferImplRemote balanceTrans) {
        this.balanceTrans = balanceTrans;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void doBalance() {
        if (!this.checkIfEmpty(amount) && checkValidAmount(amount) && this.checkIdValid(this.fromUserId) && !this.checkIfEmpty(this.fromUserId)) {
            System.out.println(this.transactionType);
            switch (this.transactionType) {
                case "DEPOSIT":
                    this.addMoney(Double.parseDouble(amount), Integer.parseInt(this.fromUserId));
                    break;
                case "TRANSFER":
                    this.transferMoney(Double.parseDouble(amount));
                    break;
                case "WITHDRAW":
                    this.withdrawMoney(Double.parseDouble(amount), Integer.parseInt(this.fromUserId));
                    break;
                default:
                    System.out.println("default");

            }
        } else {
        }

    }

    public boolean checkIfEmpty(String input) {
        if (input.trim().equals("")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkValidAmount(String amount) {
        try {
            double newAmount = Double.parseDouble(amount);
            if (newAmount > 0) {
                return true;
            }

        } catch (NumberFormatException ex) {
            return false;
        }
        return false;
    }

    public void withdrawMoney(double amount, int uid) {
        try {
            PublicUser aUser = this.userRepo.searchPublicUserById(uid);
            double oldBalance = aUser.getBalance();
            double newBalance = oldBalance - amount;
            if (newBalance > 0) {
                String transDesc = uid + " HAVE WITHDRAWN $" + amount;
                String transName = "WITHDRAW";
                BankingTransaction newTransaction = this.setTransactionAttr(transName, transDesc);
                newTransaction.setTransactionUser(aUser);
                this.transRepo.addTransaction(newTransaction);
                this.balanceTrans.adjustBalance(newBalance, uid);
                this.publicUserTrans = this.userRepo.searchPublicUserById(uid);
                this.status = "Withdraw successful";
            } else {
                this.status = "Not enough balance for this transaction!!";
            }
        } catch (Exception ex) {
            this.status = "Transaction unsuccessful!!";
        }

    }

    public void addMoney(double amount, int uid) {
        try {
            //BankingTransaction newTransaction = new BankingTransaction();
            this.publicUserTrans = this.userRepo.searchPublicUserById(uid);
            double oldBalance = this.publicUserTrans.getBalance();
            double newBalance = oldBalance + amount;
            this.fromUserBalance = String.valueOf(newBalance);
            String transDesc = uid + " DEPOSITED $" + amount;
            String transName = "DEPOSIT";
            BankingTransaction newTransaction = this.setTransactionAttr(transName, transDesc);
            newTransaction.setTransactionUser(this.publicUserTrans);
            this.transRepo.addTransaction(newTransaction);
            this.balanceTrans.adjustBalance(newBalance, uid);
            //this.publicUserTrans.setBalance(newBalance);
            this.publicUserTrans = this.userRepo.searchPublicUserById(uid);
            status = "Deposit Successful";
        } catch (Exception ex) {
            this.status = "Transaction unsuccessful!!";
        }
    }

    public void transferMoney(double amount) {
        try {
            System.out.println(this.toUserId);
            if (this.checkIdValid(this.toUserId) && this.checkIdValid(this.fromUserId)) {
                int reqId = Integer.parseInt(this.toUserId);
                int fromId = Integer.parseInt(this.fromUserId);
                System.out.println(reqId);
                PublicUser bUser = this.userRepo.searchPublicUserById(reqId);
                PublicUser aUser = this.userRepo.searchPublicUserById(fromId);
                double aUserOldBalance = aUser.getBalance();
                double bUserOldBalance = bUser.getBalance();
                double bUserNewBalance = bUserOldBalance + amount;
                double aUserNewBalance = aUserOldBalance - amount;
                if (aUserNewBalance > 0) {
                    String transDesc = "TRANSFER FROM " + fromId + " to " + reqId;
                    String transName = "TRANSFER";
                    BankingTransaction newTransaction = this.setTransactionAttr(transName, transDesc);
                    newTransaction.setTransactionUser(aUser);
                    this.transRepo.addTransaction(newTransaction);
                    //aUser.getTransactions().add(newTransaction);
                    this.balanceTrans.adjustBalance(aUserNewBalance, fromId);
                    this.balanceTrans.adjustBalance(bUserNewBalance, reqId);
                    this.publicUserTrans = this.userRepo.searchPublicUserById(fromId);
                    this.status = "You have successfully transferred $" + amount + "to " + bUser.getFirstName();
                } else {
                    this.status = "Not enough balance for this transaction!!";
                }
            }
        } catch (Exception ex) {
            this.status = "Transaction unsuccessful!!";
        }

    }

    public BankingTransaction setTransactionAttr(String transName, String transDesc) {
        BankingTransaction newTransaction = new BankingTransaction();
        newTransaction.setTransactionDesc(transDesc);
        newTransaction.setTransactionName(transName);
        try {
            List<TransactionType> typeList = this.transRepo.getAllTypes();
            if (this.typeExist(this.transactionType, typeList) != -1) {
                int typeId = this.typeExist(this.transactionType, typeList);
                TransactionType newType = this.transRepo.getTransactionType(typeId);
                newTransaction.setTransactionType(newType);
            }
        } catch (Exception ex) {
        }
        System.out.println(newTransaction.toString());
        return newTransaction;
    }

    public boolean checkIdValid(String newId) {
        try {
            List<BankingUser> allUsers = this.userRepo.getAllUsers();
                int uid = Integer.parseInt(newId);
                for (BankingUser aUser : allUsers) {
                    if (aUser.getId_number() == uid) {
                        System.out.println("id found");
                        return true;
                    }
                }
        } catch (NumberFormatException ex) {
            return false;
        } catch (Exception ex) {
        }

        return false;
    }

    public int typeExist(String transType, List<TransactionType> typeList) {
        //boolean checkType = false;
        int transTypeId;
        for (TransactionType type : typeList) {
            if (type.getTypeName().equals(transType)) {
                transTypeId = type.getTypeId();
                return transTypeId;
                //checkType = true;
            }
        }
        return -1;
    }
}
