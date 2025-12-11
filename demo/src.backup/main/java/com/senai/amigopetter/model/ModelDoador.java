package com.senai.amigopetter.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doador")
@Getter
@Setter
public class ModelDoador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "dt_registro", nullable = false, updatable = false)
    private LocalDateTime dt_registro;

    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dt_update;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDateTime dt_nascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", nullable = false)
    private ModelLogin login;
}
