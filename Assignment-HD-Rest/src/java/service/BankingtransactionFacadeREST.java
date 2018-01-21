/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import hd.entities.Bankingtransaction;
import hd.entities.Bankinguser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shazeed ahsan
 */
@Stateless
@Path("hd.entities.bankingtransaction")
public class BankingtransactionFacadeREST extends AbstractFacade<Bankingtransaction> {

    @PersistenceContext(unitName = "Assignment-HD-RestPU")
    private EntityManager em;

    public BankingtransactionFacadeREST() {
        super(Bankingtransaction.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bankingtransaction entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Bankingtransaction entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bankingtransaction find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bankingtransaction> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bankingtransaction> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findTransactionsByType/{typename}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bankingtransaction> findTransactionsByType(@PathParam("typename")String typename)throws Exception{
        Query query = em.createQuery("SELECT t FROM Bankingtransaction t WHERE t.transactionTypeId.typename =:typename");
        query = query.setParameter("typename", typename);
        return query.getResultList();
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
