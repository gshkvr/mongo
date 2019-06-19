package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.*;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.TourDto;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.TourSearchDto;
import by.epam.kvirykashvili.javalabtask.domain.parameters.CountrySearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.HotelSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    private final int ROWS = 10;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create-tour")
    public String createTourGet(ModelMap model) {
        List<Country> countries = countryService.readList(CountrySearchParameters.builder().build());
        List<Hotel> hotels = hotelService.readList(HotelSearchParameters.builder().build());
        List<String> tourTypes = new ArrayList<>();
        for (TourType type : TourType.class.getEnumConstants()) {
            tourTypes.add(type.toString());
        }
        model.addAttribute("countries", countries);
        model.addAttribute("hotels", hotels);
        model.addAttribute("tourTypes", tourTypes);
        return "add/add-tour";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-tour")
    public String createTourPost(@Valid @ModelAttribute(value = "tour") Tour tour, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create-tour";
        }
        tourService.create(tour);
        return "redirect:/tours";
    }

    @GetMapping(value = {"/", "/tours"})
    public String viewTours(ModelMap model) {
        List<Tour> tours = tourService.getPage(ROWS, 1);
        model.addAttribute("tours", tours);
        return "listEntities/tours";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @GetMapping(value = "/tour/{tourId}")
    public String getTour(@PathVariable("tourId") int tourId, @AuthenticationPrincipal User user, Model model) {
        List<Tour> userTours = tourService.readList(TourSearchParameters.builder()
                .userId(user.getId())
                .build());
        boolean isFavorite = false;
        for (Tour tour : userTours) {
            if (tour.getId() == tourId) {
                isFavorite = true;
                break;
            }
        }
        model.addAttribute("isFavorite", isFavorite);

        Tour tour = tourService.readList(TourSearchParameters.builder()
                .id(tourId)
                .build())
                .get(0);
        model.addAttribute("tour", tour);
        List<Review> reviews = reviewService.readList(ReviewSearchParameters.builder()
                .tourId(tourId)
                .build());
        model.addAttribute("reviews", reviews);
        model.addAttribute("currentUserId", user.getId());

        return "entity/tour";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @PostMapping(value = "/tour/{tourId}")
    public String favouriteTour(@PathVariable("tourId") int tourId, @AuthenticationPrincipal User user, boolean add, Model model) {
        if (add) {
            Tour tourToAdd = tourService.readList(TourSearchParameters.builder()
                    .id(tourId)
                    .build())
                    .get(0);
            userService.addFavouriteTour(user, tourToAdd);
        } else {
            Tour tourToRemove = tourService.readList(TourSearchParameters.builder()
                    .id(tourId)
                    .build())
                    .get(0);
            userService.removeFavouriteTour(user, tourToRemove);
        }

        Tour tour = tourService.readList(TourSearchParameters.builder()
                .id(tourId)
                .build())
                .get(0);
        model.addAttribute("tour", tour);
        List<Review> reviews = reviewService.readList(ReviewSearchParameters.builder()
                .tourId(tourId)
                .build());
        model.addAttribute("reviews", reviews);
        return "redirect:/tour/{tourId}";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/import-tour")
    public String singleFileUpload(@RequestParam("toursFile") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Importing file was not selected");
            return "redirect:/import";
        }

        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".csv")) {
            redirectAttributes.addFlashAttribute("message", "Importing file should be in .csv format");
            return "redirect:/import";
        }

        try {
            String bytes = new String(file.getBytes());
            tourService.importToursFromCsv(bytes);
            redirectAttributes.addFlashAttribute("message", "Tours from file were imported");

        } catch (IOException e) {
            e.printStackTrace();

        }

        return "redirect:/import";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-tour/{tourId}")
    public String getTourUpdate(@PathVariable(value = "tourId") int tourId, ModelMap model) {
        Tour tour = tourService.readList(TourSearchParameters.builder()
                .id(tourId)
                .build())
                .get(0);
        List<Country> countries = countryService.readList(CountrySearchParameters.builder().build());
        List<Hotel> hotels = hotelService.readList(HotelSearchParameters.builder().build());
        List<String> tourTypes = new ArrayList<>();
        for (TourType type : TourType.class.getEnumConstants()) {
            tourTypes.add(type.toString());
        }
        model.addAttribute("tour", tour);
        model.addAttribute("countries", countries);
        model.addAttribute("hotels", hotels);
        model.addAttribute("tourTypes", tourTypes);
        return "update/update-tour";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-tour")
    public String postTourUpdate(@Valid Tour tour, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update/update-tour";
        }
        tourService.update(tour);
        return "redirect:/tours";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete-tour")
    public String removeTour(@RequestParam(value = "tourId") int tourId) {
        tourService.delete(Tour.builder()
                .id(tourId)
                .build());
        return "redirect:/tours";
    }

    @GetMapping(value = "/tours-page/{page}", produces = "application/json")
    public @ResponseBody
    List<Object> getPage(@PathVariable("page") int page) {
        List<Tour> tourList = tourService.getPage(ROWS, page);
        List<TourDto> tourDtos = tourList.stream().map(TourDto::fromTourToDto).collect(Collectors.toList());
        int pages = tourService.readAll().size() / ROWS + 1;
        List<Object> result = new ArrayList<>();
        result.add(tourDtos);
        result.add(pages);
        result.add(page);
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search-tour")
    public String searchTourGet(ModelMap model) {
        List<Country> countries = countryService.readList(CountrySearchParameters.builder().build());
        List<Hotel> hotels = hotelService.readList(HotelSearchParameters.builder().build());
        List<String> tourTypes = new ArrayList<>();
        for (TourType type : TourType.class.getEnumConstants()) {
            tourTypes.add(type.toString());
        }
        model.addAttribute("countries", countries);
        model.addAttribute("hotels", hotels);
        model.addAttribute("tourTypes", tourTypes);
        return "search/search-tour";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/search-tour", produces = "application/json")
    public @ResponseBody
    List<TourDto> searchTourPost(@RequestBody TourSearchDto searchData) {
        TourSearchParameters searchParameters = TourSearchDto.dtoToTourSearchParameters(searchData);
        List<Tour> tourList = tourService.readList(searchParameters);
        return tourList.stream().map(TourDto::fromTourToDto).collect(Collectors.toList());
    }

}
