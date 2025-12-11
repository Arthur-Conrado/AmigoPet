package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.ModelEspecie;
import com.senai.amigopetter.repository.RepositoryEspecie;
import com.senai.amigopetter.repository.RepositoryPet;
import com.senai.amigopetter.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieServiceImpl implements EspecieService {
    
    @Autowired
    private RepositoryEspecie repositoryEspecie;
    
    @Autowired
    private RepositoryPet repositoryPet;
    
    @Override
    public List<ModelEspecie> listarTodas() {
        return repositoryEspecie.findAll();
    }
    
    @Override
    public Optional<ModelEspecie> buscarPorId(Integer id) {
        return repositoryEspecie.findById(id);
    }
    
    @Override
    public ModelEspecie salvar(ModelEspecie especie) {
      
        if (especie.getNome_especie() != null && nomeExiste(especie.getNome_especie())) {
            throw new RuntimeException("Espécie com este nome já existe");
        }
        
        return repositoryEspecie.save(especie);
    }
    
    @Override
    public ModelEspecie atualizar(Integer id, ModelEspecie especie) {
        ModelEspecie existente = repositoryEspecie.findById(id)
            .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));
        
       
        if (especie.getNome_especie() != null && 
            !especie.getNome_especie().equalsIgnoreCase(existente.getNome_especie()) && 
            nomeExiste(especie.getNome_especie())) {
            throw new RuntimeException("Espécie com este nome já existe");
        }
        
        existente.setNome_especie(especie.getNome_especie());
        
        return repositoryEspecie.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryEspecie.existsById(id)) {
            throw new RuntimeException("Espécie não encontrada");
        }
        
        if (!podeSerDeletada(id)) {
            throw new RuntimeException("Não é possível deletar a espécie. Existem pets vinculados.");
        }
        
        repositoryEspecie.deleteById(id);
    }
    
    @Override
    public Optional<ModelEspecie> buscarPorNome(String nomeEspecie) {
        return repositoryEspecie.findByNomeEspecieIgnoreCase(nomeEspecie);
    }
    
    @Override
    public List<ModelEspecie> buscarPorNomeContendo(String nomeEspecie) {
        return repositoryEspecie.findByNomeEspecieContainingIgnoreCase(nomeEspecie);
    }
    
    @Override
    public boolean nomeExiste(String nomeEspecie) {
        return repositoryEspecie.existsByNomeEspecieIgnoreCase(nomeEspecie);
    }
    
    @Override
    public boolean podeSerDeletada(Integer especieId) {
       
        List<com.senai.amigopetter.model.ModelPet> pets = repositoryPet.findByEspecieId(especieId);
        return pets.isEmpty();
    }
    
    @Override
    public Long contarPetsDaEspecie(Integer especieId) {
        return (long) repositoryPet.findByEspecieId(especieId).size();
    }
}