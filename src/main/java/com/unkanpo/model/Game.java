package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.mapping.List;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "game")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;

    @Column(unique = true)
    private String nameGame;

    private String descriptionGame;

    public Game() {
    }

    public Game( String nameGame, String descriptionGame) {
        this.nameGame = nameGame;
        this.descriptionGame = descriptionGame;
    }
    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public String getDescriptionGame() {
        return descriptionGame;
    }

    public void setDescriptionGame(String descriptionGame) {
        this.descriptionGame = descriptionGame;
    }
}