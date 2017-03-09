package com.wouterv.twatter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 18-2-2017.
 */
@Entity
@XmlRootElement
public class Account extends TweeterModel{

    public enum Groups{ADMIN, USER}

    @Column(unique = true)
    private String userName;
    private String email;
    private String bio;
    private String firstName;
    private String lastName;
    private Groups group;

    @OneToMany
    private List<Tweet> tweets;

    @OneToMany
    private List<Account> following;

    public Account() {
    }

    public Account(String userName, String email, String bio, String firstName, String lastName, Groups group) {
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Account> getFollowing() {
        return following;
    }

    public void setFollowing(List<Account> following) {
        this.following = following;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
