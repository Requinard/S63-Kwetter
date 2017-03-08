package Controllers;

import Models.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by Wouter Vanmulken on 2-3-2017.
 */
@Path("/Accounts")
public class AccountController {

    @PersistenceContext
    EntityManager em;

//    @GET
//    @Path("account")
//    @javax.ws.rs.Produces("application/json")
//    public Account GetAccounts(){
//        Account account = em.enti;
//        return account;
//    }
//
    @GET
    @Path("/account/{userId}")
    @javax.ws.rs.Produces("application/json")
    public Account GetUserById(@QueryParam("userId")int userId){
        Account account = em.find(Account.class,userId);
        return account;
   }
}