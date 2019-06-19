package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour> {
    List getPage(int rows, int page);
}
