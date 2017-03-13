package com.wouterv.twatter.Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Entity
@XmlRootElement
public class Tweet extends TweeterModel {

    private String content;
    private Date Date;

    @OneToOne
    private Account postAccount;
    @OneToMany
    private List<Account> hearted;

    public Tweet() {
    }

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

    public java.util.Date getDate() {return Date;}

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Account getPostAccount() {
        return postAccount;
    }

    public void setPostAccount(Account postAccount) {
        this.postAccount = postAccount;
    }

    public List<Account> getHearted() {
        if(hearted == null){return new ArrayList<>();}
        return hearted;
    }

    public void setHearted(List<Account> hearted) {
        this.hearted = hearted;
    }

    //todo : maybe only use the id's of the accounts
    public boolean addHearted(Account account) {
        if(hearted == null){hearted = new ArrayList<>();}
        if (this.hearted.contains(account)) {
            return false;
        }
        this.hearted.add(account);
        return true;
    }

    public boolean removeHearted(Account account) {
        if(hearted == null){hearted = new ArrayList<>();}
        try {
            this.hearted.remove(account);
        } catch (Exception e) {
            return false; //todo : check if this would ever be called
        }
        return true;
    }

}
