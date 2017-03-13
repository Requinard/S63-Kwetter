package Domain;

import Util.DatabaseCleaner;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */
public class DomainMappingTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TwatterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private static Account account;

    public DomainMappingTest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(DomainMappingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() {
    }
    @Test
    public void verifyAccountMapping(){
        account = new Account("username","email","bio","first_name","last_name");
        try {
            tx.begin();
            em.persist(account);
            tx.commit();
            // Do something you expect to fail.
            Assert.fail();
        }catch (Exception e) { // Expected exception type.
            Assert.assertTrue(e.getMessage().contains("ERROR: null value in column \"password\" violates not-null constraint"));
        }
        account = new Account("username","email","bio","first_name","last_name");
        account.setPassword("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");//test
        tx.begin();
        em.persist(account);
        tx.commit();

    }
    @Test
    public void verifyTweetMapping() {
        account = new Account("username","email","bio","first_name","last_name");
        account.setPassword("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");//test
        tx.begin();
        em.persist(account);
        tx.commit();

        Tweet tweet = new Tweet("content",account);
        try {
            tx.begin();
            em.persist(tweet);
            tx.commit();
            // Do something you expect to fail.
            Assert.fail();
        }catch (Exception e) { // Expected exception type.
            Assert.assertTrue(e.getMessage().contains("ERROR: null value in column \"date\" violates not-null constraint"));
        }
        tweet.setDate(new Date());
        tx.begin();
        em.persist(tweet);
        tx.commit();
    }
}