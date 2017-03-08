package DAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
public interface IKwetterDAO<T> {
    EntityManager getEntityManager();
    void create(T entity);
    void remove(T entity);
    void edit(T entity);
    List<T> getAll();
    T findById(Object id);
}
