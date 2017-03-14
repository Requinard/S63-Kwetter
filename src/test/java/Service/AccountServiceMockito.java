package Service;

import com.wouterv.twatter.DAO_COL.AccountDAOCol;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Wouter Vanmulken on 14-3-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceMockito {

    static AccountService service;
//    static AccountDAOCol accountdao = new AccountDAOCol();
    @Mock
    AccountDAOCol accountdao;

    Account account;

    @Before
    public void Before(){
        service = new AccountService();
        service.setAccountDAO(accountdao);
        account = new Account("username", "email", "bio", "firstname", "lastname");
        account.setId(22);
        account.setPassword("a");
        when(accountdao.findById(1)).thenReturn(account);
    }
    @Test
    public void CreateAndSearchTest() {
        service.findByID(1);
        verify(accountdao, times(1)).findById(1);
    }
    @Test
    public void GetAllTest() {
        service.getAllAccounts();
        verify(accountdao, times(1)).getAll();
    }
    @Test
    public void FindByUserNameTest() {
        when(service.findByUsername(account.getUserName())).thenReturn(account);
        Account found = service.findByUsername(account.getUserName());
        assertThat(found, is(account));
    }
}
