package by.epam.kvirykashvili.javalabtask.service.impl;

import by.epam.kvirykashvili.javalabtask.domain.model.Tour;
import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.repository.UserRepository;
import by.epam.kvirykashvili.javalabtask.domain.parameters.SearchParameters;
import by.epam.kvirykashvili.javalabtask.domain.parameters.UserSearchParameters;
import by.epam.kvirykashvili.javalabtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.create(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.readAll();
    }

    @Override
    public List<User> readList(SearchParameters parameters) {
        return userRepository.readList(parameters);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<User> userList = userRepository.readList(UserSearchParameters.builder()
                .login(login)
                .build());
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            throw new UsernameNotFoundException("User with login: '" + login + "' wasn't found");
        }
    }

    @Override
    public void addFavouriteTour(User user, Tour tour) {
        userRepository.addFavouriteTour(user, tour);
    }

    @Override
    public void removeFavouriteTour(User user, Tour tour) {
        userRepository.removeFavouriteTour(user, tour);
    }

    @Override
    public List<User> getPage(int rows, int page) {
        @SuppressWarnings("unchecked")
        List<User> users = userRepository.getPage(rows, page);
        return users;
    }
}
