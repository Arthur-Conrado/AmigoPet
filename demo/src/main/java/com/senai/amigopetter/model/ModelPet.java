package com.senai.amigopetter.model;

import java.time.LocalDateTime;
import com.senai.amigopetter.model.PortePet;
import com.senai.amigopetter.model.SexoPet;
import com.senai.amigopetter.model.DisponibilidadePet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Getter
@Setter
public class ModelPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String raca;

    @Column
    private Integer idade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PortePet porte;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoPet sexo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DisponibilidadePet disponibilidade;

    @Column(length = 500)
    private String fotos;

    @Column(length = 100)
    private String cor;

    @Column
    private Boolean vacinado;

    @Column
    private Integer qtd_doses;

    @Column(name = "dt_registro", nullable = false, updatable = false)
    private LocalDateTime dt_registro;

    @Column
    private Boolean castrado;

    @Column(length = 500)
    private String descricao;

    

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_especie", nullable = false)
    private ModelEspecie especie;

}
