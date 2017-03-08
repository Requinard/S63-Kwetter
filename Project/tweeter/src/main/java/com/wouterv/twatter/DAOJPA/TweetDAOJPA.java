package com.wouterv.twatter.DAOJPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Tweet;

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
