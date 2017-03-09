package com.wouterv.twatter.Controller;

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
        return service.postTweets(content,userId);
    }
    @GET
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getPersonalTweets() {
        return service.getPersonalTweets();
    }
}

