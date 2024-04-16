package com.unkanpo.controller.api;

import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.AlertStatus;
import com.unkanpo.service.imp.RentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
public class RentApi {
    @Autowired
    private RentalHistoryService rentalService;

    @GetMapping("/{id}/online")
    public ResponseEntity continueRent(@PathVariable Long id) {
        rentalService.updateRentStatus(id);
        return new ResponseEntity<>(new AlertDTO(AlertStatus.Success, "vẫn đang thuê"), HttpStatus.OK);
    }

    @GetMapping("/{id}/offline")
    public ResponseEntity stopRent(@PathVariable Long id) {
        rentalService.stopRent(id);
        return new ResponseEntity<>(new AlertDTO(AlertStatus.Success, "đã ngừng thuê"), HttpStatus.OK);
    }
}
