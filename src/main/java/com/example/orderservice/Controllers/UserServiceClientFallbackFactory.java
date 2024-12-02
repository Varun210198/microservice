package com.example.orderservice.Controllers;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {
    @Override
    public UserServiceClient create(Throwable cause) {
        System.out.println("Fallback implemented");
        return id -> {
            // Log the exception
            System.err.println("Fallback triggered: " + cause.getMessage());
            return "Fallback triggered: " + cause.getMessage();
        };
    }
}
