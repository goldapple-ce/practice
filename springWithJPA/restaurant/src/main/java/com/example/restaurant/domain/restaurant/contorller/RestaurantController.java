package com.example.restaurant.domain.restaurant.contorller;

import com.example.restaurant.domain.restaurant.dto.RestaurantInfo;
import com.example.restaurant.domain.restaurant.dto.RestaurantPageInfo;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.example.restaurant.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.example.restaurant.domain.restaurant.entity.Category;
import com.example.restaurant.domain.restaurant.entity.Restaurant;
import com.example.restaurant.domain.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("")
    public List<Restaurant> findAllRestaurant(){
        return restaurantService.findAllRestaurant();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantInfo> findResuarantById(@PathVariable("id") long id){
        return ResponseEntity.ok(restaurantService.findRestaurantById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<RestaurantPageInfo> getRestaurantByPagination(
            @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int size){
        RestaurantPageInfo restaurantPageInfo = restaurantService.getRestaurantByPagination(offset, size);
        return ResponseEntity.ok(restaurantPageInfo);
    }

    @GetMapping("/page/{category}")
    public ResponseEntity<RestaurantPageInfo> getRestaurantByCategoryAndPagination(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String category){
        RestaurantPageInfo restaurantPageInfo = restaurantService.getRestaurantByCategoryAndPagination(offset, size, Category.valueOf(category));
        return ResponseEntity.ok(restaurantPageInfo);
    }

    @PostMapping("")
    public ResponseEntity<RestaurantInfo> saveRestuarant(@Valid @RequestBody RestaurantCreateRequest request){
        return ResponseEntity.ok(restaurantService.saveRestaurant(request));
    }

    @PutMapping
    public ResponseEntity<RestaurantInfo> updateRestaurant(@Valid @RequestBody RestaurantUpdateRequest request){
        return ResponseEntity.ok(restaurantService.updateRestaurant(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
