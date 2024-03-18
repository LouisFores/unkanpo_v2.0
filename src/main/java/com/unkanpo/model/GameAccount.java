package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gameaccount")
public class GameAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;

    @ManyToOne
    @JoinColumn(name = "id_game")
    private Game game;

    @Column(columnDefinition = "TEXT")
    private String note;
    private String hide_info;
    private Long price;
    private String token;
    private Boolean is_online;
}
