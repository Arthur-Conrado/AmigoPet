package com.senai.amigopetter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelPet;
import com.senai.amigopetter.model.DisponibilidadePet;

@Repository
public interface RepositoryPet extends JpaRepository<ModelPet, Integer> {
    List<ModelPet> findByDisponibilidade(DisponibilidadePet disponibilidade);
    List<ModelPet> findByEspecieId(Integer idEspecie);
    List<ModelPet> findByNomeContainingIgnoreCase(String nome);
    List<ModelPet> findByRacaContainingIgnoreCase(String raca);
    Long countByDisponibilidade(DisponibilidadePet disponibilidade);
    List<ModelPet> findByIdadeBetween(Integer idadeMin, Integer idadeMax);
    List<ModelPet> findByCastrado(Boolean castrado);
}
