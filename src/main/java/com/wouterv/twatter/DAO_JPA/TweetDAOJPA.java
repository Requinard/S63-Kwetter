package com.wouterv.twatter.DAO_JPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.ITweetDAO;
import com.wouterv.twatter.Models.Tweet;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@NamedQueries({
        @NamedQuery(name = "tweetdao.getPersonalTweets", query = "select t from  Tweet t, Account a  where a.Id = :id and (t.postAccount.Id in (select b.Id from a.following b) or t.postAccount.Id = :id ) order by t.Date desc "),
        @NamedQuery(name = "tweetdao.getPostedTweets", query = "select t from  Tweet t where t.postAccount.Id = :id order by t.Date desc"),
        @NamedQuery(name = "tweetdao.search", query = "select t from  Tweet t where t.content like :content order by t.Date desc")})
@Stateless
@JPA
public class TweetDAOJPA extends DaoFacade<Tweet> implements ITweetDAO {

    @PersistenceContext
    EntityManager em;


    public TweetDAOJPA() {
        super(Tweet.class);
    }

    @Override
    public void create(Tweet entity) {
        entity.setDate(new Date());
        super.create(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Tweet> getPersonalTweets(int userId) {
        Query q = getEntityManager().createQuery(
                "select t from  Tweet t, Account a  " +
                        "where a.Id = :id and (t.postAccount.Id in (select b.Id from a.following b) or t.postAccount.Id = :id ) order by t.Date desc ");
        q.setParameter("id", userId);
        List result = q.getResultList();
        return result;
    }

    @Override
    public List<Tweet> getPostedTweets(int userId) {
        Query q = getEntityManager().createQuery(
                "select t from  Tweet t where t.postAccount.Id = :id order by t.Date desc");
        q.setParameter("id", userId);
        List result = q.getResultList();
        return result;
    }

    @Override
    public List<Tweet> search(String content) {
        Query q = getEntityManager().createQuery(
                "select t from  Tweet t where t.content like :content order by t.Date desc");
        q.setParameter("content", "%" + content + "%");
        List result = q.getResultList();
        return result;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
