package com.example.restaurant.domain.restaurant.service;

import com.example.restaurant.domain.restaurant.dto.RestaurantInfo;
import com.example.restaurant.domain.restaurant.dto.RestaurantPageInfo;
import com.example.restaurant.domain.restaurant.dto.mapper.RestaurantMapper;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.example.restaurant.domain.restaurant.entity.Category;
import com.example.restaurant.domain.restaurant.entity.Restaurant;
import com.example.restaurant.domain.restaurant.exception.NotFoundRestaurantException;
import com.example.restaurant.domain.restaurant.repository.RestaurantRepository;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    public List<Restaurant> findAllRestaurant(){
        return restaurantRepository.findAll();
    }

    public RestaurantInfo findRestaurantById(long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(NotFoundRestaurantException::new);
        return mapper.mapEntityToInfo(restaurant);
    }

    public RestaurantInfo saveRestaurant(RestaurantCreateRequest request){
        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .category(Category.valueOf(request.getCategory()))
                .build();
        return mapper.mapEntityToInfo(restaurantRepository.save(restaurant));
    }

    @Transactional(readOnly = true)
    public RestaurantInfo updateRestaurant(RestaurantUpdateRequest request){
        Restaurant restaurant = restaurantRepository
                .findById(request.getId())
                .orElseThrow(NotFoundRestaurantException::new);
        restaurant.update(mapper.mapUpdateRequestToInfo(request));
        return mapper.mapEntityToInfo(restaurantRepository.save(restaurant));
    }

    @Transactional(readOnly = true)
    public Restaurant deleteRestaurant(long id){
        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(NotFoundRestaurantException::new);
        restaurant.delete();
        return restaurantRepository.save(restaurant);
    }

    @Transactional(readOnly = true)
    public RestaurantPageInfo getRestaurantByPagination(int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        Page<Restaurant> restaurantByPagination = restaurantRepository.findRestaurantWithPagination(pageRequest);
        return mapper.mapEntityToRestaurantPageInfo(restaurantByPagination);
    }

    @Transactional(readOnly = true)
    public RestaurantPageInfo getRestaurantByCategoryAndPagination(int offset, int size, Category category) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        Page<Restaurant> restaurantByPagination = restaurantRepository.findRestaurantWithCategoryAndPagination(category, pageRequest);
        return mapper.mapEntityToRestaurantPageInfo(restaurantByPagination);
    }
}
