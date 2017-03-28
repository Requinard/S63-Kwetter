package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;
import com.wouterv.twatter.Service.TweetService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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


    public Tweet create(){
        throw  new NotImplementedException();
    }
    public List<Tweet> getAll() {
        return service.getAllTweets();
    }
    public List<Tweet> getPersonal(){
        Account account = accountService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        return service.getPersonalTweets(account.getId());
    }
    public void Heart(Tweet tweet,Account account){
        service.hearth(tweet.getId(),account.getId());
    }

    public boolean hasHearted(Tweet tweet, Account loggedIn){
        for (Account a:tweet.getHearted()) {
            if (a.getId() == loggedIn.getId()){
                return true;
            }
        }
        return false;
    }

}
