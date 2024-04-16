package com.unkanpo.dto;

public class AccountDTO {
    private Long idAccount;
    private String note;
    private int price;
    private String token;
    private Boolean isOnline;

    public AccountDTO() {
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public AccountDTO setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
        return this;
    }

    public String getNote() {
        return note;
    }

    public AccountDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AccountDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AccountDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public AccountDTO setOnline(Boolean online) {
        isOnline = online;
        return this;
    }


}
