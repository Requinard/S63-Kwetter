package Service;

import Annotations.JPA;
import Controllers.ITestDingetje;
import DAO.IAccountDAO;
import Models.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Stateless
@Path("/accountservice")
public class AccountService {

    @Inject @JPA
    IAccountDAO accountDAO;

    @Inject
    ITestDingetje TestDingetje;

    @GET
    @Path("/test")
    public String getTest() {
        String test = "not null";
        if(TestDingetje == null){
            test = "null";
        }else{
            test = TestDingetje.getStuff();
        }
        return test;
    }

    @GET
    @Path("/account/{userId}")
    @Produces("application/json")
    public Account getAccount(@QueryParam("userId")int userId) {
        Account account = accountDAO.findById(userId);
        return account;
    }
    @GET
    @Path("/account/user/{userName}")
    @Produces("application/json")
    public Account getAccount(@QueryParam("userName")String userName) {
        Account account = accountDAO.findByName(userName);
        return account;
    }

}
