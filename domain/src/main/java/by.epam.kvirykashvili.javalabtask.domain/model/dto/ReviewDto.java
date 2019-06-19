package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class ReviewDto {

    private int id;
    private Date date;
    private String text;
    private int userId;
    private String userName;
    private int tourId;
    private String tourDescription;

    public static ReviewDto fromCountryToDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .date(review.getDate())
                .text(review.getText())
                .userId(review.getUser().getId())
                .userName(review.getUser().getLogin())
                .tourId(review.getTour().getId())
                .tourDescription(review.getTour().getDescription())
                .build();
    }
}
