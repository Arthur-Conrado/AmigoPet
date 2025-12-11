package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelAdotante;
import java.util.List;
import java.util.Optional;

public interface AdotanteService {

    List<ModelAdotante> listarTodos();
    Optional<ModelAdotante> buscarPorId(Integer id);
    ModelAdotante salvar(ModelAdotante adotante);
    ModelAdotante atualizar(Integer id, ModelAdotante adotante);
    void deletar(Integer id);

    Optional<ModelAdotante> buscarPorEmail(String email);
    List<ModelAdotante> buscarPorNome(String nome);
    Optional<ModelAdotante> buscarPorTelefone(String telefone);

    boolean emailExiste(String email);
    boolean telefoneExiste(String telefone);
    boolean podeAdotar(Integer adotanteId);
    Long contarAdocoesDoAdotante(Integer adotanteId);

}
