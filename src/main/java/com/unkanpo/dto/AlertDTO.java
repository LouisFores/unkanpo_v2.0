package com.unkanpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertDTO {
    @JsonProperty("alertStatus")
    private AlertStatus alertStatus;
    private String message;
    private String token;
    private Long idRent;

    public AlertDTO() {
    }

    public AlertDTO(AlertStatus alertStatus, String message) {
        this.alertStatus = alertStatus;
        this.message = message;
    }

    public AlertDTO(AlertStatus alertStatus, String message, RentalTokenDTO rentalTokenDTO) {
        this.alertStatus = alertStatus;
        this.message = message;
        this.token = rentalTokenDTO.getToken();
        this.idRent = rentalTokenDTO.getRentId();
    }

    public AlertStatus getStatus() {
        return alertStatus;
    }

    public void setStatus(AlertStatus alertStatus) {
        this.alertStatus = alertStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
