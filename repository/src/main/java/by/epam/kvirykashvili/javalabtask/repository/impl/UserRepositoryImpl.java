package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.repository.AbstractHibernateRepository;
import by.epam.kvirykashvili.javalabtask.repository.UserRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractHibernateRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        setClazz(User.class);
    }

    @Override
    @ShowResult
    @ShowTime
    public List<User> readAll() {
        @SuppressWarnings("unchecked")
        List<User> users = getCurrentSession().createNamedQuery("readAllUsersNamed").getResultList();
        return users;
    }

    @Override
    public void delete(User user) {
        Query query = getCurrentSession().createNamedQuery("deleteUserNative");
        query.setParameter(1, user.getId());
        query.executeUpdate();
    }

    @Override
    public void addFavouriteTour(User user, Tour tourToAdd) {
        List<Tour> tours = user.getTours();
        if (!tours.contains(tourToAdd)) {
            tours.add(tourToAdd);
        }
        user.setTours(tours);
        getCurrentSession().merge(user);
    }

    @Override
    public void removeFavouriteTour(User user, Tour tourToRemove) {
        List<Tour> tours = user.getTours();
        for (Tour t : tours) {
            if(t.getId() == tourToRemove.getId()){
                tours.remove(t);
                break;
            }
        }
        user.setTours(tours);
        getCurrentSession().merge(user);
    }

    @Override
    public List getPage(int rows, int page) {
        Query query = getCurrentSession().createNamedQuery("readAllUsersNamed");
        query.setFirstResult((page-1) * rows);
        query.setMaxResults(rows);
        @SuppressWarnings("unchecked")
        List<User> user = query.getResultList();
        return user;
    }
}
