package com.wouterv.twatter.DAO_JPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.ITypeDAO;
import com.wouterv.twatter.Models.Type;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wouter Vanmulken on 12-3-2017.
 */
@Stateless
@JPA
public class TypeDOAJPA implements ITypeDAO{
    @PersistenceContext
    EntityManager em;

    @Override
    public Type findOrCreate(String name) {
        Type entity = em.find(Type.class, name);
        if ( entity != null ) {
            return entity;
        } else {
            try {
                entity = new Type(name);
                em.persist(entity);
                return entity;
            } catch ( Exception e ) {
                throw new RuntimeException(e);
            }
        }
    }
}
