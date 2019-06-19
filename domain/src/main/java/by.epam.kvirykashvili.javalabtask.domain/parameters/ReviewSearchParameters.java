package by.epam.kvirykashvili.javalabtask.domain.parameters;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.domain.model.Review_;
import lombok.Builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
public class ReviewSearchParameters implements SearchParameters<Review> {

    private Integer id;
    private Date date;
    private String text;
    private Integer userId;
    private Integer tourId;

    @Override
    public List<Predicate> getPredicates(Root<Review> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(builder.equal(root.get(Review_.id), id));
        }
        if (date != null) {
            predicates.add(builder.equal(root.get(Review_.date), date));
        }
        if (text != null) {
            predicates.add(builder.equal(root.get(Review_.text), text));
        }
        if (userId != null) {
            predicates.add(builder.equal(root.get(Review_.user), userId));
        }
        if (tourId != null) {
            predicates.add(builder.equal(root.get(Review_.tour), tourId));
        }
        return predicates;
    }
}
