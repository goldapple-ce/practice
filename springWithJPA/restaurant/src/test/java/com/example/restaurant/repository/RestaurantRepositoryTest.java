package com.example.restaurant.repository;

import com.example.restaurant.domain.restaurant.entity.Restaurant;
import com.example.restaurant.domain.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant1, restaurant2;

    @BeforeEach
    public void setUp(){
        restaurant1 = restaurantRepository.save(Restaurant.builder()
                .name("testRestaurant1")
                .category("양식")
                .build());
        restaurant2 = restaurantRepository.save(Restaurant.builder()
                .name("testRestaurant2")
                .category("양식")
                .build());
    }

    @Disabled
    @Test
    public void createRestaurant(){
        Restaurant restaurant2 = Restaurant.builder()
                .name("testRestaurant2")
                .category("양식")
                .build();

        Restaurant restaurant3 = Restaurant.builder()
                        .category("양식")
                .build();

        restaurantRepository.save(restaurant2);

        assertThrows(DataIntegrityViolationException.class,
                () -> {
            restaurantRepository.save(restaurant3);
        });
    }

    @Test
    public void findAllRestaurant(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        assertEquals(2, restaurants.size());
        assertEquals(restaurant1.getName(),restaurants.get(0).getName());

        for(Restaurant restaurant : restaurants){
            System.out.println(restaurant.getId());
        }
    }

    @Test
    public void findRestaurantById(){
        long id = restaurant1.getId();

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(RuntimeException::new);

        long finalId = 1L;
        assertThrows(RuntimeException.class,() -> {
            restaurantRepository.findById(finalId).orElseThrow(RuntimeException::new);
        });
    }
}
