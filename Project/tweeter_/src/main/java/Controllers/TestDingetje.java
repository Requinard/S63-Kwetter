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
        String result = "Restful example : ";

        Query q = em.createQuery("SELECT u FROM Account u");
        List<Account> userList = q.getResultList();
        for (Account user : userList) {
            result += user;
        }

        String test2 = "stuff - not null";
        if(em ==null){
            test2 = "stuff - null";
        }
        return test2;
    }
}
