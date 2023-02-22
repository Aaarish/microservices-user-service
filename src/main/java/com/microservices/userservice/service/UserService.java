package com.microservices.userservice.service;

import com.microservices.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUser(String userId);

    List<UserDto> getUsers();

    String removeUser(String userId);
}
