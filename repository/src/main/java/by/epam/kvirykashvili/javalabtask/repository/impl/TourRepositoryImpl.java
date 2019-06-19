package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.repository.AbstractHibernateRepository;
import by.epam.kvirykashvili.javalabtask.repository.TourRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourRepositoryImpl extends AbstractHibernateRepository<Tour> implements TourRepository {

    public TourRepositoryImpl() {
        setClazz(Tour.class);
    }

    @Override
    @ShowResult
    @ShowTime
    public List<Tour> readAll() {
        @SuppressWarnings("unchecked")
        List<Tour> tours = getCurrentSession().createNamedQuery("readAllToursNamed").getResultList();
        return tours;
    }

    @Override
    public void delete(Tour tour) {
        Query query = getCurrentSession().createNamedQuery("deleteTourNative");
        query.setParameter(1, tour.getId());
        query.executeUpdate();
    }

    @Override
    public List<Tour> getPage(int rows, int page) {
        Query query = getCurrentSession().createNamedQuery("readAllToursNamed");
        query.setFirstResult((page-1) * rows);
        query.setMaxResults(rows);
        @SuppressWarnings("unchecked")
        List<Tour> tours = query.getResultList();
        return tours;
    }
}
