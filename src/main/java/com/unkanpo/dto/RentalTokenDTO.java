package com.unkanpo.dto;

public class RentalTokenDTO implements DataTransferObject<RentalTokenDTO>{
    private Long rentId;
    private String token;

    public RentalTokenDTO() {
    }

    public RentalTokenDTO(Long rentId, String token) {
        this.rentId = rentId;
        this.token = token;
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

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    @Override
    public RentalTokenDTO getDTO() {
        return this;
    }
}
