package Controllers;

import Models.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Wouter Vanmulken on 2-3-2017.
 */
@Path("Users")
public class AccountController {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("user")
    @javax.ws.rs.Produces("application/json")
    public Account GetUser(){
        Account account = em.find(Account.class,0);
        return account;
//        return new Account("foo","bar");
   }
}