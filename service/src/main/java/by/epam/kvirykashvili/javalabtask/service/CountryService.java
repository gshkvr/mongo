package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;

import java.util.List;

public interface CountryService extends CrudService<Country> {
    List<Country> getPage(int rows, int page);
}
