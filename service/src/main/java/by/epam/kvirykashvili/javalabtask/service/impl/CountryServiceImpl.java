package by.epam.kvirykashvili.javalabtask.service.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.repository.CountryRepository;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public void create(Country country) {
        countryRepository.create(country);
    }

    @Override
    @Transactional
    public List<Country> readAll() {
        return countryRepository.readAll();
    }

    @Override
    @Transactional
    public List<Country> readList(SearchParameters parameters) {
        return countryRepository.readList(parameters);
    }

    @Override
    @Transactional
    public void update(Country country) {
        countryRepository.update(country);
    }

    @Override
    @Transactional
    public void delete(Country country) {
        countryRepository.delete(country);
    }

    @Override
    @Transactional
    public List<Country> getPage(int rows, int page) {
        @SuppressWarnings("unchecked")
        List<Country> countries = countryRepository.getPage(rows, page);
        return countries;
    }
}
