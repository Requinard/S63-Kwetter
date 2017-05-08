package Service;

import com.wouterv.twatter.DAO.DAO_COL.AccountDAOCol;
import com.wouterv.twatter.DAO.DAO_COL.TweetDAOCol;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;
import com.wouterv.twatter.Service.TweetService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by Wouter Vanmulken on 12-3-2017.
 */

public class TweetServiceTest {
    static TweetService service = new TweetService();
    static AccountService accountService = new AccountService();
    static TweetDAOCol tweetdao = new TweetDAOCol();
    static AccountDAOCol accountdao = new AccountDAOCol();
    static Tweet tweet;
    static Account acc1;
    static Account acc2;

    @BeforeClass
    public static void BeforeClass(){
        service.setAccountDAO(accountdao);
        service.setTweetDAO(tweetdao);
        accountService.setAccountDAO(accountdao);
        tweetdao.setAccountService(accountdao);
    }
    @Before
    public void Before(){
        accountdao.setAccounts(new CopyOnWriteArrayList<>());
        tweetdao.setTweets(new CopyOnWriteArrayList<>());
        acc1 = accountService.create("username", "email", "bio", "firstname", "lastname","pass");
        acc2 = accountService.create("username2", "email2", "bio2", "firstname2", "lastname2","pass");
    }
    @Test
    public void CreateAndRemoveAndGetAll(){
        tweet = service.create("Test1",acc1.getId());
        Assert.assertEquals(1,service.getAllTweets().size());
        service.remove(tweet.getId());
        Assert.assertEquals(0,service.getAllTweets().size());
    }
    @Test
    public void PersonalTweets(){
        service.create("Test",acc2.getId());
        Assert.assertEquals(0,service.getPersonalTweets(acc1.getId()).size());
        Assert.assertTrue(accountService.follow(acc2.getId(),acc1.getId()));//TODO : Can't test this because of the getEntityManager.merge() that doesn't have a entitymanager
        Assert.assertEquals(1,service.getPersonalTweets(acc1.getId()).size());
    }
    @Test
    public void Hearting(){
        Tweet t = service.create("Test",acc1.getId());
        service.hearth(t.getId(),acc2.getId());
        List<Account> accounts = t.getHearted();
        Account foundAccount = (Account) accounts.stream().filter(x -> x.getId() == acc2.getId()).findFirst().get();
        Assert.assertEquals(acc2.getId(),foundAccount.getId());
        service.hearth(t.getId(),acc2.getId());
        accounts = t.getHearted();
        Assert.assertEquals(0,accounts.size());
    }
    @Test
    public void Search(){
        service.create("Test",acc1.getId());
        List<Tweet> tweets = service.search("e");
        Assert.assertEquals(1,tweets.size());
    }


}
