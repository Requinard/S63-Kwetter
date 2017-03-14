package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Bool;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
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
    @Path("/create")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response create(@FormParam("content") String content,
                           @FormParam("userId") int userId) {//TODO : remove the userId and use JAAS
        Tweet tweet;
        try {
            tweet = service.create(content, userId);
        }catch (Exception e){
            return Response.serverError().build();
        }
        if(tweet==null)return Response.serverError().build();
        return Response.created(getCreatedLink(tweet)).entity(tweet).build();
    }
    private URI getCreatedLink(Tweet entity){
        return uriInfo.getAbsolutePathBuilder().path(entity.getId() +"").build();
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(service.findById(id)).build();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllTweets() {

        return Response.ok().entity(service.getAllTweets()).build();
    }


    @GET
    @Path("/personal")
    @Produces("application/json")
    public Response getPersonalTweets(@QueryParam("Id") int Id) {
        return Response.ok().entity(service.getPersonalTweets(Id)).build();
    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("content") String content) {
        if (content.isEmpty()) {
            throw new BadRequestException("The parameter 'content' was null.");
        }
        return Response.ok().entity(service.search(content)).build();
    }

    @GET
    @Path("/remove/{Id}")
    @Produces("application/json")
    public Response delete(@PathParam("Id") int id) {
        return Response.ok().entity(new Bool(service.remove(id))).build();
    }

    @GET
    @Path("/heart/{Id}")
    @Produces("application/json")
    public Response heart(@PathParam("Id") int id, @QueryParam("userId") int userId) {
        return Response.ok().entity(new Bool(service.hearth(id, userId))).build();
    }
}

