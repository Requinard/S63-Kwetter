package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */

@Stateless
@Path("/accounts")
public class AccountController {

    @Inject
    AccountService service;

    @GET
    @Path("/{userId}")
    @Produces("application/json")
    public Account getAccount(@PathParam("userId") int userId) {
        return service.getAccount(userId);
    }

    @GET
    @Path("/user/{userName}")
    @Produces("application/json")
    public Account getAccountByUsername(@PathParam("userName") String userName) {
        return service.getAccountByUsername(userName);
    }

    @GET
    @Path("/search/{name}")
    @Produces("application/json")
    public List<Account> search(@PathParam("name") String name) {
        return service.search(name);
    }
    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public boolean Login(@FormParam("Username") String username,
                            @FormParam("Password") String password) throws LoginException {//TODO : remove the userId and use JAAS
        return service.login(username,password);
    }

}