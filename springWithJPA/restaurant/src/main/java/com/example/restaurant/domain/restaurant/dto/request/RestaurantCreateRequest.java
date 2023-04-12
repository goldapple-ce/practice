package com.example.restaurant.domain.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RestaurantCreateRequest {
    @NotBlank(message = "레스토랑 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "레스토랑 카테고리는 필수입니다.")
    private String category;
}
