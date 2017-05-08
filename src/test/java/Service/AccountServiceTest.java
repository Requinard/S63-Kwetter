package Service; /**
 * Created by Wouter Vanmulken on 10-3-2017.
 */
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wouterv.twatter.DAO.DAO_COL.AccountDAOCol;
import com.wouterv.twatter.DAO.DAO_COL.TypeDAOCol;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;



public class AccountServiceTest {

    static AccountService service = new AccountService();
    static AccountDAOCol accountdao = new AccountDAOCol();
    static TypeDAOCol typedao = new TypeDAOCol();
    static Account account;

    @BeforeClass
    public static void BeforeClass(){
        service.setAccountDAO(accountdao);
        service.setTypeDAO(typedao);
    }
    @Before
    public void Before(){
        accountdao.setAccounts(new CopyOnWriteArrayList<>());
        accountdao.setId(0);
    }
    @Test
    public void CreateAndSearchTest() {

        service.create("username", "email", "bio", "firstname", "lastname","pass");
        account = service.findByUsername("username");
        Assert.assertNotNull(account);
        Assert.assertEquals(0,account.getId());
        List<Account> accountList = service.search("u");
        Assert.assertTrue(accountList.contains(account));
    }
    @Test
    public void DeleteTest(){
        service.create("username", "email", "bio", "firstname", "lastname","pass");
        Assert.assertEquals(1,service.getAllAccounts().size());
        Assert.assertTrue(service.remove(0));
        Assert.assertEquals(0,service.getAllAccounts().size());

    }
    @Test
    public void FollowUnFollowTest(){
        Account acc1 = service.create("username", "email", "bio", "firstname", "lastname","pass");
        Account acc2 = service.create("username2", "email2", "bio2", "firstname2", "lastname2","pass");
        service.follow(acc1.getId(),acc2.getId());
        Assert.assertEquals(1,acc2.getFollowing().size());
        service.unFollow(acc1.getId(),acc2.getId());
        Assert.assertEquals(0,acc2.getFollowing().size());
    }
    @Test
    public void TypeTest(){
        Account acc1 = service.create("username", "email", "bio", "firstname", "lastname","pass");
        Assert.assertTrue(service.addRole("admin",acc1.getId()));
    }
}
