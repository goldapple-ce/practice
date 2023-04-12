package com.example.restaurant.domain.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RestaurantUpdateRequest {
    @NotNull(message = "레스토랑 id는 필수입니다.")
    private Long id;

    @NotBlank(message = "레스토랑 카테고리는 필수입니다.")
    private String category;
}
