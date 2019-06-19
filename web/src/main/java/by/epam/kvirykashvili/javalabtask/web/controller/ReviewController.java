package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.Review;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.ReviewDto;
import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private final int ROWS = 10;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create-review/{tourId}")
    public String createReviewGet(@PathVariable(value = "tourId") int tourId, @AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("userId", user.getId());
        model.addAttribute("tourId", tourId);
        return "add/add-review";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create-review")
    public String createReviewPost(@Valid @ModelAttribute(value = "review") Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create-review/" + review.getTour().getId();
        }
        reviewService.create(review);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/reviews")
    public String viewReviews(ModelMap model) {
        List<Review> reviews = reviewService.getPage(ROWS, 1);
        model.addAttribute("reviews", reviews);
        return "listEntities/reviews";
    }

    @GetMapping(value = "/review/{reviewId}")
    public String getReview(@PathVariable("reviewId") int reviewId, Model model) {
        Review review = reviewService.readList(ReviewSearchParameters.builder()
                .id(reviewId)
                .build())
                .get(0);
        model.addAttribute("review", review);

        return "entity/review";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update-review/{reviewId}")
    public String getReviewUpdate(@PathVariable(value = "reviewId") int reviewId, ModelMap model) {
        Review review = reviewService.readList(ReviewSearchParameters.builder()
                .id(reviewId)
                .build())
                .get(0);
        model.addAttribute("review", review);
        return "update/update-review";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-review")
    public String postReviewUpdate(@Valid Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update/update-review";
        }
        reviewService.update(review);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete-review")
    public String removeReview(@RequestParam(value = "reviewId") int reviewId) {
        reviewService.delete(Review.builder()
                .id(reviewId)
                .build());
        return "redirect:/tours";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/reviews-page/{page}", produces = "application/json")
    public @ResponseBody
    List<Object> getPage(@PathVariable("page") int page) {
        List<Review> countryList = reviewService.getPage(ROWS, page);
        List<ReviewDto> reviewsDtos = countryList.stream().map(ReviewDto::fromCountryToDto).collect(Collectors.toList());
        int pages = reviewService.readAll().size() / ROWS + 1;
        List<Object> result = new ArrayList<>();
        result.add(reviewsDtos);
        result.add(pages);
        result.add(page);
        return result;
    }
}
