package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Bool;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */

@Stateless
@Path("/accounts")
public class AccountController {

    @Inject
    AccountService service;

    @Context
    UriInfo uriInfo;

    @POST
    @Path("/register")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response create(@FormParam("username") String username,
                          @FormParam("email") String email,
                          @FormParam("bio") String bio,
                          @FormParam("firstName") String firstName,
                          @FormParam("lastName") String lastName,
                          @FormParam("password") String password) throws URISyntaxException {
        Account account;
        try {
            account = service.create(username, email, bio, firstName, lastName, password);
        }catch(Exception e){
            return Response.serverError().build();
        }
        if(account==null)return Response.serverError().build();
        return Response.created(getCreatedLink(account)).entity(account).build();
    }
    private URI getCreatedLink(Account entity){
        return uriInfo.getAbsolutePathBuilder().path(entity.getId() +"").build();
    }

    @GET
    @Path("/accounts")
    @Produces("application/json")
    public Response getAllAccounts() {
        return Response.ok().entity(service.getAllAccounts()).build();
    }

    @GET
    @Path("/id/{userId}")
    @Produces("application/json")
    public Response getAccountById(@PathParam("userId") int userId) {
        return Response.ok().entity(service.findByID(userId)).build();
    }


    @GET
    @Path("/username/{userName}")
    @Produces("application/json")
    public Response getAccountByUsername(@PathParam("userName") String userName) {
        return Response.ok().entity(service.findByUsername(userName)).build();
    }

    //    @RolesAllowed("admin")//TODO : make the rolesallowed work and keep a user
    @GET
    @Path("/search/{name}")
    @Produces("application/json")
    public Response search(@PathParam("name") String name) {
        return Response.ok().entity(service.search(name)).build();
    }


    @GET
    @Path("/follow/{Id}")
    @Produces("application/json")
    public Response follow(@PathParam("Id") int id,
                          @QueryParam("loggedInUser") int loggedInUser) {
        return Response.ok().entity(new Bool(service.follow(id, loggedInUser))).build();
    }

    @GET
    @Path("/unFollow/{Id}")
    @Produces("application/json")
    public Response unfollow(@PathParam("Id") int id,
                            @QueryParam("loggedinUser") int loggedInUser) {
        return Response.ok().entity(new Bool(service.unFollow(id, loggedInUser))).build();
    }

    @GET
    @Path("/followers/{Id}")//following you
    @Produces("application/json")
    public Response followers(@PathParam("Id") int id) {
        return Response.ok().entity(service.followers(id)).build();
    }

    @GET
    @Path("/role/add/{type}/{Id}")
    @Produces("application/json")
    public Response RoleAdd(@PathParam("type") String type, @PathParam("Id") int id) {
        return Response.ok().entity(new Bool(service.addRole(type, id))).build();
    }

    @GET
    @Path("/role/remove/{type}/{Id}")//following you
    @Produces("application/json")
    public Response RoleRemove(@PathParam("type") String type, @PathParam("Id") int id) {
        return Response.ok().entity(new Bool(service.removeRole(type, id))).build();
    }

//    @POST
//    @Path("/login")
//    @Consumes("application/x-www-form-urlencoded")
//    @Produces("application/json")
//    public boolean Login(@FormParam("Username") String username,
//                            @FormParam("Password") String password) throws LoginException {//TODO : remove the userId and use JAAS
//        return service.login(username,password);
//    }
}