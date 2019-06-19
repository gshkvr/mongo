package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends Serializable> {
    void create(T t);

    List<T> readAll();

    List<T> readList(SearchParameters parameters);

    void update(T t);

    void delete(T t);
}
