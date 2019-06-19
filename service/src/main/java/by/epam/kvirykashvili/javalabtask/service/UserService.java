package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.User;

import java.util.List;

public interface UserService extends CrudService<User> {
    void addFavouriteTour(User user, Tour tour);

    void removeFavouriteTour(User user, Tour tour);

    List<User> getPage(int rows, int page);
}
