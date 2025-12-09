package com.senai.amigopetter.model;
import java.lang.annotation.Inherited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Especie")
@Getter
@setter
public class Especie {
      @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column (nullable = false, length =100)
    private String nome_especie;

}
