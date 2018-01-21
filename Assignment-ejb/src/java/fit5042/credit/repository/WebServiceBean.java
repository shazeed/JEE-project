/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.repository;

import fit5042.credit.repository.entities.TransactionType;
import java.util.Scanner;
import javax.ejb.Singleton;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import java.net.HttpURLConnection; 
import java.net.URL; 
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author shazeed ahsan
 */
@Singleton
public class WebServiceBean implements WebServiceBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private WebTarget userWebTarget;
    private WebTarget transWebTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:49820/Assignment-HD-Rest/webresources";
    
    
    
    /*
    private static final String PERSISTENCE_UNIT = "Assignment-ejbPU";
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;
*/
    public WebServiceBean(){
    }
    
    @Override
    public String searchUserById(String userId) {
        final String methodPath = "/hd.entities.bankinguser/" + userId;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        try {
            url = new URL(BASE_URI + methodPath);

            conn = (HttpURLConnection) url.openConnection();
//set the timeout 
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json 
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response 
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string 
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    
    @Override
    public String searchTransactionByType(String transType){
        final String methodPath = "/hd.entities.bankingtransaction/findTransactionsByType/" + transType;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        try {
            url = new URL(BASE_URI + methodPath);

            conn = (HttpURLConnection) url.openConnection();
//set the timeout 
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
//set the connection method to GET
            conn.setRequestMethod("GET");
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json 
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
//Read the response 
            Scanner inStream = new Scanner(conn.getInputStream());
//read the input steream and store it as string 
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    
    /*
    
    @Override
    public void addTransactionType(TransactionType transType){
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try 
        {
            transaction.begin();
            this.entityManager.persist(transType);
            transaction.commit();
        } 
        catch (Exception ex)
        {
            if (transaction != null && transaction.isActive()) 
            {
                transaction.rollback();
            }
        }
    }
    
    public List<TransactionType> getAllTransactionTypes(){
        return this.entityManager.createNamedQuery(TransactionType.GET_ALL_QUERY_NAME).getResultList(); 
 
}*/
}

