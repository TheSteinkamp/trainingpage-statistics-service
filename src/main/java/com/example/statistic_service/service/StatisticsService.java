package com.example.statistic_service.service;

import com.example.statistic_service.client.TrainingClient;
import com.example.statistic_service.client.UserClient;
import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import com.example.statistic_service.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final TrainingClient trainingClient;
    private final UserClient userClient;

    public StatisticsService(TrainingClient trainingClient, UserClient userClient) {
        this.trainingClient = trainingClient;
        this.userClient = userClient;
    }

    public List<Training> getTrainings(Long id) {
        return trainingClient.getTrainingsPerUser(id);
    }

    public List<Training> getTrainingsPerPeriod(Long id, LocalDate startDate, LocalDate endDate) {
        List<Training> allTrainings = trainingClient.getTrainingsPerUser(id);
        List<Training> filteredTrainings = allTrainings.stream().filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate)).collect(Collectors.toList());
        System.out.println(filteredTrainings);
        return filteredTrainings;
    }

    public Statistics getStats(Long id) {
        List<Training> allTrainings = trainingClient.getTrainingsPerUser(id);
        int total = 0;
        int duration = 0;
        for (Training training : allTrainings) {
            total++;
            duration += training.getDuration();
        }
        double average = (double) duration / total;
        return new Statistics(total, duration, average);
    }

    public Statistics getStatsPerPeriod(Long id, LocalDate startDate, LocalDate endDate) {
        List<Training> allTrainings = trainingClient.getTrainingsPerUser(id);
        int total = 0;
        int duration = 0;
        for (Training training : allTrainings) {
            if (!training.getDate().isBefore(startDate) && !training.getDate().isAfter(endDate)) {
                total++;
                duration += training.getDuration();
            }
        }
        double average = (double) duration / total;
        return new Statistics(total, duration, average);
    }

    public Map<String, Integer> getUserStatistics() {
        List<User> allUsers = userClient.getUsers();
        List<Training> allTrainings = trainingClient.getAllTrainings();
        Map<String, Integer> userStatistics = new HashMap<>();

        for (User user : allUsers) {
            int noOfTrainings = 0;
            for (Training training : allTrainings) {
                if (training.getUserId() == user.getId()) {
                    noOfTrainings++;
                }
            }
            userStatistics.put(user.getName(), noOfTrainings);
        }
        return userStatistics;
    }

    public Map<String, Long> getCharts(Long id) {
        List<Training> allTrainings = trainingClient.getTrainingsPerUser(id);
        Map<String, Long> usercharts = allTrainings.stream()
                .map(t -> t.getType().toLowerCase())
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
        return usercharts;
    }
}
