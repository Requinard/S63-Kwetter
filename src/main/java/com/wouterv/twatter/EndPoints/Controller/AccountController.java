package com.wouterv.twatter.EndPoints.Controller;

import com.wouterv.twatter.Bool;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */

@Stateless
//@DeclareRoles({"regular_role", "admin_role"})
@Path("/accounts")
public class AccountController {

    @Inject
    AccountService service;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response create(@FormParam("username") String username,
                           @FormParam("email") String email,
                           @FormParam("bio") String bio,
                           @FormParam("firstName") String firstName,
                           @FormParam("lastName") String lastName,
                           @FormParam("password") String password) {
        Account account;
        try {
            account = service.create(username, email, bio, firstName, lastName, password);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (account == null) return Response.serverError().build();
        return Response.created(getCreatedLink(account)).entity(account).build();
    }

    @GET
//    @RolesAllowed({"admin_role"})
    @Produces("application/json")
    public Response getAllAccounts() {
        List<Account> accounts;
        try {
            accounts = service.getAllAccounts();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        final GenericEntity<List<Account>> entity = new GenericEntity<List<Account>>(accounts) {
        };
        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("/{userId}")
    @Produces("application/json")
    public Response getAccountById(@PathParam("userId") int userId) {
        Account account;
        try {
            account = service.findByID(userId);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (account == null) return Response.noContent().build();
        return Response.ok().entity(account).build();
    }

    @GET
    @Path("/tweets/{userId}")
    @Produces("application/json")
    public Response getTweetsByAccountId(@PathParam("userId") int userId) {
        Account account;
        try {
            account = service.findByID(userId);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (account == null || account.getTweets() == null) return Response.noContent().build();
        final GenericEntity<List<Tweet>> entity = new GenericEntity<List<Tweet>>(account.getTweets()) {
        };
        return Response.ok().entity(entity).build();
    }


    @GET
    @Path("/username/{userName}")
    @Produces("application/json")
    public Response getAccountByUsername(@PathParam("userName") String userName) {
        Account account;
        try {
            account = service.findByUsername(userName);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (account == null) return Response.noContent().build();
        return Response.ok().entity(account).build();
    }

    //    @RolesAllowed("admin")//TODO : make the rolesallowed work and keep a user
    @GET
    @Path("/search/{name}")
    @Produces("application/json")
    public Response search(@PathParam("name") String name) {
        List<Account> accounts;
        try {
            accounts = service.search(name);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (accounts == null) return Response.serverError().build();
        final GenericEntity<List<Account>> entity = new GenericEntity<List<Account>>(accounts) {
        };

        return Response.ok().entity(entity).build();
    }

    //    @GET
//    @Path("/follow/{Id}")
//    @Produces("application/json")
//    public Response follow(@PathParam("Id") int id, @QueryParam("loggedInUser") int loggedInUser) {
//        boolean success;
//        try {
//            success = service.follow(id, loggedInUser);
//        } catch (Exception e) {
//            return Response.serverError().build();
//        }
//        return Response.ok().entity(new Bool(success)).build();
//    }
//
//    @GET
//    @Path("/unFollow/{Id}")
//    @Produces("application/json")
//    public Response unfollow(@PathParam("Id") int id,
//                             @QueryParam("loggedinUser") int loggedInUser) {
//        boolean success;
//        try {
//            success = service.unFollow(id, loggedInUser);
//        } catch (Exception e) {
//            return Response.serverError().build();
//        }
//        return Response.ok().entity(new Bool(success)).build();
//    }
    @GET
    @Path("/follow/{Id}")
    @Produces("application/json")
    public Response followToggle(@PathParam("Id") int id, @QueryParam("loggedInUser") int loggedInUser) {
        boolean success;
        try {
            success = service.followToggle(id, loggedInUser);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }

    @GET
    @Path("/followers/{Id}")//following you
    @Produces("application/json")
    public Response followers(@PathParam("Id") int id) {
        List<Account> accounts;
        try {
            accounts = service.followers(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (accounts == null) return Response.serverError().build();
        final GenericEntity<List<Account>> entity = new GenericEntity<List<Account>>(accounts) {
        };

        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("/role/add/{type}/{Id}")
    @Produces("application/json")
    public Response RoleAdd(@PathParam("type") String type, @PathParam("Id") int id) {
        boolean success;
        try {
            success = service.addRole(type, id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();

    }

    @GET
    @Path("/role/remove/{type}/{Id}")//following you
    @Produces("application/json")
    public Response RoleRemove(@PathParam("type") String type, @PathParam("Id") int id) {
        boolean success;
        try {
            success = service.removeRole(type, id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    @Path("/edit/password")
    public Response editPassword(@FormParam("currentPass") String currentPass,
                                 @FormParam("newPass") String newPass,
                                 @QueryParam("id") int id) {
        boolean success;
        try {
            success = service.editPassword(id, currentPass, newPass);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    @Path("/edit")
    public Response edit(@QueryParam("id") int id,
                         @FormParam("username") String username,
                         @FormParam("email") String email,
                         @FormParam("bio") String bio,
                         @FormParam("firstName") String firstName,
                         @FormParam("lastName") String lastName) {
        boolean success;
        try {
            success = service.edit(id, username, email, bio, firstName, lastName);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }


    private URI getCreatedLink(Account entity) {
        return uriInfo.getAbsolutePathBuilder().path(entity.getId() + "").build();
    }
}