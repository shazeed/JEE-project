/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.credit.controller;

import fit5042.credit.repository.entities.BankingTransaction;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author shazeed ahsan
 */
@Named(value = "transactionController")
@RequestScoped
public class TransactionController {

    private int transactionId;
    private BankingTransaction transaction;
/*
    public TransactionController() {
        transactionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("movieID"));
// Assign movie based on the id provided
        movie = getMovie();

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public BankingTransaction getTransaction() {
        if (transaction == null) {
// Get application context bean MovieApplication
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            MovieApplication app
                    = (MovieApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "movieApplication");
// -1 to movieId since we +1 in JSF (to always have positive movie id!)
            return app.getMovies().get(--movieId);
        }
        return movie;
    }

    public void setTransaction(BankingTransaction transaction) {
        this.transaction = transaction;
    }
*/
}
