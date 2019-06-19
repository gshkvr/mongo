package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.Country;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.CountryDto;
import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.CountryService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.web.controller.exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private TourService tourService;

    private final int ROWS = 10;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create-country")
    public String createCountryGet() {
        return "add/add-country";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-country")
    public String createCountryPost(@Valid @ModelAttribute(value = "country") Country country, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create-country";
        }
        countryService.create(country);
        return "redirect:/countries";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/countries")
    public String viewCountries(ModelMap model) {
        List<Country> countries = countryService.getPage(ROWS, 1);
        model.addAttribute("countries", countries);
        return "listEntities/countries";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-country/{countryId}")
    public String getCountryUpdate(@PathVariable(value = "countryId") int countryId, ModelMap model) {
        Country country = countryService.readList(UserSearchParameters.builder()
                .id(countryId)
                .build())
                .get(0);
        model.addAttribute("country", country);
        return "update/update-country";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-country")
    public String postCountryUpdate(@Valid Country country, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update/update-country";
        }
        countryService.update(country);
        return "redirect:/countries";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete-country")
    public String removeCountry(@RequestParam(value = "countryId") int countryId) {
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .countryId(countryId)
                .build());
        if (tours.size() > 0) {
            throw new DeleteException("There are some tours reference on deleting country");
        } else {
            countryService.delete(Country.builder()
                    .id(countryId)
                    .build());
        }

        return "redirect:/countries";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/countries-page/{page}", produces = "application/json")
    public @ResponseBody
    List<Object> getPage(@PathVariable("page") int page) {
        List<Country> countryList = countryService.getPage(ROWS, page);
        List<CountryDto> countryDtos = countryList.stream().map(CountryDto::fromCountryToDto).collect(Collectors.toList());
        int pages = countryService.readAll().size() / ROWS + 1;
        List<Object> result = new ArrayList<>();
        result.add(countryDtos);
        result.add(pages);
        result.add(page);
        return result;
    }
}
