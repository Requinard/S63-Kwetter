package com.wouterv.twatter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
//@Entity
//@XmlRootElement
@Entity
@XmlRootElement
public class Tweet extends TweeterModel {


    private String content;
    @OneToOne
    private Account postAccount;
    @OneToMany
    private List<Account> hearted;

    public Tweet() {}

    public Tweet(String content, Account postAccount) {
        this.content = content;
        this.postAccount = postAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getPostAccount() {
        return postAccount;
    }

    public void setPostAccount(Account postAccount) {
        this.postAccount = postAccount;
    }

//    public List<Tag> getTags() {
//        return Tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//        Tags = tags;
//    }

    public List<Account> getHearted() {
        return hearted;
    }

    public void setHearted(List<Account> hearted) {
        this.hearted = hearted;
    }

    //todo : maybe only use the id's of the accounts
    public boolean addHearted(Account account) {
        if (this.hearted.contains(account)) {
            return false;
        }
        this.hearted.add(account);
        return true;
    }

    public boolean removeHearted(Account account) {
        try {
            this.hearted.remove(account);
        } catch (Exception e) {
            return false; //todo : check if this would ever be called
        }
        return true;
    }

}
