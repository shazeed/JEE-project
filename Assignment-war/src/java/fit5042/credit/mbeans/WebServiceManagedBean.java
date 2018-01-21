/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.mbeans;

import fit5042.credit.repository.WebServiceBeanRemote;
import fit5042.credit.repository.entities.TransactionType;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author shazeed ahsan
 */
@Named(value = "webServiceManagedBean")
@SessionScoped
public class WebServiceManagedBean implements Serializable {

    @EJB
    private WebServiceBeanRemote serviceRepo;
    private String userId;
    private String transactionType;
    private String output;
    /**
     * Creates a new instance of WebServiceManagedBean
     */
    public WebServiceManagedBean() {
        output = "";
        //this.transactionType = new TransactionType();
    }

    public WebServiceBeanRemote getServiceRepo() {
        return serviceRepo;
    }

    public void setServiceRepo(WebServiceBeanRemote serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    public void transTypeFromWebService(){
        this.output = this.serviceRepo.searchTransactionByType(this.transactionType);
    }
    
    
    
    public void userFromWebService(){
        this.output = this.serviceRepo.searchUserById(this.userId);
    }

    /*
    public List<TransactionType> getTypeList() {
        this.typeList = this.serviceRepo.getAllTransactionTypes();
        return typeList;
    }

    public void setTypeList(List<TransactionType> typeList) {
        this.typeList = typeList;
    }
*/

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    
    
    public void addType(){
        //this.serviceRepo.addTransactionType(this.transactionType);
    }
    
    
}
