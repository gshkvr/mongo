package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateRepository<T extends Serializable> extends AbstractRepository<T> implements CrudRepository<T> {

    @Override
    public void create(T t) {
        getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public void update(T t) {
        getCurrentSession().merge(t);
    }

    @Override
    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    @Override
    @ShowResult
    public List<T> readList(SearchParameters parameters) {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        List<Predicate> predicates;
        predicates = parameters.getPredicates(root, builder);
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        }
        Query query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
