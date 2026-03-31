package com.example.statistic_service.service;

import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import com.example.statistic_service.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class StatisticsService {

    public Statistics getStats(List<Training> allTrainings) {
        int total = 0;
        int duration = 0;
        for (Training training : allTrainings) {
            total ++;
            duration += training.getDuration();
        }
        double average = (double) duration / total;
        return new Statistics(allTrainings.getFirst().getUserId(), total, duration, average);
    }

    public Statistics getStatsPerPeriod(List<Training> allTrainings, LocalDate startDate, LocalDate endDate) {
        int total = 0;
        int duration = 0;
        for (Training training : allTrainings) {
            if(training.getDate().isBefore(endDate) && training.getDate().isAfter(startDate)) {
                total ++;
                duration += training.getDuration();
            }
        }
        double average = (double) duration / total;
        return new Statistics(allTrainings.getFirst().getUserId(), total, duration, average);
    }

    public HashMap<String, Integer> getUserStatistics (List<Training> allTrainings, List<User> allUsers) {
        HashMap<String, Integer> userStatistics = new HashMap<>();

        for (User user : allUsers) {
            int noOfTrainings = 0;
            for (Training training : allTrainings) {
                if(training.getUserId() == user.getId()){
                    noOfTrainings++;
                }
            }
            userStatistics.put(user.getName(), noOfTrainings);
        }
        return userStatistics;
    }
}
