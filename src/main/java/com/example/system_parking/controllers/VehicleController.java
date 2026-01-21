package com.example.system_parking.controllers;

import com.example.system_parking.models.ParkingFee;
import com.example.system_parking.models.ParkingSpot;
import com.example.system_parking.models.Vehicle;
import com.example.system_parking.repositories.ClientRepository;
import com.example.system_parking.repositories.ParkingFeeRepository;
import com.example.system_parking.repositories.ParkingSpotRepository;
import com.example.system_parking.repositories.VehicleRepository;
import com.example.system_parking.service.ParkingService;
import com.example.system_parking.service.PdfService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingFeeRepository parkingFeeRepository;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/retire/{id}")
    public ResponseEntity<ByteArrayResource> retireVehicle(@PathVariable Long id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id:" + id));
        vehicle.setExitTime(LocalDateTime.now());
        double fee = parkingService.calculateFee(vehicle);

        // Save the parking fee
        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setVehicle(vehicle);
        parkingFee.setTotalFee(fee);
        parkingFeeRepository.save(parkingFee);

        // Update vehicle and parking spot
        ParkingSpot parkingSpot = vehicle.getParkingSpot();
        if (parkingSpot != null) {
            parkingSpot.setOccupied(false);
            parkingSpot.setVehicle(null);
            parkingSpotRepository.save(parkingSpot);
        }

        vehicleRepository.save(vehicle);

        // Genera el PDF
        byte[] pdfBytes;
        try {
            pdfBytes = pdfService.generateVehicleReport(
                    vehicle.getId().toString(),
                    vehicle.getEntryTime().toString(),
                    vehicle.getExitTime().toString(),
                    fee
            );
        } catch (IOException | DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehicle_report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/vehicles/new")
    public String showCreateForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("parkingSpots", parkingSpotRepository.findAll());
        return "create_vehicle";
    }

    @PostMapping("/vehicles")
    public String createVehicle(@ModelAttribute Vehicle vehicle) {
        vehicle.setEntryTime(LocalDateTime.now());

        Optional<ParkingSpot> parkingSpotOpt = parkingSpotRepository.findById(vehicle.getParkingSpot().getId());
        if (parkingSpotOpt.isPresent()) {
            ParkingSpot parkingSpot = parkingSpotOpt.get();
            parkingSpot.setOccupied(true);
            parkingSpot.setVehicle(vehicle);
            vehicle.setParkingSpot(parkingSpot);

            vehicleRepository.save(vehicle);
            parkingSpotRepository.save(parkingSpot);
        } else {
            throw new IllegalArgumentException("Invalid parking spot ID");
        }

        return "redirect:/vehicles/new";
    }


    @GetMapping("/vehicles/list")
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getParkingSpot() == null) {
                vehicle.setParkingSpot(new ParkingSpot());
                vehicle.getParkingSpot().setSpotNumber("N/A");
            }
        }
        model.addAttribute("vehicles", vehicles);
        return "list_vehicles";
    }
}
