package com.senai.amigopetter.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adocao")
@Getter
@Setter
public class ModelAdocao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_adotante", nullable = false)
    private ModelAdotante adotante;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_doador", nullable = false)
    private ModelDoador doador;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_pet", nullable = false)
    private ModelPet pet;

    @Column(name = "dt_adocao", nullable = false, updatable = false)
    private LocalDateTime dt_adocao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAdocao status_adocao;

    @Column(length = 500)
    private String motivo_adotante;

    @Column(length = 500)
    private String motivo_doador;

    @Column(length = 500)
    private String observacoes;
}
