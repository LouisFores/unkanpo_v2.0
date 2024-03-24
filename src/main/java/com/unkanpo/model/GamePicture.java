package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gamepicture")
public class GamePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_picture;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    private String picture;
}
