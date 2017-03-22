package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.TweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 21-3-2017.
 */
@Named
@RequestScoped
public class TweetsBean {
    @Inject
    TweetService service;

    public List<Tweet> getAll() {
        return service.getAllTweets();
    }

    public List<Tweet> getPersonal(int userId) {
        return service.getPersonalTweets(userId);
    }

}
