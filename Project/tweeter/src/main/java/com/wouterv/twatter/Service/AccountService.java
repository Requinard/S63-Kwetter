package com.wouterv.twatter.Service;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Stateless
@Path("/accountsservice")
public class AccountService {

    @Inject
    @JPA
    IAccountDAO accountDAO;

    @Inject
    ITestDingetje TestDingetje;

    @GET
    @Path("/test")
    public String getTest() {
        String test;
        if (TestDingetje == null) {
            test = "null";
        } else {
            test = TestDingetje.getStuff();
        }
        return test;
    }

    @GET
    @Path("/account/{userId}")
    @Produces("application/json")
    public Account getAccount(@PathParam("userId") int userId) {
        Account account = accountDAO.findById(userId);
        return account;
    }

    @GET
    @Path("/account/user/{userName}")
    @Produces("application/json")
    public Account getAccountByUsername(@PathParam("userName") String userName) {
        Account account = accountDAO.findByUserName(userName);
        return account;
    }

    @GET
    @Path("/account/search/{userName}")
    @Produces("application/json")
    public List<Account> search(@PathParam("userName") String userName) {
        List<Account> accounts = accountDAO.search(userName);
        return accounts;
    }

//    @GET
//    @Path("account")
//    @Produces("application/json")
//    public String test(){
//
//    }

}
