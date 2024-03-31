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

    private String nameGame;
    private String descriptionGame;
}