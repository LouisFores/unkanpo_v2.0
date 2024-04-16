package com.unkanpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertDTO {
    @JsonProperty("alertStatus")
    private AlertStatus alertStatus;
    private String message;
    private String token;
    private Long idRent;

    public AlertDTO() {
        this.alertStatus = AlertStatus.None;
        this.message = "";
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

    public String getStatus() {
        return alertStatus.toString();
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

    public AlertStatus getAlertStatus() {
        return alertStatus;
    }

    public AlertDTO setAlertStatus(AlertStatus alertStatus) {
        this.alertStatus = alertStatus;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AlertDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getIdRent() {
        return idRent;
    }

    public AlertDTO setIdRent(Long idRent) {
        this.idRent = idRent;
        return this;
    }
}
