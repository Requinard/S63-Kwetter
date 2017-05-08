package DAO;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */

import Util.DatabaseCleaner;
import com.wouterv.twatter.DAO.DAO_JPA.AccountDAOJPA;
import com.wouterv.twatter.DAO.DAO_JPA.TweetDAOJPA;
import com.wouterv.twatter.DAO.DAO_JPA.TypeDOAJPA;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */
public class TweetDAOJPATest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TwatterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private TweetDAOJPA tweetDao;
    private AccountDAOJPA accountDao;
    private Account account1;
    private Account account2;
    private Tweet tweet;

    public TweetDAOJPATest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAOJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tweetDao= new TweetDAOJPA();
        tweetDao.setEm(em);

        accountDao = new AccountDAOJPA();
        accountDao.setEm(em);


        account1 = new Account("foo", "", "", "", "");
        account1.setPassword("a");
        account2 = new Account("bar", "A", "", "", "");
        account2.setPassword("a");
        tx.begin();
        accountDao.create(account1);
        accountDao.create(account2);
        tx.commit();
        assertThat(accountDao.getAll().size(), is(2));

        tx.begin();
        tweet = new Tweet("content",account1);
        tweetDao.create(tweet);
        tx.commit();
    }

    @After
    public void tearDown() {
    }
//        List<Tweet> search(String content);

    @Test
    public void personalTweets(){
        tx.begin();
        account2.addFollowing(account1);
        accountDao.edit(account2);
        tx.commit();
        List<Tweet> tweets =tweetDao.getPersonalTweets(account2.getId());
        Assert.assertEquals(1,tweets.size());

        tx.begin();
        tweet = new Tweet("Content fom account2",account2);
        tweetDao.create(tweet);
        tx.commit();
        tweets =tweetDao.getPersonalTweets(account2.getId());
        Assert.assertEquals(2,tweets.size());
    }
    @Test
    public void postedTweets(){
        List<Tweet> tweets = tweetDao.getPostedTweets(account1.getId());
        Assert.assertEquals(1,tweets.size());
    }
    @Test
    public void search(){
        List<Tweet> tweets = tweetDao.search("content");
        Assert.assertEquals(1,tweets.size());
    }
}

