package by.epam.kvirykashvili.javalabtask.web.controller;

import by.epam.kvirykashvili.javalabtask.domain.model.*;
import by.epam.kvirykashvili.javalabtask.domain.model.dto.UserDto;
import by.epam.kvirykashvili.javalabtask.domain.parameters.ReviewSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.TourSearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.ReviewService;
import by.epam.kvirykashvili.javalabtask.service.TourService;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import by.epam.kvirykashvili.javalabtask.web.controller.exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;
    @Autowired
    private ReviewService reviewService;

    private final int ROWS = 10;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register-user")
    public String registerUser(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create-user")
    public String createUserGet(ModelMap model) {
        List<String> userRoles = new ArrayList<>();
        for (UserRole role : UserRole.class.getEnumConstants()) {
            userRoles.add(role.toString());
        }
        model.addAttribute("userRoles", userRoles);
        return "add/add-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-user")
    public String createUserPost(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/create-user";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .userId(user.getId())
                .build());
        List<Review> reviews = reviewService.readList(ReviewSearchParameters.builder()
                .userId(user.getId())
                .build());
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);
        return "entity/user";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        List<User> users = userService.getPage(ROWS, 1);
        model.addAttribute("users", users);
        return "listEntities/users";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @GetMapping(value = "/user/{userId}")
    public String getUser(@PathVariable("userId") int userId, Model model) {
        User user = userService.readList(UserSearchParameters.builder()
                .id(userId)
                .build())
                .get(0);
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .userId(userId)
                .build());
        List<Review> reviews = reviewService.readList(ReviewSearchParameters.builder()
                .userId(userId)
                .build());
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);

        return "entity/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-user/{userId}")
    public String getUserUpdate(@PathVariable(value = "userId") int userId, ModelMap model) {
        User user = userService.readList(UserSearchParameters.builder()
                .id(userId)
                .build())
                .get(0);
        List<String> userRoles = new ArrayList<>();
        for (UserRole role : UserRole.class.getEnumConstants()) {
            userRoles.add(role.toString());
        }
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "update/update-user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-user")
    public String postUserUpdate(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update/update-user";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete-user")
    public String removeUser(@RequestParam(value = "userId") int userId) {
        List<Tour> tours = tourService.readList(TourSearchParameters.builder()
                .userId(userId)
                .build());
        List<Review> reviews = reviewService.readList(ReviewSearchParameters.builder()
                .userId(userId)
                .build());
        if (tours.size() > 0) {
            throw new DeleteException("There are some tours reference on deleting user");
        } else if (reviews.size() > 0) {
            throw new DeleteException("There are some reviews reference on deleting user");
        } else {
            userService.delete(User.builder()
                    .id(userId)
                    .build());
        }

        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/users-page/{page}", produces = "application/json")
    public @ResponseBody
    List<Object> getPage(@PathVariable("page") int page) {
        List<User> userList = userService.getPage(ROWS, page);
        List<UserDto> userDtos = userList.stream().map(UserDto::fromUserToDto).collect(Collectors.toList());
        int pages = userService.readAll().size() / ROWS + 1;
        List<Object> result = new ArrayList<>();
        result.add(userDtos);
        result.add(pages);
        result.add(page);
        return result;
    }
}
