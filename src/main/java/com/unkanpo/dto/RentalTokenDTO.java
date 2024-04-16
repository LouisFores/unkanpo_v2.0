package com.unkanpo.dto;

public class RentalTokenDTO implements DataTransferObject<RentalTokenDTO>{
    private Long rentId;
    private String token;
    private String status;
    public RentalTokenDTO() {
    }

    public RentalTokenDTO(Long rentId, String token, String status) {
        this.rentId = rentId;
        this.token = token;
        this.status = status;
    }

    public RentalTokenDTO(String token) {
        this.token = token;
    }

    public Long getRentId() {
        return rentId;
    }

    public RentalTokenDTO setRentId(Long rentId) {
        this.rentId = rentId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public RentalTokenDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RentalTokenDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public RentalTokenDTO getDTO() {
        return this;
    }
}
