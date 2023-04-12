package com.example.restaurant.domain.restaurant.exception;

import com.example.restaurant.global.error.ErrorCode;
import com.example.restaurant.global.exception.BusinessException;

public class NotFoundRestaurantException extends BusinessException {
    public NotFoundRestaurantException(){
        super(ErrorCode.NOT_FOUND_RESTAURANT_ENTITY);
    }
}
