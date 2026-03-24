package com.example.statistic_service.client;

import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/all")
    List<User> getUsers(@PathVariable int id);
}
