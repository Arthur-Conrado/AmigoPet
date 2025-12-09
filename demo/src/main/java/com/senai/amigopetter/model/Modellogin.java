package com.senai.amigopetter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "login")
@Getter
@setter
public class login {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column (nullable = false, length =100)
    private String usuario;

    @Column(nullable = false,length=100, unique = true)
    private String email;

    @Column(length=20)
    private String telefone;

    @Column(length=100)
    private String senha;

}
