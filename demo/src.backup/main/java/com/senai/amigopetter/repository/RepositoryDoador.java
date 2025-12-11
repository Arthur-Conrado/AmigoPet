package com.senai.amigopetter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelDoador;

@Repository
public interface RepositoryDoador extends JpaRepository<ModelDoador, Integer> {
    Optional<ModelDoador> findByCpf(String cpf);
    Optional<ModelDoador> findByEmail(String email);
    List<ModelDoador> findByNomeContainingIgnoreCase(String nome);
    Optional<ModelDoador> findByLoginId(Integer loginId);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByLoginId(Integer loginId);
}