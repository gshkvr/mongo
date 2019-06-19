package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country> {
    List getPage(int rows, int page);
}
