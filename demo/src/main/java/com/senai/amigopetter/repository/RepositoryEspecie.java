package com.senai.amigopetter.repository;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelEspecie;

@Repository
public interface RepositoryEspecie extends JpaRepository<ModelEspecie, Integer> {
    Optinal <ModelEspecie> findByNomeEspecieIgnoreCase(String nomeEspecie);
    boolean existByNomeEspecieIgnoreCase(String nomeEspecie);
    List<ModelEspecie> findByNomeEspecieContainingIgnoreCase(String nomeEspecie);

}
