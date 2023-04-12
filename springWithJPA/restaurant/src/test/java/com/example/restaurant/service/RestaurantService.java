package com.example.restaurant.service;


import com.example.restaurant.domain.restaurant.entity.Restaurant;
import com.example.restaurant.domain.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantService {
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant restaurant1,restaurant2;

    @BeforeEach
    public void setUp() {

    }
}
