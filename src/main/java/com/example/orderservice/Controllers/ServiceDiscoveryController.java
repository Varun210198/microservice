package com.example.orderservice.Controllers;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceDiscoveryController {

    private final DiscoveryClient discoveryClient;

    public ServiceDiscoveryController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/discovered-services")
    public List<String> getDiscoveredServices() {
        // Returns a list of service names (e.g., user-service, order-service)
        return discoveryClient.getServices();
    }

    @GetMapping("/service-instances/userservice")
    public List<ServiceInstance> getUserServiceInstances() {
        // Returns the list of instances for the service 'user-service'
        return discoveryClient.getInstances("userservice");
    }
}

