package com.example.statistic_service.service;

import com.example.statistic_service.model.Statistics;
import com.example.statistic_service.model.Training;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsService {

    public Statistics getStats(List<Training> alltrainings) {
        int total = 0;
        int duration = 0;
        for (Training training : alltrainings) {
            total ++;
            duration += training.getDuration();
        }
        double average = (double) duration / total;
        return new Statistics(alltrainings.getFirst().getUserId(), total, duration, average);
    }

    public Statistics getStatsPerPeriod(List<Training> alltrainings, LocalDate startDate, LocalDate endDate) {
        int total = 0;
        int duration = 0;
        for (Training training : alltrainings) {
            if(training.getDate().isBefore(endDate) && training.getDate().isAfter(startDate)) {
                total ++;
                duration += training.getDuration();
            }
        }
        double average = (double) duration / total;
        return new Statistics(alltrainings.getFirst().getUserId(), total, duration, average);
    }
}
