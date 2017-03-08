package DAOCol;

import DAO.DaoFacade;
import DAO.IAccountDAO;
import Models.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */


@Stateless
@Default
public class AccountDAOCol extends DaoFacade<Account> implements IAccountDAO {

    CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<>();

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public void create(Account account) {
        accounts.add(account);
    }

    @Override
    public void remove(Account account) {
        accounts.remove(account);
    }

    @Override
    public ArrayList<Account> getAll() {
        return new ArrayList(accounts);
    }

    @Override
    public Account findById(Object id) {
        if(!(id instanceof Integer)){
            return null;
        }
        for (Account a : accounts){
            if(a.getId() == (int)id){
                return a;
            }
        }
        return null;
    }


    @PostConstruct
    public void init(){
        System.out.println("StudentDaoColl");
    }


    public AccountDAOCol() {
        super(Account.class);
    }

    @Override
    public Account findByUserName(String name) {
        for (Account account : accounts) {
            if ((account.getFirstName()+account.getLastName()).contains(name)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> search(String name) {
        ArrayList acc = new ArrayList();
        for (Account account : accounts) {
            if ((account.getFirstName()+account.getLastName()).contains(name)) {
                acc.add(account);
            }
        }
        return acc;
    }
}


