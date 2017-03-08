package com.wouterv.twatter.Service;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */

@Stateless
@Path("/tweetsservice")
public class TweetService {

    @Inject
    @JPA
    ITweetDAO tweetDAO;

    @Inject
    @JPA
    IAccountDAO accountDAO;

    @GET
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getTweets() {
        List<Tweet> tweets = tweetDAO.getAll();
        return tweets;
    }
    @POST
//    @Path("/tweet/{userId}/{content}")
    @Path("/tweet")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Tweet postTweets(@FormParam("content") String content,
                            @FormParam("userId") int userId) {
        Account account = accountDAO.findById(userId);
        Tweet tweet = new Tweet(content,account);
        tweetDAO.create(tweet);
        return tweet;
    }
}
