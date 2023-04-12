package com.example.restaurant.domain.restaurant.dto;

import com.example.restaurant.domain.restaurant.entity.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantInfo {
    private String name;
    private Category category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createAt;
}
