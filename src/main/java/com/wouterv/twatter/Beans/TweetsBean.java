package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;
import com.wouterv.twatter.Service.TweetService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 21-3-2017.
 */
@Named
@SessionScoped
public class TweetsBean implements Serializable{
    @Inject
    TweetService service;
    @Inject
    AccountService accountService;

    public List<Tweet> getAll() {
        return service.getAllTweets();
    }

    public List<Tweet> getPersonal(){
        Account account = accountService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        return service.getPersonalTweets(account.getId());
    }
    public void Heart(int tweetId){
        Account account = accountService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        service.hearth(tweetId,account.getId());
    }

}
