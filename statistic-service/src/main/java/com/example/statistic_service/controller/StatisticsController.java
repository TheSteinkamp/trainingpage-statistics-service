package com.example.statistic_service.controller;

import com.example.statistic_service.client.TrainingClient;
import com.example.statistic_service.client.UserClient;
import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import com.example.statistic_service.model.User;
import com.example.statistic_service.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticsController {

    private final TrainingClient trainingClient;
    private final UserClient userClient;
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService, TrainingClient trainingClient, UserClient userClient) {
        this.statisticsService = statisticsService;
        this.trainingClient = trainingClient;
        this.userClient = userClient;
    }

    @GetMapping("{id}")
    public List<Training> getTrainings(@PathVariable int id) {
        return trainingClient.getTrainings(id);
    }

    @GetMapping("/user/{id}")
    public Statistics getTrainingsPerUser(@PathVariable int id) {
        List<Training> allTrainings = trainingClient.getTrainings(id);
        return statisticsService.getStats(allTrainings);
    }

    @GetMapping("/period/user/{id}")
    public Statistics getTrainingsPerUserAndPeriod(@PathVariable int id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Training> allTrainings = trainingClient.getTrainings(id);
        return statisticsService.getStatsPerPeriod(allTrainings, startDate, endDate);
    }

    @GetMapping("/users")
    public HashMap<String, Integer> getUserStatistics (){
        List<User> allUsers = userClient.getUsers();
        List<Training> allTrainings = trainingClient.getAllTrainings();
        return statisticsService.getUserStatistics(allTrainings, allUsers);
    }
}
