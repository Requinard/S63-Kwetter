package com.wouterv.twatter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 18-2-2017.
 */
@Entity
@XmlRootElement
public class Account extends TweeterModel{

//    public enum Groups{ADMIN, USER}

    @Column(unique = true,nullable = false)
    private String userName;
    @Column(unique = true,nullable = false)
    private String email;
    private String bio;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;//TODO : temporary password

    @ManyToMany(mappedBy = "accounts")
    private List<Type> groups;

    @OneToMany
    private List<Tweet> tweets;

    @OneToMany
    private List<Account> following;

    public Account() {
    }

    public Account(String userName, String email, String bio, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
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
        if(following == null){return new ArrayList<Account>();}
        return following;
    }

    public void setFollowing(List<Account> following) {
        this.following = following;
    }
    public void addFollowing(Account tofollow) {
        if(following == null){//TODO : is this neccesary ?
            following = new ArrayList<>();
        }
        following.add(tofollow);
    }
    public void removeFollowing(Account toUnfollow) {
        if(following == null){//TODO : is this neccesary ?
            following = new ArrayList<>();
        }
        following.remove(toUnfollow);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Type> getGroups() {
        return groups;
    }

    public void setGroups(List<Type> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
