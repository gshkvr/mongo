package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelSearchDto {
    private String name;
    private Integer stars;
    private String website;

    public static HotelSearchParameters dtoToHotelSearchParameters(HotelSearchDto dto) {
        return HotelSearchParameters.builder()
                .name(dto.getName())
                .stars(dto.getStars())
                .website(dto.getWebsite())
                .build();
    }
}
