package by.epam.kvirykashvili.javalabtask.service.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.repository.TourFromCsvImporter;
import by.epam.kvirykashvili.javalabtask.repository.TourRepository;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    private TourRepository tourRepository;
    private TourFromCsvImporter tourFromCsvImporter;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, TourFromCsvImporter tourFromCsvImporter) {
        this.tourRepository = tourRepository;
        this.tourFromCsvImporter = tourFromCsvImporter;
    }

    @Override
    public void create(Tour tour) {
        tourRepository.create(tour);
    }

    @Override
    public List<Tour> readAll() {
        return tourRepository.readAll();
    }

    @Override
    public List<Tour> readList(SearchParameters parameters) {
        return tourRepository.readList(parameters);
    }

    @Override
    public void update(Tour tour) {
        tourRepository.update(tour);
    }

    @Override
    public void delete(Tour tour) {
        tourRepository.delete(tour);
    }

    @Override
    public void importToursFromCsv(String csvFile) {
        tourFromCsvImporter.importToursFromCsv(csvFile);
    }

    @Override
    public List<Tour> getPage(int rows, int page) {
        @SuppressWarnings("unchecked")
        List<Tour> tours = tourRepository.getPage(rows, page);
        return tours;
    }
}
