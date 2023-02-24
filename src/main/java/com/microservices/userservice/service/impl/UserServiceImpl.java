package com.microservices.userservice.service.impl;

import com.microservices.userservice.converter.UserConverter;
import com.microservices.userservice.dto.Hotel;
import com.microservices.userservice.dto.Rating;
import com.microservices.userservice.dto.UserDto;
import com.microservices.userservice.entity.User;
import com.microservices.userservice.repository.UserRepo;
import com.microservices.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userConverter.convertDtoToEntity(userDto);
        User savedUser = userRepo.save(user);
        return userConverter.convertEntityToDto(savedUser);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepo.findById(userId).get();

        Rating[] ratingForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratingsForUser = Arrays.stream(ratingForUser)
                .collect(Collectors.toList());

        List<Rating> ratingList = ratingsForUser.stream()
                .map(rating -> {
                    Hotel hotelRatedByUser = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                    logger.info("hotel rated by user: {}", hotelRatedByUser);
                    rating.setHotel(hotelRatedByUser);
                    return rating;
                })
                .collect(Collectors.toList());

        user.setRatings(ratingList);
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
