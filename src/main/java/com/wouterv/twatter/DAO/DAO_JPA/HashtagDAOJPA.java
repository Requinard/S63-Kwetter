package com.wouterv.twatter.DAO.DAO_JPA;

import com.wouterv.twatter.Utils.JPA;
import com.wouterv.twatter.DAO.DAO.DaoFacade;
import com.wouterv.twatter.DAO.DAO.IHashtagDAO;
import com.wouterv.twatter.DAO.DAO.ITypeDAO;
import com.wouterv.twatter.Models.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wouter Vanmulken on 2-4-2017.
 */
@Stateless
@JPA
public class HashtagDAOJPA extends DaoFacade<Hashtag> implements IHashtagDAO {
    @PersistenceContext
    EntityManager em;

    public HashtagDAOJPA() {
        super(Hashtag.class);
    }

    @Override
    public Hashtag findOrCreate(String name) {
        Hashtag entity;
            entity = em.find(Hashtag.class, name);
            if(entity == null){
                entity = new Hashtag(name);
                em.persist(entity);
            }
            return entity;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
