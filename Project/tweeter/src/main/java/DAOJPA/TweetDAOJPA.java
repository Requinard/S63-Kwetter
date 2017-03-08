package DAOJPA;

import Annotations.JPA;
import DAO.DaoFacade;
import DAO.IAccountDAO;
import DAO.ITweetDAO;
import Models.Account;
import Models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@Stateless
@JPA
public class TweetDAOJPA extends DaoFacade<Tweet> implements ITweetDAO {

    @PersistenceContext
    EntityManager em;


    public TweetDAOJPA() {
        super(Tweet.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
