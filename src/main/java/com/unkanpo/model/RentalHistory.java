package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rentalhistory")
public class RentalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rental;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private GameAccount gameAccount;

    private String start_time;
    private String end_time;
    private Long total;
}
