package com.example.system_parking.service;

import com.example.system_parking.models.Vehicle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ParkingService {
    private static final double RATE_PER_HOUR = 4.0;

    public double calculateFee(Vehicle vehicle) {
        LocalDateTime entryTime = vehicle.getEntryTime();
        LocalDateTime exitTime = vehicle.getExitTime();
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();
        return hours * RATE_PER_HOUR;
    }
}