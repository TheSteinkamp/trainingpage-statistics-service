package com.example.statistic_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    private Long id;
    private LocalDate date;
    private int duration;
    private Long userId;
}
