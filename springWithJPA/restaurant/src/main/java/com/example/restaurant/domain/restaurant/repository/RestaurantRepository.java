package com.example.restaurant.domain.restaurant.repository;

import com.example.restaurant.domain.restaurant.entity.Category;
import com.example.restaurant.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("select r from Restaurant r where r.isActivated is true")
    Page<Restaurant> findRestaurantWithPagination(Pageable pageable);

    @Query("select r from Restaurant r where r.isActivated is true and r.category = ?1")
    Page<Restaurant> findRestaurantWithCategoryAndPagination(Category category, Pageable pageable);
}
