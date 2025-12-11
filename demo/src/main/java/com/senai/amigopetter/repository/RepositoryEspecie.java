package com.senai.amigopetter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelEspecie;

@Repository
public interface RepositoryEspecie extends JpaRepository<ModelEspecie, Integer> {
    @Query("SELECT e FROM ModelEspecie e WHERE LOWER(e.nome_especie) = LOWER(:nomeEspecie)")
    Optional<ModelEspecie> findByNomeEspecieIgnoreCase(@Param("nomeEspecie") String nomeEspecie);
    
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM ModelEspecie e WHERE LOWER(e.nome_especie) = LOWER(:nomeEspecie)")
    boolean existsByNomeEspecieIgnoreCase(@Param("nomeEspecie") String nomeEspecie);
    
    @Query("SELECT e FROM ModelEspecie e WHERE LOWER(e.nome_especie) LIKE LOWER(CONCAT('%', :nomeEspecie, '%'))")
    List<ModelEspecie> findByNomeEspecieContainingIgnoreCase(@Param("nomeEspecie") String nomeEspecie);
}