package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Bool;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */
@Stateless
@Path("/tweets")
public class TweetController {

    @Inject
    TweetService service;
    @Context
    UriInfo uriInfo;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response create(@FormParam("content") String content,
                           @FormParam("userId") int userId) {//TODO : remove the userId and use JAAS
        Tweet tweet;
        try {
            tweet = service.create(content, userId);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (tweet == null) return Response.serverError().build();
        return Response.created(getCreatedLink(tweet)).entity(tweet).build();
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        Tweet tweet;
        try {
            tweet = service.findById(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        if (tweet == null) return Response.serverError().build();
        return Response.ok().entity(tweet).build();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllTweets() {
        List<Tweet> tweets;
        try {
            tweets = service.getAllTweets();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        final GenericEntity<List<Tweet>> entity = new GenericEntity<List<Tweet>>(tweets) {
        };
        return Response.ok().entity(entity).build();
    }

    @GET
    @Produces("application/json")
    public Response getPersonalTweets(@QueryParam("id") int id) {
        List<Tweet> tweets;
        try {
            tweets = service.getPersonalTweets(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        final GenericEntity<List<Tweet>> entity = new GenericEntity<List<Tweet>>(tweets) {
        };
        return Response.ok().entity(entity).build();
    }


    @GET
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("content") String content) {
        List<Tweet> tweets;
        try {
            tweets = service.search(content);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        final GenericEntity<List<Tweet>> entity = new GenericEntity<List<Tweet>>(tweets) {
        };
        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("/remove/{Id}")
    @Produces("application/json")
    public Response delete(@PathParam("Id") int id) {
        boolean success;
        try {
            success = service.remove(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }

    @GET
    @Path("/heart/{Id}")
    @Produces("application/json")
    public Response heart(@PathParam("Id") int id, @QueryParam("userId") int userId) {
        boolean success;
        try {
            success = service.hearth(id, userId);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().entity(new Bool(success)).build();
    }


    private URI getCreatedLink(Tweet entity) {

        return uriInfo.getAbsolutePathBuilder().path(entity.getId() + "").build();
    }
}

