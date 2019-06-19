package by.epam.kvirykashvili.javalabtask.domain.model.dto;

import by.epam.kvirykashvili.javalabtask.domain.model.User;
import by.epam.kvirykashvili.javalabtask.domain.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDto {
    private int id;
    private String login;
    private String password;
    private UserRole role;

    public static UserDto fromUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getUserRole())
                .build();
    }
}
