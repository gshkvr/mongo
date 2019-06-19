package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;

import java.io.Serializable;
import java.util.List;

public interface CrudRepository<T extends Serializable> {

    void create(T t);

    List<T> readAll();

    List<T> readList(SearchParameters parameters);

    void update(T t);

    void delete(T t);

}
