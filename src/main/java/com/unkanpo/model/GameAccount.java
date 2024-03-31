package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gameaccount")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    @Column(columnDefinition = "TEXT")
    private String note;

    private String hideInfo;
    private Long price;
    private String token;
    private Boolean isOnline;
}
