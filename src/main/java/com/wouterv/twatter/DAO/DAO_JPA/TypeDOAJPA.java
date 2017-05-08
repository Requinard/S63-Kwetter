package com.wouterv.twatter.DAO.DAO_JPA;

import com.wouterv.twatter.Utils.JPA;
import com.wouterv.twatter.DAO.DAO.ITypeDAO;
import com.wouterv.twatter.Models.Type;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wouter Vanmulken on 12-3-2017.
 */
@Stateless
@JPA
public class TypeDOAJPA implements ITypeDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public Type findOrCreate(String name) {
        Type entity;
        try {
            entity = em.find(Type.class, name);
            return entity;
        } catch (Exception e) {
            entity = new Type(name);
            em.persist(entity);
            return entity;
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
