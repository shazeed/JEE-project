/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.rest;

import fit5042.credit.repository.UserRepositoryImplRemote;
import fit5042.credit.repository.entities.BankingUser;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author shazeed ahsan
 */
@Path("UserSearch")
public class UserSearch {

    @Context
    private UriInfo context;

    @EJB
    private UserRepositoryImplRemote userRepo;
    /**
     * Creates a new instance of UserSearch
     */
    public UserSearch() {
    }

    /**
     * Retrieves representation of an instance of fit5042.assignment.rest.UserSearch
     * @return an instance of fit5042.credit.repository.entities.BankingUser
     */
    @GET
    @Path("firstAttempt")
    @Produces(MediaType.TEXT_PLAIN)
    public String oneMethod(){
        return "Hello";
    }
    
    @GET
    @Path("getUserById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BankingUser getUserById(@PathParam("id") Integer id){
        try{
           return this.userRepo.searchUserById(id);
        }catch(Exception ex){
            return null;
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BankingUser getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserSearch
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(BankingUser content) {
    }
}
