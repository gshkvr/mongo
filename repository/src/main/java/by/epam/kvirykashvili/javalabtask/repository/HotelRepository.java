package by.epam.kvirykashvili.javalabtask.repository;

import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel> {
    List getPage(int rows, int page);
}
