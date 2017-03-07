package Controllers;

import Models.Account;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TestDingetje implements ITestDingetje{

    @PersistenceContext
    EntityManager em;


    public String getStuff() {

//        em.getTransaction().begin();
        Account test = new Account("first_test", "last_test");
        em.persist(test);
//        em.getTransaction().commit();
        String test2 = "stuff - not null";

        Query q = em.createQuery("SELECT u FROM Account u");
        List<Account> userList = q.getResultList();
        for (Account user : userList) {
            test2 += user.toString();
        }

        if(em ==null){
            test2 = "stuff - null";
        }
        return test2;
    }
}
