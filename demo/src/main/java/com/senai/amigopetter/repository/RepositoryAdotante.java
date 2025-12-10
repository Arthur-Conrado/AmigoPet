package com.senai.amigopetter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelAdotante;

@Repository
public interface RepositoryAdotante extends JpaRepository<ModelAdotante, Integer> {
    Optional<ModelAdotante> findByEmail(String email);
    List <ModelAdotante> findByNomeContaingIgonrecase(String nome);
    bolean existByEmail(String email);
    Optional<ModelAdotante> findByTelefone(String telefone);

}
