package com.wouterv.twatter.DAOJPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Override
    public List<Tweet> getPersonalTweets() {
        Account a = new Account();
        getEntityManager().createQuery(
                "select t from  Tweet t, Account a  ");
    }
}
