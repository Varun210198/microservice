package com.example.orderservice.Controllers;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableDiscoveryClient
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    ServiceDiscoveryController serviceDiscoveryController;
    UserServiceClient userServiceClient;

    public OrderController(@Qualifier("com.example.orderservice.Controllers.UserServiceClient") UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    private static final Map<Integer, String> orders = new HashMap<>();

    static {
        orders.put(1, "Order for User 1");
        orders.put(2, "Order for User 2");
        orders.put(3, "Order for User 3");
    }

    @GetMapping("/{orderId}")
    public Map<String, String> getOrderDetails(@PathVariable int orderId) {
        String order = orders.getOrDefault(orderId, "Order not found");

//        List<String> discoveredServices = serviceDiscoveryController.getDiscoveredServices();
//        discoveredServices.stream().forEach(System.out::println);
//
//
//        List<ServiceInstance> instances = serviceDiscoveryController.getUserServiceInstances();
//        instances.stream().forEach(instance -> System.out.println("Host : "+instance.getHost()+" Prt : "+instance.getPort()));
//
//        String host = instances.get(0).getHost();
//        int port = instances.get(0).getPort();
//        URI uri = instances.get(0).getUri();

//        System.out.println("URI :"+uri);
//        // Call User Service to get user details
//        RestTemplate restTemplate = new RestTemplate();
//        String userServiceUrl = uri+"/users/" + orderId;
//        System.out.println(userServiceUrl);

        //String user = restTemplate.getForObject(userServiceUrl, String.class);

        //Using Feign Client
        String user1 = userServiceClient.getUserById(Long.valueOf(orderId));
        System.out.println("By Feign Client : " +user1);


        Map<String, String> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user1);

        return response;
    }
}



