package DAO;

import Util.DatabaseCleaner;
import com.wouterv.twatter.DAO_JPA.AccountDAOJPA;
import com.wouterv.twatter.DAO_JPA.TypeDOAJPA;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Type;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */
public class AccountDAOJPATest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TwatterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private AccountDAOJPA accountDao;
    private TypeDOAJPA typeDao;
    private Account account1;
    private Account account2;

    public AccountDAOJPATest() {
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

        accountDao = new AccountDAOJPA();
        accountDao.setEm(em);

        typeDao = new TypeDOAJPA();
        typeDao.setEm(em);

        account1 = new Account("foo", "", "", "", "");
        account1.setPassword("a");
        account2 = new Account("bar", "A", "", "", "");
        account2.setPassword("a");
        tx.begin();
        accountDao.create(account1);
        accountDao.create(account2);
        tx.commit();
        assertThat(accountDao.getAll().size(), is(2));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findByUserNameTest() {
        Account acc = accountDao.findByUserName("foo");
        Assert.assertNotNull(acc);
    }

    @Test
    public void searchTest() {
        List<Account> accs = accountDao.search("f");
        Assert.assertEquals(1, accs.size());
        accs = accountDao.search("f o o");
        Assert.assertEquals(1, accs.size());
    }

    @Test
    public void Following() {
        account1.addFollowing(account2);
        tx.begin();
        accountDao.edit(account1);
        tx.commit();
        Account acc = accountDao.findById(account1.getId());
        Assert.assertEquals(1, acc.getFollowing().size());
    }

    @Test
    public void Roles() {
        tx.begin();
        Type type = typeDao.findOrCreate("test");
        accountDao.addRole(type, account1.getId());
        tx.commit();
        Assert.assertEquals(1, accountDao.findById(account1.getId()).getGroups().size());
        tx.begin();
        accountDao.removeRole(type, account1.getId());
        tx.commit();
        Assert.assertEquals(0, account1.getGroups().size());
    }
}
