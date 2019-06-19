package by.epam.kvirykashvili.javalabtask.domain.parameters;

import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel_;
import lombok.Builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Builder
public class HotelSearchParameters implements SearchParameters<Hotel> {

    private Integer id;
    private String name;
    private Integer stars;
    private String website;
    private String latitude;
    private String longitude;
    private Features[] features;

    @Override
    public List<Predicate> getPredicates(Root<Hotel> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(builder.equal(root.get(Hotel_.id), id));
        }
        if (name != null) {
            predicates.add(builder.equal(root.get(Hotel_.name), name));
        }
        if (stars != null) {
            predicates.add(builder.equal(root.get(Hotel_.stars), stars));
        }
        if (website != null) {
            predicates.add(builder.equal(root.get(Hotel_.website), website));
        }
        if (latitude != null) {
            predicates.add(builder.equal(root.get(Hotel_.latitude), latitude));
        }
        if (longitude != null) {
            predicates.add(builder.equal(root.get(Hotel_.longitude), longitude));
        }
        if (features != null) {
            predicates.add(builder.equal(root.get(Hotel_.features), features));
        }
        return predicates;
    }
}
