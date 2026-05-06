package com.example.statistic_service.controller;

import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import com.example.statistic_service.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/stats")
    public Statistics getTrainingsPerUser(@RequestParam Long id) {
        return statisticsService.getStats(id);
    }

    @GetMapping("/session")
    public List<Training> getTrainings(@RequestParam Long id) {
        return statisticsService.getTrainings(id);
    }

    @GetMapping("/period/stats")
    public Statistics getTrainingsPerUserAndPeriod(@RequestParam Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return statisticsService.getStatsPerPeriod(id, startDate, endDate);
    }

    @GetMapping("/period/session")
    public List<Training> getTrainingsPerPeriod(@RequestParam Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return statisticsService.getTrainingsPerPeriod(id, startDate, endDate);
    }

    @GetMapping("/chart")
    public Map<String, Long> getChartsPerUser(@RequestParam Long id) {
        return statisticsService.getCharts(id);
    }

    @GetMapping("/users")
    public Map<String, Integer> getUserStatistics() {
        return statisticsService.getUserStatistics();
    }
}
