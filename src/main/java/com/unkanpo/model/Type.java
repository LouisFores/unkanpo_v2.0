package com.unkanpo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "type")
@Data
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;

    private String nameType;

    public Type(Long idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }

    public Type() {

    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }


    public String convert() {
        return "idType=(" + this.idType + "), nameType=(" + this.nameType + ")";
    }
}