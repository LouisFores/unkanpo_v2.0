package com.unkanpo.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AlertStatus {
    Success("Success"), Warning("Warning"), Error("Error"),None("None");

    private final String description;

    AlertStatus(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return description;
    }
}



