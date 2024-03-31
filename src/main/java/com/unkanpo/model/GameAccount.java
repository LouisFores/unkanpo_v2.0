package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gameaccount")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    @Column(columnDefinition = "TEXT")
    private String note;
    private String hideInfo;
    private Long price;
    private String token;
    private Boolean isOnline;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHideInfo() {
        return hideInfo;
    }

    public void setHideInfo(String hideInfo) {
        this.hideInfo = hideInfo;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
