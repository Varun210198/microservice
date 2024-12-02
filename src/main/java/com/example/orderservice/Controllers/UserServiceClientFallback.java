package com.example.orderservice.Controllers;

import org.springframework.stereotype.Component;

@Component
class UserServiceClientFallback implements UserServiceClient {
    @Override
    public String getUserById(Long id) {
        System.out.println("FAll Back Executed..!");
        // Return fallback response
        return new String("Userservice call failed..!");
    }
}
