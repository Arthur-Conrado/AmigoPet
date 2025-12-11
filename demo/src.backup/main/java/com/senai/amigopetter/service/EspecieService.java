package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelEspecie;
import java.util.List;
import java.util.Optional;

public interface EspecieService {

    List<ModelEspecie> listarTodas();
    Optional<ModelEspecie> buscarPorId(Integer id);
    ModelEspecie salvar(ModelEspecie especie);
    ModelEspecie atualizar(Integer id, ModelEspecie especie);
    void deletar(Integer id);

    Optional<ModelEspecie> buscarPorNome(String nomeEspecie);
    List<ModelEspecie> buscarPorNomeContendo(String nomeEspecie);

    boolean nomeExiste(String nomeEspecie);
    boolean podeSerDeletada(Integer especieId);
    Long contarPetsDaEspecie(Integer especieId);
}
