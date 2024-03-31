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
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gameType",
            joinColumns = {@JoinColumn(name = "idGame")},
            inverseJoinColumns = {@JoinColumn(name = "idType")})
    private Set<Type> types;
    private String nameGame;
    private String descriptionGame;
}