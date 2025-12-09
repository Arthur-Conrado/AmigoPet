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
@Table(name = "Adotante")
@Getter
@setter
public class ModelAdotante{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column (nullable = false, length =100)
    private String nome;

    @Column(length=20)
    private String telefone;

    @Column(nullable = false,length=100, unique = true)
    private String email;
}