package com.microservices.userservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<Rating> ratings=new ArrayList<>();
}
