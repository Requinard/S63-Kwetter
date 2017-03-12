/**
 * Created by Wouter Vanmulken on 10-3-2017.
 */
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.ws.policy.AssertionSet;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {

    AccountService service;
    @Before
    public void Before(){
        service = new AccountService();
    }
    @Test
    public void Test1(){
        System.out.println("Hello world");
    }
    @Test
    public void Test2(){
//        service.enti
        service.create("username","email","bio","firstname","lastname");
        Account a = service.getAccountByUsername("username");
        Assert.assertNotNull(a);
        List<Account> accountList = service.search("u");
        Assert.assertTrue(accountList.contains(a));


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
}
