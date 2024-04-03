package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "billcoint")
@Data
public class BillCoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillCoint;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User idUser;

    private Long amount;
    private String dateTime;
}
