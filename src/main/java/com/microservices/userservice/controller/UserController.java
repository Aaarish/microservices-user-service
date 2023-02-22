package com.microservices.userservice.controller;

import com.microservices.userservice.dto.UserDto;
import com.microservices.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.removeUser(userId));
    }
}
