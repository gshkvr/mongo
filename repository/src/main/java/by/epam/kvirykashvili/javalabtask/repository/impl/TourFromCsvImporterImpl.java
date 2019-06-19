package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourCsv;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import by.epam.kvirykashvili.javalabtask.repository.AbstractRepository;
import by.epam.kvirykashvili.javalabtask.repository.CountryRepository;
import by.epam.kvirykashvili.javalabtask.repository.HotelRepository;
import by.epam.kvirykashvili.javalabtask.repository.TourFromCsvImporter;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TourFromCsvImporterImpl extends AbstractRepository<Tour> implements TourFromCsvImporter {

    private CountryRepository countryRepository;
    private HotelRepository hotelRepository;

    @Autowired
    public TourFromCsvImporterImpl(CountryRepository countryRepository, HotelRepository hotelRepository) {
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void importToursFromCsv(String csvFile) {
        List<TourCsv> tourList = null;
        try {
            tourList = createToursFromFile(csvFile);
        } catch (IOException e) {
            log.warn("Problems with tours csv file", e);
        }
        if (tourList != null) {
            saveTours(tourList);
        }
    }

    private List<TourCsv> createToursFromFile(String csvFile) throws IOException {

        List<TourCsv> csvTours = new ArrayList<>();
        try (Reader reader = new StringReader(csvFile)) {
            CsvToBean<TourCsv> csvToBean = new CsvToBeanBuilder<TourCsv>(reader)
                    .withType(TourCsv.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (TourCsv csvTour : csvToBean) {
                csvTours.add(csvTour);
            }
        }
        return csvTours;
    }

    private void saveTours(List<TourCsv> tourCsvList) {
        for (TourCsv tourCsv : tourCsvList) {
            Tour tour = convertTourCsvToTour(tourCsv);
            getCurrentSession().saveOrUpdate(tour);
        }
    }

    private Tour convertTourCsvToTour(TourCsv tourCsv) {
        return Tour.builder()
                .photo(tourCsv.getPhoto())
                .date(tourCsv.getDate())
                .duration(tourCsv.getDuration())
                .description(tourCsv.getDescription())
                .cost(tourCsv.getCost())
                .tourType(TourType.valueOf(tourCsv.getTourType()))
                .hotel(hotelRepository.readList(HotelSearchParameters.builder()
                        .id(tourCsv.getHotelId())
                        .build())
                        .get(0))
                .country(countryRepository.readList(CountrySearchParameters.builder()
                        .id(tourCsv.getCountryId())
                        .build())
                        .get(0))
                .build();
    }
}
