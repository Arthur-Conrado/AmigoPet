package com.senai.amigopetter.repository;

import java.util.List;
import com.senai.amigopetter.model.StatusAdocao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelAdocao;

@Repository
public interface RepositoryAdocao extends JpaRepository<ModelAdocao, Integer> {
    List<ModelAdocao> findByAdotanteId(Integer adotanteId);
    List<ModelAdocao> findByDoadorId(Integer doadorId);
    List<ModelAdocao> findByPetId(Integer petId);
    List<ModelAdocao> findByStatusAdocao(StatusAdocao statusAdocao);
    Long countByStatusAdocao(StatusAdocao statusAdocao);

}
