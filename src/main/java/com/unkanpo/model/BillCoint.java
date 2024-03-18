package com.unkanpo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "billcoint")
@Data
public class BillCoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bill_coint;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private Long amount;
    private String date_time;
}
