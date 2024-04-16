package com.unkanpo.model;

import com.unkanpo.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gameaccount")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String hideInfo;
    private Integer price;
    private String token;
    private Boolean isRent;

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getOnline() {
        return isRent;
    }

    public void setOnline(Boolean online) {
        isRent = online;
    }
    public AccountDTO toAccountDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setIdAccount(this.idAccount)
                .setPrice(this.price)
                .setToken(this.token)
                .setOnline(this.isRent);
        return accountDTO;
    }
}
