package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.config.AppConfig;
import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class ReviewRepositoryImplTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    @Test
    @Transactional
    public void createReviewTest() {
        Review review = Review.builder()
                .date(new Date())
                .text("New nice tour.")
                .user(userRepository.readList(UserSearchParameters.builder()
                        .id(1)
                        .build())
                        .get(0))
                .tour(tourRepository.readList(TourSearchParameters.builder()
                        .id(1)
                        .build())
                        .get(0))
                .build();
        long countBeforeCreate = reviewRepository.readAll().size();
        reviewRepository.create(review);
        long countAfterCreate = reviewRepository.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    @Transactional
    public void readAllReviewsTest() {
        long reviewsNamed = reviewRepository.readAll().size();
        long reviewsCriteria = reviewRepository.readList(ReviewSearchParameters.builder()
                .build())
                .size();
        assertEquals(reviewsNamed, reviewsCriteria);
    }

    @Test
    @Transactional
    public void readReviewTest() {
        Review review = reviewRepository.readList(ReviewSearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertEquals("Review text", review.getText());
    }

    @Test
    @Transactional
    public void updateReviewTest() {
        Review review = Review.builder()
                .id(4)
                .date(new Date())
                .text("Nice tour.")
                .user(userRepository.readList(UserSearchParameters.builder()
                        .id(5)
                        .build())
                        .get(0))
                .tour(tourRepository.readList(TourSearchParameters.builder()
                        .id(70)
                        .build())
                        .get(0))
                .build();
        reviewRepository.update(review);
        Review updated = reviewRepository.readList(ReviewSearchParameters.builder()
                .id(review.getId())
                .build())
                .get(0);
        assertEquals(review.getText(), updated.getText());
    }

    @Test
    @Transactional
    public void deleteReviewTest() {
        Review review = Review.builder()
                .id(3)
                .build();
        long countBeforeDelete = reviewRepository.readAll().size();
        reviewRepository.delete(review);
        long countAfterDelete = reviewRepository.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }
}