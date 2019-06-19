package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class TourDto {

    private int id;
    private String country;
    private Date date;
    private int duration;
    private String description;
    private String hotelName;
    private int hotelId;
    private int stars;
    private TourType tourType;
    private int cost;

    public static TourDto fromTourToDto(Tour tour) {
        return TourDto.builder()
                .id(tour.getId())
                .country(tour.getCountry().getName())
                .date(tour.getDate())
                .duration(tour.getDuration())
                .description(tour.getDescription())
                .hotelId(tour.getHotel().getId())
                .hotelName(tour.getHotel().getName())
                .stars(tour.getHotel().getStars())
                .tourType(tour.getTourType())
                .cost(tour.getCost())
                .build();
    }
}
