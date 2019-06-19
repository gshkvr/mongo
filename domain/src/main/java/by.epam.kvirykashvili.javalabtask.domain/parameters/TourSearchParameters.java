package by.epam.kvirykashvili.javalabtask.domain.parameters;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.TourType;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour_;
import lombok.Builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
public class TourSearchParameters implements SearchParameters<Tour> {

    private Integer id;
    private String photo;
    private Date date;
    private Integer duration;
    private String description;
    private Integer cost;
    private TourType tourType;
    private Integer userId;
    private Integer hotelId;
    private Integer countryId;

    @Override
    public List<Predicate> getPredicates(Root<Tour> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(builder.equal(root.get(Tour_.id), id));
        }
        if (photo != null) {
            predicates.add(builder.equal(root.get(Tour_.photo), photo));
        }
        if (date != null) {
            predicates.add(builder.equal(root.get(Tour_.date), date));
        }
        if (duration != null) {
            predicates.add(builder.equal(root.get(Tour_.duration), duration));
        }
        if (description != null) {
            predicates.add(builder.equal(root.get(Tour_.description), description));
        }
        if (cost != null) {
            predicates.add(builder.equal(root.get(Tour_.cost), cost));
        }
        if (tourType != null) {
            predicates.add(builder.equal(root.get(Tour_.tourType), tourType));
        }
        if (userId != null) {
            predicates.add(builder.equal(root.join(Tour_.users), userId));
        }
        if (hotelId != null) {
            predicates.add(builder.equal(root.join(Tour_.hotel), hotelId));
        }
        if (countryId != null) {
            predicates.add(builder.equal(root.join(Tour_.country), countryId));
        }
        return predicates;
    }
}
