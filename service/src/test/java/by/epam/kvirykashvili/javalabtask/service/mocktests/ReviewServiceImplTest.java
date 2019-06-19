package by.epam.kvirykashvili.javalabtask.service.mocktests;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import by.epam.kvirykashvili.javalabtask.service.config.ServiceConfig;
import by.epam.kvirykashvili.javalabtask.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@ActiveProfiles("test")
public class ReviewServiceImplTest {

    private ReviewService reviewServiceMock;
    private List<Review> reviews;
    private Review review;

    @Before
    public void init() {
        reviewServiceMock = mock(ReviewServiceImpl.class);
        review = new Review();
        review.setDate(new Date());
        review.setText("Nice tour.");
        Review review1 = new Review();
        review1.setId(10);
        review1.setDate(new Date());
        review1.setText("Nice tour.");
        Review review2 = new Review();
        review2.setId(1000);
        review2.setDate(new Date());
        review2.setText("Tour review.");
        Review review3 = new Review();
        review3.setId(1001);
        review3.setDate(new Date());
        review3.setText("Tour review.");
        reviews = Arrays.asList(review1, review2, review3);
    }

    @Test
    public void createCountryMockTest() {
        reviewServiceMock.create(review);
        verify(reviewServiceMock).create(review);
    }

    @Test
    public void deleteCountryMockTest() {
        reviewServiceMock.delete(review);
        verify(reviewServiceMock).delete(review);
    }

    @Test
    public void updateCountryMockTest() {
        reviewServiceMock.update(review);
        verify(reviewServiceMock).update(review);
    }

    @Test
    public void readAllCountriesMockTest() {
        when(reviewServiceMock.readAll()).thenReturn(reviews);
        List<Review> countryList = reviewServiceMock.readAll();
        assertEquals(3, countryList.size());
    }

    @Test
    public void readCountryMockTest() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        when(reviewServiceMock.readList(any())).thenReturn(reviewList);
        Review r = reviewServiceMock.readList(ReviewSearchParameters.builder().id(1).build()).get(0);
        assertEquals("Nice tour.", r.getText());
    }
}