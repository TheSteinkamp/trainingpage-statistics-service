package com.example.statistic_service.controller;

import com.example.statistic_service.client.TrainingClient;
import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import com.example.statistic_service.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private TrainingClient trainingClient;
    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService, TrainingClient trainingClient) {
        this.statisticsService = statisticsService;
        this.trainingClient = trainingClient;
    }

    @GetMapping("{id}")
    public List<Training> getTrainings(@PathVariable int id) {
        return trainingClient.getTrainings(id);
    }
    @GetMapping("/user/{id}")
    public Statistics getUserTrainings(@PathVariable int id) {
        List<Training> alltrainings = trainingClient.getTrainings(id);
        return statisticsService.getStats(alltrainings);
    }
    @GetMapping("/period/user/{id}")
    public Statistics getUserTrainingsPerPeriod(@PathVariable int id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Training> alltrainings = trainingClient.getTrainings(id);
        return statisticsService.getStatsPerPeriod(alltrainings, startDate, endDate);
    }
}
