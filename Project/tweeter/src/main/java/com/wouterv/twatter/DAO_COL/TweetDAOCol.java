package com.wouterv.twatter.DAO_COL;

import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Tweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@Stateless
@Default
public class TweetDAOCol implements ITweetDAO{
    CopyOnWriteArrayList<Tweet> tweets = new CopyOnWriteArrayList<>();

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public void create(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
    public void remove(Tweet tweet) {
        tweets.remove(tweet);
    }

    @Override
    public void edit(Tweet entity) {
        for (int i =0;i<tweets.size();i++) {
            if(tweets.get(i).getId() == entity.getId()){
                tweets.set(i,entity);
                continue;
            }
        }
    }

    @Override
    public ArrayList<Tweet> getAll() {
        return new ArrayList(tweets);
    }

    @Override
    public Tweet findById(int id) {
        for (Tweet t :this.tweets) {
            if(t.getId() == (int)id){
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Tweet> getPersonalTweets(int userId) {
        List<Tweet> tweets = new ArrayList<>();
        for (Tweet t :this.tweets) {
            if(t.getPostAccount().getId() == userId){
                tweets.add(t);
            }
        }
        return tweets;
    }

    @Override
    public List<Tweet> getPostedTweets(int id) {
        List<Tweet> tweets = new ArrayList<>();
        for (Tweet t :this.tweets) {
            if(t.getPostAccount().getId() == id){
                tweets.add(t);
            }
        }
        return tweets;
    }

    @Override
    public List<Tweet> search(String content) {
        List<Tweet> tweets = new ArrayList<>();
        for (Tweet t :this.tweets) {
            if(t.getContent().contains(content)){
                tweets.add(t);
            }
        }
        return tweets;
    }
}
