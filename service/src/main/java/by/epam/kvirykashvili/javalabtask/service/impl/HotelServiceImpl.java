package by.epam.kvirykashvili.javalabtask.service.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.repository.HotelRepository;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void create(Hotel hotel) {
        hotelRepository.create(hotel);
    }

    @Override
    public List<Hotel> readAll() {
        return hotelRepository.readAll();
    }

    @Override
    public List<Hotel> readList(SearchParameters parameters) {
        return hotelRepository.readList(parameters);
    }

    @Override
    public void update(Hotel hotel) {
        hotelRepository.update(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public List<Hotel> getPage(int rows, int page) {
        @SuppressWarnings("unchecked")
        List<Hotel> hotels = hotelRepository.getPage(rows, page);
        return hotels;

    }
}
