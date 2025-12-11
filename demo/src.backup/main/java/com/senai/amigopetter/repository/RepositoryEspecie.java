package com.senai.amigopetter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelEspecie;

@Repository
public interface RepositoryEspecie extends JpaRepository<ModelEspecie, Integer> {
    Optional<ModelEspecie> findByNomeEspecieIgnoreCase(String nomeEspecie);
    boolean existsByNomeEspecieIgnoreCase(String nomeEspecie);
    List<ModelEspecie> findByNomeEspecieContainingIgnoreCase(String nomeEspecie);
}