package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    void addFavouriteTour(User user, Tour tour);

    void removeFavouriteTour(User user, Tour tour);

    List getPage(int rows, int page);
}
