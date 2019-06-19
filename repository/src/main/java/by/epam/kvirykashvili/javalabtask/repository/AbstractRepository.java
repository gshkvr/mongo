package by.epam.kvirykashvili.javalabtask.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractRepository<T extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    Class<T> clazz;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

}
