package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
@Transactional
public class TourFromCsvImporterImplTest {

    @Autowired
    TourFromCsvImporter tourFromCsvImporter;
    @Autowired
    TourRepository tourRepository;

    @Test
    public void importToursFromCsv() {
        final String TOURS_CSV_FILE =
                "photo,date,duration,description,cost,tour_type,hotel_id,country_id\n" +
                        "\"//photos/2138838923923\",2018-02-11,11,\"Cultural tour for 11 days\",400,\"CULTURAL\",38,26\n" +
                        "\"//photos/8579357925848\",2018-03-22,12,\"Cultural tour for 12 days\",1600,\"CULTURAL\",14,10";

        long countBeforeImport = tourRepository.readAll().size();
        tourFromCsvImporter.importToursFromCsv(TOURS_CSV_FILE);
        long countAfterImport = tourRepository.readAll().size();
        assertEquals(2, countAfterImport - countBeforeImport);
    }
}