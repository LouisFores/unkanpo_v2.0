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
    private Long id_game;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_type",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_type")})
    private Set<Type> types;

    private String name_game;
    private String description_game;
}
