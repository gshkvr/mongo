package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;

import java.util.List;

public interface TourService extends CrudService<Tour> {
    void importToursFromCsv(String csvFile);
    List<Tour> getPage(int rows, int page);
}
