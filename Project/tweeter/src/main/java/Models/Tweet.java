package Models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Entity
@XmlRootElement
public class Tweet implements Serializable {

    @Id
//    @GeneratedValue(generator="TWEET_SEQ",strategy=GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    private String Content;
    @OneToOne
    private Account PostAccount;
    @OneToMany
    private List<Tag> Tags;
    @OneToMany
    private List<Account> Hearted;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Account getPostAccount() {
        return PostAccount;
    }

    public void setPostAccount(Account postAccount) {
        PostAccount = postAccount;
    }

    public List<Tag> getTags() {
        return Tags;
    }

    public void setTags(List<Tag> tags) {
        Tags = tags;
    }

    public List<Account> getHearted() {
        return Hearted;
    }

    public void setHearted(List<Account> hearted) {
        Hearted = hearted;
    }

    //todo : maybe only use the id's of the accounts
    public boolean addHearted(Account account) {
        if (this.Hearted.contains(account)) {
            return false;
        }
        this.Hearted.add(account);
        return true;
    }

    public boolean removeHearted(Account account) {
        try {
            this.Hearted.remove(account);
        } catch (Exception e) {
            return false; //todo : check if this would ever be called
        }
        return true;
    }

}
