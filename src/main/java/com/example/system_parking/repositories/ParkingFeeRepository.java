package com.example.system_parking.repositories;

import com.example.system_parking.models.ParkingFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFeeRepository extends JpaRepository<ParkingFee, Long> {
}
