package com.wouterv.twatter.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 15-3-2017.
 */
@Entity
@XmlRootElement
public class Hashtag extends TweeterModel{
    @Column(unique = true)
    private String name;

    @ManyToMany
    private List<Tweet> tweets;

    public Hashtag() {}
    public Hashtag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
    public boolean addTweets(Tweet t) {
        if(tweets == null){tweets = new ArrayList<>();}
        return tweets.add(t);
    }
    public boolean removeTweets(Tweet t) {
        return tweets.remove(t);
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
