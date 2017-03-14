package com.wouterv.twatter.DAO;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
public abstract class DaoFacade<T> {

    private final Class<T> entityClass;

    public DaoFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T findById(int id) {
        T entity=null;
        try {
            entity = getEntityManager().find(entityClass, id);
        }catch (NotFoundException e){
            System.out.println("Could not fin Entity of type "+ entityClass.getName()+ " with ID :"+id);
        }
        return entity;
    }

    public List<T> getAll() {
        return getEntityManager().createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
    }
}
