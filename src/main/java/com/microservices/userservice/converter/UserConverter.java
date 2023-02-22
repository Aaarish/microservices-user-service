package com.microservices.userservice.converter;

import com.microservices.userservice.dto.UserDto;
import com.microservices.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter {

    public UserDto convertEntityToDto(User user){
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getAbout())
                .ratings(user.getRatings())
                .build();

        return userDto;
    }

    public User convertDtoToEntity(UserDto userDto){
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                .ratings(userDto.getRatings())
                .build();

        return user;
    }
}
