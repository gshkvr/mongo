package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime;
import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.repository.AbstractHibernateRepository;
import by.epam.kvirykashvili.javalabtask.repository.CountryRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepositoryImpl extends AbstractHibernateRepository<Country> implements CountryRepository {

    public CountryRepositoryImpl() {
        setClazz(Country.class);
    }

    @Override
    @ShowResult
    @ShowTime
    public List<Country> readAll() {
        @SuppressWarnings("unchecked")
        List<Country> countries = getCurrentSession().createNamedQuery("readAllCountriesNamed").getResultList();
        return countries;
    }

    @Override
    public void delete(Country country) {
        Query query = getCurrentSession().createNamedQuery("deleteCountryNative");
        query.setParameter(1, country.getId());
        query.executeUpdate();
    }

    @Override
    public List getPage(int rows, int page) {
        Query query = getCurrentSession().createNamedQuery("readAllCountriesNamed");
        query.setFirstResult((page-1) * rows);
        query.setMaxResults(rows);
        @SuppressWarnings("unchecked")
        List<Country> countries = query.getResultList();
        return countries;
    }
}
