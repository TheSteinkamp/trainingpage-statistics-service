package com.example.statistic_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private int totalTrainings;
    private int totalDuration;
    private double averageDuration;

}
