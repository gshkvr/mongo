package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;

import java.util.List;

public interface ReviewService extends CrudService<Review> {
    List<Review> getPage(int rows, int page);
}
