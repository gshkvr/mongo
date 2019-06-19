package by.epam.kvirykashvili.javalabtask.repository.impl;

import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult;
import by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime;
import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.repository.AbstractHibernateRepository;
import by.epam.kvirykashvili.javalabtask.repository.ReviewRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends AbstractHibernateRepository<Review> implements ReviewRepository {

    public ReviewRepositoryImpl() {
        setClazz(Review.class);
    }

    @Override
    @ShowResult
    @ShowTime
    public List<Review> readAll() {
        @SuppressWarnings("unchecked")
        List<Review> reviews = getCurrentSession().createNamedQuery("readAllReviewsNamed").getResultList();
        return reviews;
    }

    @Override
    public void delete(Review review) {
        Query query = getCurrentSession().createNamedQuery("deleteReviewNative");
        query.setParameter(1, review.getId());
        query.executeUpdate();
    }

    @Override
    public List getPage(int rows, int page) {
        Query query = getCurrentSession().createNamedQuery("readAllReviewsNamed");
        query.setFirstResult((page-1) * rows);
        query.setMaxResults(rows);
        @SuppressWarnings("unchecked")
        List<Review> reviews = query.getResultList();
        return reviews;
    }
}
