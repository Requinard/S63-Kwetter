package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;

import javax.annotation.ManagedBean;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import java.security.Principal;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */

@Stateless
@ManagedBean
@Path("/accounts")
public class AccountController {

    @Inject
    AccountService service;

    @GET
    @Path("/{userId}")
    @Produces("application/json")
    public Account getAccountById(@PathParam("userId") int userId) {
        return service.getAccount(userId);
    }
    @GET
    @Path("/accounts")
    @Produces("application/json")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GET
    @Path("/user/{userName}")
    @Produces("application/json")
    public Account getAccountByUsername(@PathParam("userName") String userName) {
        return service.getAccountByUsername(userName);
    }
//    @RolesAllowed("admin")//TODO : make the rolesallowed work and keep a user
    @GET
    @Path("/search/{name}")
    @Produces("application/json")
    public List<Account> search(@PathParam("name") String name) {
        return service.search(name);
    }


    @POST
    @Path("/register")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Account create(@FormParam("username") String username,
                              @FormParam("email") String email,
                              @FormParam("bio") String bio,
                              @FormParam("firstName") String firstName,
                              @FormParam("lastName") String lastName) {
        return service.create(username, email, bio, firstName, lastName);
    }
    @GET
    @Path("/follow/{Id}")
    @Produces("application/json")
    public Boolean follow(@PathParam("Id") int id,
                                @QueryParam("loggedinUser") int loggedInUser) {//TODO : maybe this should be a post
        return service.follow(id,loggedInUser);
    }
    @GET
    @Path("/unFollow/{Id}")
    @Produces("application/json")
    public Boolean unfollow(@PathParam("Id") int id,
                          @QueryParam("loggedinUser") int loggedInUser) {//TODO : maybe this should be a post
        return service.unFollow(id,loggedInUser);
    }
    @GET
    @Path("/followers/{Id}")
    @Produces("application/json")
    public List<Account> followers(@PathParam("Id") int id) {//TODO : maybe this should be a post
        return service.followers(id);
    }

//    @POST
//    @Path("/login")
//    @Consumes("application/x-www-form-urlencoded")
//    @Produces("application/json")
//    public boolean Login(@FormParam("Username") String username,
//                            @FormParam("Password") String password) throws LoginException {//TODO : remove the userId and use JAAS
//        return service.login(username,password);
//    }
    @GET
    @Path("/test")
    @Produces("application/json")
    public Principal test() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext a = fc.getExternalContext();
        Principal p = a.getUserPrincipal();
        return p;
//        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }


}