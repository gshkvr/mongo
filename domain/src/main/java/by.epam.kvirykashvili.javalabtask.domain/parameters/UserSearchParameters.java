package by.epam.kvirykashvili.javalabtask.domain.parameters;

import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.model.User_;
import lombok.Builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Builder
public class UserSearchParameters implements SearchParameters<User> {

    private Integer id;
    private String login;
    private String password;

    @Override
    public List<Predicate> getPredicates(Root<User> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            predicates.add(builder.equal(root.get(User_.id), id));
        }
        if (login != null) {
            predicates.add(builder.equal(root.get(User_.login), login));
        }
        if (password != null) {
            predicates.add(builder.equal(root.get(User_.password), password));
        }
        return predicates;
    }
}
