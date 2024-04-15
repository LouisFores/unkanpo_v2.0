package com.unkanpo.controller.api;

import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.AlertStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rents")
public class RentApi {
    @GetMapping("/{id}")
    public ResponseEntity checkRentStatus(@PathVariable String id) {
        return new ResponseEntity<>(new AlertDTO(AlertStatus.Success,"vẫn đang thuê"), HttpStatus.OK);
    }
}
