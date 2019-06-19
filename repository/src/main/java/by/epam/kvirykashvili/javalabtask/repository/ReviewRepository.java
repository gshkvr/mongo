package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review> {
    List getPage(int rows, int page);
}
