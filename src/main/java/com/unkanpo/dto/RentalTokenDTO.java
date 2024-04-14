package com.unkanpo.dto;

public class RentalTokenDTO implements DataTransferObject<RentalTokenDTO>{
    private String token;

    public RentalTokenDTO() {
    }

    public RentalTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public RentalTokenDTO getDTO() {
        return this;
    }
}
