package fr.polytech.messager.doa;

import fr.polytech.messager.doa.impl.PageImpl;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class HibernateDao<Id extends Serializable, T extends Identifiable<Id>> implements Serializable {

    protected Session SESSION;

    protected final String className;
    protected final Class<T> clazz;
    protected final String tableName;

    public HibernateDao(Class<T> clazz) {
        this.clazz = clazz;
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null || tableAnnotation.name().isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        } else {
            tableName = tableAnnotation.name();
        }
        className = clazz.getSimpleName();
        getSession();
    }

    public boolean isEmpty() {
        System.out.println("[DEBUG] isEmpty " + tableName);
        return ((BigInteger) getSession().createSQLQuery("SELECT EXISTS (SELECT NULL FROM " + tableName + ")").uniqueResult()).intValue() == 0;
    }

    public void save(T toSave) {
        Transaction transaction = getSession().beginTransaction();
        getSession().save(toSave);
        transaction.commit();
    }

    public Optional<T> getById(Id id) {
        return Optional.ofNullable(getSession().get(clazz, id));
    }

    public void remove(T toRemove) {
        Transaction transaction = getSession().beginTransaction();
        getSession().delete(toRemove);
        transaction.commit();
    }

    public List<T> getAll() {
        return getSession().createQuery("FROM " + className, clazz).list();
    }

    protected Page<T> getPage(String hqlQuery, int pageSize, int pageNumber) {
        final Query<T> query = getSession().createQuery(hqlQuery, clazz);
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        final List<T> res = query.list();
        Long totalResults = (Long) getSession().createQuery("select count (t.id) from " + className + " t").uniqueResult();
        int numberOfPages = Double.valueOf(Math.ceil(((double)totalResults / (double) pageSize))).intValue();
        return new PageImpl<>(res, pageSize, pageNumber, totalResults, numberOfPages);
    }

    public Session getSession() {
        if (SESSION == null || ! SESSION.isOpen()) {
            SESSION = new Configuration().configure().buildSessionFactory().openSession();
        }
        return SESSION;
    }

    protected Optional<T> getOne(Query<T> query) {
        return Optional.ofNullable(query.uniqueResult());
    }
}
