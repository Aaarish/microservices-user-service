package com.microservices.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private String hotelId;
    private String hotelName;
    private String address;
}
