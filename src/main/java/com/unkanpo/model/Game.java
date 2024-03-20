package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "game")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gameType",
            joinColumns = {@JoinColumn(name = "idGame")},
            inverseJoinColumns = {@JoinColumn(name = "idType")})
    private Set<Type> types;

    private String name_game;
    private String description_game;
}
