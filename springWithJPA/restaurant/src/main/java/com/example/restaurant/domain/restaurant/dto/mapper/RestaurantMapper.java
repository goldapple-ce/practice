package com.example.restaurant.domain.restaurant.dto.mapper;

import com.example.restaurant.domain.restaurant.dto.RestaurantInfo;
import com.example.restaurant.domain.restaurant.dto.RestaurantPageInfo;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.example.restaurant.domain.restaurant.entity.Category;
import com.example.restaurant.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RestaurantMapper {
    public RestaurantInfo mapEntityToInfo(Restaurant restaurant) {
        return RestaurantInfo.builder()
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .createAt(restaurant.getCreateAt())
                .build();
    }

    public RestaurantInfo mapUpdateRequestToInfo(RestaurantUpdateRequest request) {
        return RestaurantInfo.builder()
                .category(Category.valueOf(request.getCategory()))
                .build();
    }

    public RestaurantPageInfo mapEntityToRestaurantPageInfo(Page<Restaurant> restaurantList) {
        List<RestaurantInfo> restaurantInfoList =
                restaurantList.stream().map(this::mapEntityToInfo).collect(Collectors.toList());
        return RestaurantPageInfo.builder().restaurantInfos(restaurantInfoList).build();
    }
}