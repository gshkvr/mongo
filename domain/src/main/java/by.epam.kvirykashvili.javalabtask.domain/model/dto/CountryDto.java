package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CountryDto {
    private int id;
    private String name;

    public static CountryDto fromCountryToDto(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
