package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gameimage")
public class GameImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    private String image;

    public GameImage(Game game, String image) {
        this.game = game;
        this.image = image;
    }

    public GameImage() {
    }

    public Long getIdImage() {
        return idImage;
    }

    public void setIdImage(Long idImage) {
        this.idImage = idImage;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
