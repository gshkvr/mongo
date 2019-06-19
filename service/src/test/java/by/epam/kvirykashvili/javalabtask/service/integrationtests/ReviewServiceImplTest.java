package by.epam.kvirykashvili.javalabtask.service.integrationtests;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class ReviewServiceImplTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Autowired
    TourService tourService;

    @Test
    public void createReviewTest() {
        Review review = Review.builder()
                .date(new Date())
                .text("Nice tour.")
                .user(userService.readList(UserSearchParameters.builder()
                        .id(14)
                        .build())
                        .get(0))
                .tour(tourService.readList(TourSearchParameters.builder()
                        .id(132)
                        .build())
                        .get(0))
                .build();
        long countBeforeCreate = reviewService.readAll().size();
        reviewService.create(review);
        long countAfterCreate = reviewService.readAll().size();
        assertEquals(1, countAfterCreate - countBeforeCreate);
    }

    @Test
    public void readAllReviewsTest() {
        long reviewsNamed = reviewService.readAll().size();
        long reviewsCriteria = reviewService.readList(ReviewSearchParameters.builder()
                .build())
                .size();
        assertEquals(reviewsNamed, reviewsCriteria);
    }

    @Test
    public void readReviewTestTrue() {
        Review review = reviewService.readList(ReviewSearchParameters.builder()
                .id(2)
                .build())
                .get(0);
        assertEquals("Review text", review.getText());
    }

    @Test
    public void readReviewTestFalse() {
        Review review = reviewService.readList(ReviewSearchParameters.builder()
                .id(4)
                .build())
                .get(0);
        assertNotEquals("Review text false", review.getText());
    }

    @Test
    public void updateReviewTest() {
        Review review = Review.builder()
                .id(10)
                .date(new Date())
                .text("Nice tour.")
                .user(userService.readList(UserSearchParameters.builder()
                        .id(20)
                        .build())
                        .get(0))
                .tour(tourService.readList(TourSearchParameters.builder()
                        .id(488)
                        .build())
                        .get(0))
                .build();
        reviewService.update(review);
        Review updated = reviewService.readList(ReviewSearchParameters.builder()
                .id(review.getId())
                .build())
                .get(0);
        assertEquals(review.getText(), updated.getText());
    }

    @Test
    public void deleteReviewTestTrue() {
        Review review = Review.builder()
                .id(1000)
                .build();
        long countBeforeDelete = reviewService.readAll().size();
        reviewService.delete(review);
        long countAfterDelete = reviewService.readAll().size();
        assertEquals(1, countBeforeDelete - countAfterDelete);
    }

    @Test
    public void deleteReviewTestFalse() {
        Review review = Review.builder()
                .id(1003)
                .build();
        long countBeforeDelete = reviewService.readAll().size();
        reviewService.delete(review);
        long countAfterDelete = reviewService.readAll().size();
        assertNotEquals(1, countBeforeDelete - countAfterDelete);
    }
}