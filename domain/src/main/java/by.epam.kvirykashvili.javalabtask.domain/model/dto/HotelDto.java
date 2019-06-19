package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HotelDto {

    private int id;
    private String name;
    private int stars;
    private String website;
    private String latitude;
    private String longitude;
    private Features[] features;

    public static HotelDto fromHotelToDto(Hotel hotel) {
        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .stars(hotel.getStars())
                .website(hotel.getWebsite())
                .latitude(hotel.getLatitude())
                .longitude(hotel.getLongitude())
                .features(hotel.getFeatures())
                .build();
    }
}
