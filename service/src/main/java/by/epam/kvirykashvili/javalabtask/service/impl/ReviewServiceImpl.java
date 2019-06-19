package by.epam.kvirykashvili.javalabtask.service.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.repository.ReviewRepository;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void create(Review review) {
        reviewRepository.create(review);
    }

    @Override
    public List<Review> readAll() {
        return reviewRepository.readAll();
    }

    @Override
    public List<Review> readList(SearchParameters parameters) {
        return reviewRepository.readList(parameters);
    }

    @Override
    public void update(Review review) {
        reviewRepository.update(review);
    }

    @Transactional
    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getPage(int rows, int page) {
        @SuppressWarnings("unchecked")
        List<Review> reviews = reviewRepository.getPage(rows, page);
        return reviews;
    }
}
