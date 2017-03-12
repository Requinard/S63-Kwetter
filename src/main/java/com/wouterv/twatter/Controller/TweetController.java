package com.wouterv.twatter.Controller;

import com.wouterv.twatter.Bool;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 9-3-2017.
 */
@Stateless
@Path("/tweets")
public class TweetController {

    @Inject
    TweetService service;

    @GET
    @Path("/allTweets")
    @Produces("application/json")
    public List<Tweet> getAllTweets() {
        return service.getAllTweets();
    }

    @POST
    @Path("/tweet")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Tweet postTweets(@FormParam("content") String content,
                            @FormParam("userId") int userId) {//TODO : remove the userId and use JAAS
        return service.create(content, userId);
    }

    @GET
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getPersonalTweets(@QueryParam("Id") int Id) {
        return service.getPersonalTweets(Id);
    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public List<Tweet> search(@QueryParam("content") String content) {
        if (content.isEmpty()) {
            throw new BadRequestException("The parameter 'content' was null.");
        }
        return service.search(content);
    }

    @GET
    @Path("/remove/{Id}")
    @Produces("application/json")
    public Bool delete(@PathParam("Id") int id) {
        return new Bool(service.remove(id));
    }

    @GET
    @Path("/heart/{Id}")
    @Produces("application/json")
    public Bool heart(@PathParam("Id") int id, @QueryParam("userId") int userId) {
        boolean result = service.hearth(id, userId);
        return new Bool(result);
    }
}

