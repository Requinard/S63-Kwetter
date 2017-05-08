package com.wouterv.twatter.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wouterv.twatter.EndPoints.Controller.TweetController;
import org.glassfish.jersey.linking.InjectLink;

import javax.persistence.*;
//import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "tweetdao.getPersonalTweets",
                query = "select t from  Tweet t, Account a  where a.Id = :id " +
                        "and (t.postAccount.Id in (select b.Id from a.following b) or t.postAccount.Id = :id ) " +
                        "order by t.Date desc"),
        @NamedQuery(name = "tweetdao.getPostedTweets", query = "select t from  Tweet t where t.postAccount.Id = :id order by t.Date desc"),
        @NamedQuery(name = "tweetdao.search", query = "select t from  Tweet t where t.content like :content order by t.Date desc")})
public class Tweet extends TweeterModel {

//    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
//    private Link self;

    @InjectLink(resource = TweetController.class)
    URI link;

    @Column(length = 140)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date Date;

//    @ManyToOne(optional = false)
    @ManyToOne
    @JsonBackReference(value = "tweets")
//    @JsonBackReference
    private Account postAccount;

    @OneToMany
    @JsonBackReference
    private List<Account> hearted;

//    @ManyToOne
//    @JsonManagedReference
//    private List<Hashtag> hashtags;

    @ManyToMany(mappedBy = "mentions")
    @JsonBackReference
    private List<Account> mentions;


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

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

//    @XmlTransient
    public Account getPostAccount() {
        return postAccount;
    }

    public void setPostAccount(Account postAccount) {
        this.postAccount = postAccount;
    }

    public List<Account> getHearted() {
        if (hearted == null) {
            return new ArrayList<>();
        }
        return hearted;
    }

    public void setHearted(List<Account> hearted) {
        this.hearted = hearted;
    }

    //todo : maybe only use the id's of the accounts
    public boolean addHearted(Account account) {
        if (hearted == null) {
            hearted = new ArrayList<>();
        }
        if (this.hearted.contains(account)) {
            return false;
        }
        this.hearted.add(account);
        return true;
    }

    public boolean removeHearted(Account account) {
        if (hearted == null) {
            hearted = new ArrayList<>();
        }
        try {
            this.hearted.remove(account);
        } catch (Exception e) {
            return false; //todo : check if this would ever be called
        }
        return true;
    }

//    public List<Hashtag> getHashtags() {
//        return hashtags;
//    }
//
//    public boolean addHashtag(Hashtag hashtag) {
//        if(hashtags == null){hashtags = new ArrayList<>();}
//        return hashtags.add(hashtag);
//    }
//
//    public boolean removeHashtag(Hashtag hashtag) {
//        if(hashtags == null){hashtags = new ArrayList<>();}
//        return hashtags.remove(hashtag);
//    }
//
//    public void setHashtags(List<Hashtag> hashtags) {
//        this.hashtags = hashtags;
//    }

//    @XmlTransient
    public List<Account> getMentions() {
        return mentions;
    }

    public boolean addMention(Account account) {
        if(mentions == null){mentions = new ArrayList<>();}
        return mentions.add(account);
    }

    public boolean removeMention(Account account) {
        return mentions.remove(account);
    }

    public void setMentions(List<Account> mentions) {
        this.mentions = mentions;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }
}
