package by.epam.kvirykashvili.javalabtask.domain.parameters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface SearchParameters<T> {
    List<Predicate> getPredicates(Root<T> root, CriteriaBuilder builder);
}
