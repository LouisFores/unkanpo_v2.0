package com.unkanpo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gametypes")
public class GameType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    @ManyToOne
    @JoinColumn(name  = "idType")
    private Type type;

    public GameType(Game game, Type type) {
        this.game = game;
        this.type = type;
    }


    public GameType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
