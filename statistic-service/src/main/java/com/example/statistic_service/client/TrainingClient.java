package com.example.statistic_service.client;

import com.example.statistic_service.model.Training;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "training-service")
public interface TrainingClient {
    @GetMapping("/training/user/{id}")
    List<Training> getTrainings(@PathVariable int id);
}
