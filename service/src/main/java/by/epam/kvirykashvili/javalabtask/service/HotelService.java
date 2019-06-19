package by.epam.kvirykashvili.javalabtask.service;

import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;

import java.util.List;

public interface HotelService extends CrudService<Hotel> {
    List<Hotel> getPage(int rows, int page);
}
