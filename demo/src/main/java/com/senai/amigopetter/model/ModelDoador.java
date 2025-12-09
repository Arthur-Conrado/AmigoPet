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
@Table(name = "Doador")
@Getter
@setter

public class Doador {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column (nullable = false, length =100)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(length=20)
    private String telefone;

    @Column(nullable = false,length=100, unique = true)
    private String email;

    @Column(name = "dt_Registro", nullable = false, updatable = false)
    private LocalDateTime dt_Registro;

    @Column (nullable = false, length =100)
    private String dt_update;

    @Column (nullable = false, length =100)
    private String dt_nascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", nullable = false)
    private Model login;

}
