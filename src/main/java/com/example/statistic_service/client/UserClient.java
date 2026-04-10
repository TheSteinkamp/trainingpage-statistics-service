package com.example.statistic_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.statistic_service.model.User;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/all")
    List<User> getUsers();
}
