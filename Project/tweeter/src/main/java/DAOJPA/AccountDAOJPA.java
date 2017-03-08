package DAOJPA;

import Annotations.JPA;
import DAO.DaoFacade;
import DAO.IAccountDAO;
import Models.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@Stateless
@JPA
public class AccountDAOJPA extends DaoFacade<Account> implements IAccountDAO {

    @PersistenceContext
    EntityManager em;

    public AccountDAOJPA() {
        super(Account.class);
    }

    @Override
    public Account findByName(String name) {
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
