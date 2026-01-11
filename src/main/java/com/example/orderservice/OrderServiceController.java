package com.example.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceController {
    @GetMapping("/health")
    public String order()
{
    return "health is good";
}

}