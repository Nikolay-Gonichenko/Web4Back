package com.example.webback.converter;

import com.example.webback.dto.UserDto;
import com.example.webback.entity.User;
import com.example.webback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private RoleRepository roleRepository;

    public User fromUserDtoToUser(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword(), roleRepository.findByName("ROLE_USER"));
    }

    public UserDto fromUserToUserDto(User user) {
        return new UserDto(user.getUsername(), user.getPassword());
    }
}
