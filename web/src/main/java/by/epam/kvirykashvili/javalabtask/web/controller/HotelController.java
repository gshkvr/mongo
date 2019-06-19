package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.Features;
import by.epam.kvirykashvili.javalabtask.domain.model.Hotel;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.HotelDto;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.HotelSearchDto;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.HotelService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.web.controller.exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private TourService tourService;

    private final int ROWS = 10;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create-hotel")
    public String createHotelGet(ModelMap model) {
        List<String> features = new ArrayList<>();
        for (Features f : Features.class.getEnumConstants()) {
            features.add(f.toString());
        }
        model.addAttribute("features", features);
        return "add/add-hotel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-hotel")
    public String createHotelPost(@Valid @ModelAttribute(value = "hotel") Hotel hotel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create-hotel";
        }
        hotelService.create(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/hotels")
    public String viewHotels(ModelMap model) {
        List<Hotel> hotels = hotelService.getPage(ROWS, 1);
        model.addAttribute("hotels", hotels);
        return "listEntities/hotels";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @GetMapping(value = "/hotel/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.readList(HotelSearchParameters.builder()
                .id(hotelId)
                .build())
                .get(0);
        model.addAttribute("hotel", hotel);
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .hotelId(hotelId)
                .build());
        model.addAttribute("tours", tours);

        return "entity/hotel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-hotel/{hotelId}")
    public String getHotelUpdate(@PathVariable(value = "hotelId") int hotelId, ModelMap model) {
        Hotel hotel = hotelService.readList(HotelSearchParameters.builder()
                .id(hotelId)
                .build())
                .get(0);
        List<String> features = new ArrayList<>();
        for (Features f : Features.class.getEnumConstants()) {
            features.add(f.toString());
        }
        model.addAttribute("hotel", hotel);
        model.addAttribute("features", features);
        return "update/update-hotel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-hotel")
    public String postHotelUpdate(@Valid Hotel hotel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update/update-hotel";
        }
        hotelService.update(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete-hotel")
    public String removeHotel(@RequestParam(value = "hotelId") int hotelId) {
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .hotelId(hotelId)
                .build());
        if (tours.size() > 0) {
            throw new DeleteException("There are some tours reference on deleting hotel");
        } else {
            hotelService.delete(Hotel.builder()
                    .id(hotelId)
                    .build());
        }
        return "redirect:/hotels";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/hotels-page/{page}", produces = "application/json")
    public @ResponseBody
    List<Object> getPage(@PathVariable("page") int page) {
        List<Hotel> hotelList = hotelService.getPage(ROWS, page);
        List<HotelDto> hotelDtos = hotelList.stream().map(HotelDto::fromHotelToDto).collect(Collectors.toList());
        int pages = hotelService.readAll().size() / ROWS + 1;
        List<Object> result = new ArrayList<>();
        result.add(hotelDtos);
        result.add(pages);
        result.add(page);
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search-hotel")
    public String searchTourGet(ModelMap model) {
        List<String> features = new ArrayList<>();
        for (Features f : Features.class.getEnumConstants()) {
            features.add(f.toString());
        }
        model.addAttribute("features", features);
        return "search/search-hotel";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/search-hotel", produces = "application/json")
    public @ResponseBody
    List<HotelDto> searchTourPost(@RequestBody HotelSearchDto searchData) {
        HotelSearchParameters searchParameters = HotelSearchDto.dtoToHotelSearchParameters(searchData);
        List<Hotel> tourList = hotelService.readList(searchParameters);
        return tourList.stream().map(HotelDto::fromHotelToDto).collect(Collectors.toList());
    }
}
