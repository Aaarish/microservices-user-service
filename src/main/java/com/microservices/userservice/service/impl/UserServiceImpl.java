package com.microservices.userservice.service.impl;

import com.microservices.userservice.converter.UserConverter;
import com.microservices.userservice.dto.Rating;
import com.microservices.userservice.dto.UserDto;
import com.microservices.userservice.entity.User;
import com.microservices.userservice.repository.UserRepo;
import com.microservices.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userConverter.convertDtoToEntity(userDto);
        User savedUser = userRepo.save(user);
        return userConverter.convertEntityToDto(savedUser);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepo.findById(userId).get();

        List<Rating> ratingForUser = restTemplate.getForObject("http://localhost:8081/ratings/users/" + user.getUserId(), ArrayList.class);
        user.setRatings(ratingForUser);
        userRepo.save(user);

        return userConverter.convertEntityToDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();

        List<UserDto> userDtos = users.stream()
                .map(u -> userConverter.convertEntityToDto(u))
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public String removeUser(String userId) {
        userRepo.deleteById(userId);
        return "User deleted successfully";
    }
}
