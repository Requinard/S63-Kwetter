package com.wouterv.twatter.DAOJPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Tweet;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        int userId = 1;
        Query q = getEntityManager().createQuery(
                "select t from  Tweet t, Account a  " +
                        "where a.Id = :id and " +
                        "t.postAccount.Id in (select b.Id from a.following b) " +
                        "order by t.Date desc ");
        q.setParameter(":id", userId);
        List result = q.getResultList();
        return result;
    }

}
