package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rentalhistory")
public class RentalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRental;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private GameAccount gameAccount;

    private String startTime;
    private String endTime;

    public RentalHistory() {
    }

    public RentalHistory(User user, GameAccount gameAccount) {
        this.user = user;
        this.gameAccount = gameAccount;
    }

    public Long getIdRental() {
        return idRental;
    }

    public void setIdRental(Long idRental) {
        this.idRental = idRental;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameAccount getGameAccount() {
        return gameAccount;
    }

    public void setGameAccount(GameAccount gameAccount) {
        this.gameAccount = gameAccount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void startRenting() {
        this.startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getEndTime() {
        return endTime;
    }

    public void stopRenting() {
        this.endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
