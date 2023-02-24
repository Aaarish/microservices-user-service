package com.microservices.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String remark;
    private Hotel hotel;
}
