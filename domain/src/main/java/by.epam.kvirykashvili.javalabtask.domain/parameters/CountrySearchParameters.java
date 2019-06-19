package by.epam.kvirykashvili.javalabtask.domain.parameters;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.model.Country_;
import lombok.Builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Builder
public class CountrySearchParameters implements SearchParameters<Country> {

    private Integer id;
    private String name;

    @Override
    public List<Predicate> getPredicates(Root root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(builder.equal(root.get(Country_.id), id));
        }
        if (name != null) {
            predicates.add(builder.equal(root.get(Country_.name), name));
        }
        return predicates;
    }
}
