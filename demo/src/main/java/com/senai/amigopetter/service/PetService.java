package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelPet;
import java.util.List;
import java.util.Optional;

public class PetService {
    List<ModelPet> listarTodos();
    Optional<ModelPet> buscarPorId(Integer id);
    ModelPet salvar(ModelPet pet);
    ModelPet atualizar(Integer id, ModelPet pet);
    void deletar(Integer id);

    List<ModelPet> listarDisponiveis();
    List<ModelPet> listarPorEspecie(Integer especieId);

    List<ModelPet> buscarPorNome(String nome);
    List<ModelPet> buscarPorRaca(String raca);
    List<ModelPet> buscarPorCastrado(Boolean castrado);
    List<ModelPet> buscarPorIdadeEntre(Integer idadeMin, Integer idadeMax);

    ModelPet marcarComoAdotado(Integer petId);
    ModelPet atualizarDisponibilidade(Integer petId, DisponibilidadePet disponibilidade);
    Long contarPetsDisponiveis();
    Long contarPetsPorEspecie(Integer especieId);
    
    boolean petExiste(Integer id);
    boolean podeSerDeletado(Integer petId);
}
