package Models;

//import javax.persistence.Entity;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wouter on 18-2-2017.
 */
@Entity
@XmlRootElement
public class Account implements Serializable{

    @Id
//    @GeneratedValue(generator="ACCOUNT_SEQ",strategy=GenerationType.SEQUENCE)
//    @GeneratedValue
//    @SequenceGenerator(name="ACCOUNT_seq_gen", sequenceName="ACCOUNT_SEQ,",allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.AUTO, generator="ACCOUNT_seq_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String FirstName;

    private String LastName;

    @OneToMany
    private List<Tweet> Tweets;

    @OneToMany
    private List<Account> Following;

    public Account() {
    }

    public Account(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

//    @javax.persistence.Id
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public List<Tweet> getTweets() {
        return Tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        Tweets = tweets;
    }

    public List<Account> getFollowing() {
        return Following;
    }

    public void setFollowing(List<Account> following) {
        Following = following;
    }

    @Override
    public String toString() {
        return this.FirstName + " " + this.LastName;
    }
}
