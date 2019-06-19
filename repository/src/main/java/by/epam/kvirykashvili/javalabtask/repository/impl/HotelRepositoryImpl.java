package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.repository.AbstractHibernateRepository;
import by.epam.kvirykashvili.javalabtask.repository.HotelRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepositoryImpl extends AbstractHibernateRepository<Hotel> implements HotelRepository {

    public HotelRepositoryImpl() {
        setClazz(Hotel.class);
    }

    @Override
    @ShowResult
    @ShowTime
    public List<Hotel> readAll() {
        @SuppressWarnings("unchecked")
        List<Hotel> hotels = getCurrentSession().createNamedQuery("readAllHotelsNamed").getResultList();
        return hotels;
    }

    @Override
    public void delete(Hotel hotel) {
        Query query = getCurrentSession().createNamedQuery("deleteHotelNative");
        query.setParameter(1, hotel.getId());
        query.executeUpdate();
    }

    @Override
    public List getPage(int rows, int page) {
        Query query = getCurrentSession().createNamedQuery("readAllHotelsNamed");
        query.setFirstResult((page - 1) * rows);
        query.setMaxResults(rows);
        @SuppressWarnings("unchecked")
        List<Hotel> hotels = query.getResultList();
        return hotels;
    }

}