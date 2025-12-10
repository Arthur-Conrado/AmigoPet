package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.*;
import com.senai.amigopetter.repository.RepositoryPet;
import com.senai.amigopetter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    
    @Autowired
    private RepositoryPet repositoryPet;
    
    @Override
    public List<ModelPet> listarTodos() {
        return repositoryPet.findAll();
    }
    
    @Override
    public Optional<ModelPet> buscarPorId(Integer id) {
        return repositoryPet.findById(id);
    }
    
    @Override
    public ModelPet salvar(ModelPet pet) {
        
        if (pet.getDt_registro() == null) {
            pet.setDt_registro(LocalDateTime.now());
        }
        
        return repositoryPet.save(pet);
    }
    
    @Override
    public ModelPet atualizar(Integer id, ModelPet pet) {
        ModelPet existente = repositoryPet.findById(id)
            .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        
      
        existente.setNome(pet.getNome());
        existente.setRaca(pet.getRaca());
        existente.setIdade(pet.getIdade());
        existente.setPorte(pet.getPorte());
        existente.setSexo(pet.getSexo());
        existente.setDisponibilidade(pet.getDisponibilidade());
        existente.setCor(pet.getCor());
        existente.setVacinado(pet.getVacinado());
        existente.setQtd_doses(pet.getQtd_doses());
        existente.setCastrado(pet.getCastrado());
        existente.setDescricao(pet.getDescricao());
        existente.setEspecie(pet.getEspecie());
        
        return repositoryPet.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryPet.existsById(id)) {
            throw new RuntimeException("Pet não encontrado");
        }
        
       
        if (!podeSerDeletado(id)) {
            throw new RuntimeException("Não é possível deletar o pet. Existe adoção vinculada.");
        }
        
        repositoryPet.deleteById(id);
    }
    
    @Override
    public List<ModelPet> listarDisponiveis() {
        return repositoryPet.findByDisponibilidade(DisponibilidadePet.DISPONÍVEL);
    }
    
    @Override
    public List<ModelPet> listarPorEspecie(Integer especieId) {
        return repositoryPet.findByEspecieId(especieId);
    }
    
    @Override
    public List<ModelPet> buscarPorNome(String nome) {
        return repositoryPet.findByNomeContainingIgnoreCase(nome);
    }
    
    @Override
    public List<ModelPet> buscarPorRaca(String raca) {
        return repositoryPet.findByRacaContainingIgnoreCase(raca);
    }
    
    @Override
    public List<ModelPet> buscarPorCastrado(Boolean castrado) {
        return repositoryPet.findByCastrado(castrado);
    }
    
    @Override
    public List<ModelPet> buscarPorIdadeEntre(Integer idadeMin, Integer idadeMax) {
        return repositoryPet.findByIdadeBetween(idadeMin, idadeMax);
    }
    
    @Override
    public ModelPet marcarComoAdotado(Integer petId) {
        ModelPet pet = repositoryPet.findById(petId)
            .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        
        pet.setDisponibilidade(DisponibilidadePet.ADOTADO);
        return repositoryPet.save(pet);
    }
    
    @Override
    public ModelPet atualizarDisponibilidade(Integer petId, DisponibilidadePet disponibilidade) {
        ModelPet pet = repositoryPet.findById(petId)
            .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        
        pet.setDisponibilidade(disponibilidade);
        return repositoryPet.save(pet);
    }
    
    @Override
    public Long contarPetsDisponiveis() {
        return repositoryPet.countByDisponibilidade(DisponibilidadePet.DISPONÍVEL);
    }
    
    @Override
    public Long contarPetsPorEspecie(Integer especieId) {
        return (long) repositoryPet.findByEspecieId(especieId).size();
    }
    
    @Override
    public boolean petExiste(Integer id) {
        return repositoryPet.existsById(id);
    }
    
    @Override
    public boolean podeSerDeletado(Integer petId) {
        
        return true;
    }
    
   
    public List<ModelPet> filtrarPets(String porte, String sexo, String cor, Boolean vacinado) {
        
        List<ModelPet> todosPets = repositoryPet.findAll();
        
        return todosPets.stream()
            .filter(pet -> porte == null || pet.getPorte().name().equalsIgnoreCase(porte))
            .filter(pet -> sexo == null || pet.getSexo().name().equalsIgnoreCase(sexo))
            .filter(pet -> cor == null || (pet.getCor() != null && pet.getCor().equalsIgnoreCase(cor)))
            .filter(pet -> vacinado == null || (pet.getVacinado() != null && pet.getVacinado().equals(vacinado)))
            .toList();
    }
}