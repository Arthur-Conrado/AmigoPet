package com.senai.amigopetter.model;

import java.beans.ConstructorProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "pet")
@Getter
@setter

public class ModelPet {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column (nullable = false, length =100)
    private String nome;

    @Column(nullable = false,length=100)
    private String raca;
    
    @Column(length=20)
    private String idade;

    @Column(nullable = false,length=100)
    private String porte;

    @Column(length=100)
    private String sexo;

    @Column(nullable = false,length=100)
    private String disponibilidade;

    @Column(length=100)
    private Url foto;

    @Column(nullable = false,length=100)
    private String cor;

    @Column(nullable = false,length=100)
    private String vacinado;

    @Column(nullable = false,length=100)
    private String qtd_doses;

    @Column(nullable = false,length=100)
    private String dt_registro;

    @Column(nullable = false,length=100)
    private String castrado;

    @Column(nullable = false,length=100)
    private String descricao;

     @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_especie", nullable = false)
    private String Especie;



}
