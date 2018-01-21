/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.mbeans;

import fit5042.credit.repository.TransactionRepositoryImplRemote;
import fit5042.credit.repository.entities.BankingTransaction;
import fit5042.credit.repository.entities.TransactionType;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author shazeed ahsan
 */
@Named(value = "searchManagedBean")
@SessionScoped
public class SearchManagedBean implements Serializable {

    @EJB
    private TransactionRepositoryImplRemote transactionRepo;

    private List<BankingTransaction> newTransactionList;
    private BankingTransaction transaction;
    private String keyword;
    private String searchBy;
    private Boolean searchResult;

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
        transaction = new BankingTransaction();
        this.newTransactionList = new ArrayList<>();
        this.searchResult = false;
    }

    public List<BankingTransaction> getNewTransactionList() {
        return newTransactionList;
    }

    public void setNewTransactionList(ArrayList<BankingTransaction> newTransactionList) {
        this.newTransactionList = newTransactionList;
    }

    public BankingTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(BankingTransaction transaction) {
        this.transaction = transaction;
    }

    public TransactionRepositoryImplRemote getTransactionRepo() {
        return transactionRepo;
    }

    public void setTransactionRepo(TransactionRepositoryImplRemote transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public Boolean getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(Boolean searchResult) {
        this.searchResult = searchResult;
    }


    
    

    public void searchTransactionById(String transId) {
        newTransactionList = new ArrayList<>();
        try {
            int id = Integer.parseInt(transId);
            BankingTransaction newTransaction = transactionRepo.searchTransactionById(id);
            newTransactionList.add(newTransaction);
            if (newTransactionList.isEmpty()) {
                this.searchResult = true;
            }else {
                this.searchResult = false;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Id provided is not valid. Exception occured: " + ex.getMessage());
            searchResult = true;
        } catch (Exception ex) {
            System.out.println("Exception at sbi: " + ex.getMessage());
        }

    }

    public void searchTransactionsByName(String name) {
        newTransactionList = new ArrayList<>();
        try {
            newTransactionList = transactionRepo.searchTransactionsByName(name);
            if (newTransactionList.isEmpty()) {
                this.searchResult = true;
            }else{
                    searchResult = false;
                }

        } catch (Exception ex) {
            System.out.println("Exception at sbn: " + ex.getMessage());
        }

    }

    public void searchTransactionsByType(String transType) {
        newTransactionList = new ArrayList<>();
        try {
            List<TransactionType> typeList = transactionRepo.getAllTypes();
            int reqId = typeExist(transType, typeList);
            if (reqId != -1) {
                newTransactionList = transactionRepo.searchTransactionsByType(reqId);
                this.searchResult = false;
            }else{
                this.searchResult = true;
            }
        } catch (Exception ex) {

        }

    }

    public void doSearch() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (this.checkIfEmpty(keyword)) {
            switch (searchBy) {
                case "nameSearch":
                    searchTransactionsByName(keyword);
                    break;
                case "idSearch":
                    this.searchTransactionById(keyword);
                    break;
                case "typeSearch":
                    this.searchTransactionsByType(keyword);
                    break;
                case "all":
                    this.listAllTransactions();
                default:
                    this.listAllTransactions();
            }

        } else {
            facesContext.addMessage("searchForm", new FacesMessage("The input should not be null"));
        }

    }

    public void listAllTransactions() {
        try {
            this.newTransactionList = transactionRepo.getAllTransactions();
            if (newTransactionList.isEmpty()) {
                System.out.println("No Transactions!!!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    public boolean checkIfEmpty(String input) {
        if (input.trim().equals("")) {
            return false;
        } else {
            return true;
        }

    }

    public void viewDetails() {
        this.transaction = new BankingTransaction();
        String myParam = "" + FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("myParam");
        int transId = Integer.parseInt(myParam);
        try {
            this.transaction = this.transactionRepo.searchTransactionById(transId);
            System.out.println(transaction.getTransactionName());
            FacesContext.getCurrentInstance().getExternalContext().dispatch("transactionDetails.xhtml");
        } catch (Exception ex) {
            System.out.println("eroor");
        }
    }
}
