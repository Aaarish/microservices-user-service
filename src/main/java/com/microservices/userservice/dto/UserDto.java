package com.microservices.userservice.dto;

import lombok.*;

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
}
