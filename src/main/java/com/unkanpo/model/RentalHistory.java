package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String lastTimeRequest;
    private boolean isOnline;
    private String endTime;
    private int total;

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
        this.isOnline = true;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getTotal() {
        return total;
    }

    public RentalHistory setTotal(int total) {
        this.total = total;
        return this;
    }

    public void updateStatus() {
        this.endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public void endRent() {
        this.setOnline(false);
    }

    public void totalCoin(int price) {
        LocalDateTime startTime = LocalDateTime.parse(this.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endTime = LocalDateTime.parse(this.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Duration duration = Duration.between(startTime, endTime);
        int minutes = (int) duration.toMinutes() / 5;
        this.setTotal(minutes*price);
//        rentalHistory.setTotal(minutes*price);
    }
}
