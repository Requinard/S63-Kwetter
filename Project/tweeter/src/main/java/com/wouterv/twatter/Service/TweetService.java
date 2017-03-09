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
public class TweetService {

    @Inject
    @JPA
    ITweetDAO tweetDAO;

    @Inject
    @JPA
    IAccountDAO accountDAO;

    public List<Tweet> getAllTweets() {
        return tweetDAO.getAll();
    }
    public Tweet postTweets(@FormParam("content") String content,
                            @FormParam("userId") int userId) {//TODO : remove the userId and use JAAS
        Account account = accountDAO.findById(userId);
        Tweet tweet = new Tweet(content,account);
        tweetDAO.create(tweet);
        return tweet;
    }
    public List<Tweet> getPersonalTweets() {
        return tweetDAO.getPersonalTweets();
    }
}
