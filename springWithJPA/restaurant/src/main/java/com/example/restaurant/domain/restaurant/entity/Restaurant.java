package com.example.restaurant.domain.restaurant.entity;

import com.example.restaurant.domain.restaurant.dto.RestaurantInfo;
import com.example.restaurant.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

@Getter
//@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Category category;

    public void update(RestaurantInfo restaurantInfo){
        this.category = restaurantInfo.getCategory();
    }

    public void delete(){
        super.isActivated = false;
    }

}
