package by.epam.kvirykashvili.javalabtask.domain.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TourCsv {

    @CsvBindByPosition(position = 0, required = true)
    private String photo;

    @CsvBindByPosition(position = 1, required = true)
    @CsvDate("yyyy-MM-dd")
    private Date date;

    @CsvBindByPosition(position = 2, required = true)
    private int duration;

    @CsvBindByPosition(position = 3, required = true)
    private String description;

    @CsvBindByPosition(position = 4, required = true)
    private int cost;

    @CsvBindByPosition(position = 5, required = true)
    private String tourType;

    @CsvBindByPosition(position = 6, required = true)
    private int hotelId;

    @CsvBindByPosition(position = 7, required = true)
    private int countryId;
}
