package Service; /**
 * Created by Wouter Vanmulken on 10-3-2017.
 */
import java.util.List;

import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.DAO_COL.AccountDAOCol;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class AccountServiceTest {

    static AccountService service = new AccountService();
    static IAccountDAO accountdao = new AccountDAOCol();
    static Account account;

    @BeforeClass
    public static void Before(){
        service.setAccountDAO(accountdao);
    }
    @Test
    public void CreateAndSearchTest() {

        service.create("username", "email", "bio", "firstname", "lastname");
        account = service.getAccountByUsername("username");
        Assert.assertNotNull(account);
        Assert.assertEquals(0,account.getId());
        List<Account> accountList = service.search("u");
        Assert.assertTrue(accountList.contains(account));
    }
    @Test
    public void DeleteTest(){
        Assert.assertEquals(1,service.getAllAccounts().size());
        Assert.assertTrue(service.remove(0));
        Assert.assertEquals(0,service.getAllAccounts().size());

    }
    @Test
    public void FollowUnFollowTest(){
        Account acc1 = service.create("username", "email", "bio", "firstname", "lastname");
        Account acc2 = service.create("username2", "email2", "bio2", "firstname2", "lastname2");
        service.follow(acc1.getId(),acc2.getId());
        Assert.assertEquals(1,acc2.getFollowing().size());
        service.unFollow(acc1.getId(),acc2.getId());
        Assert.assertEquals(0,acc2.getFollowing().size());
    }

//        Account a=mock(Account.class);
////        when(a.)
//        //arrange
//        Iterator i=mock(Iterator.class);
//        when(i.next()).thenReturn("Hello").thenReturn("World");
//        //act
//        String result=i.next()+" "+i.next();
//        //assert
//        assertEquals("Hello World", result);
}
