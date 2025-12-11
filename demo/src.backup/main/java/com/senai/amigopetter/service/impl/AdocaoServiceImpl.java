package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.*;
import com.senai.amigopetter.repository.RepositoryAdocao;
import com.senai.amigopetter.repository.RepositoryPet;
import com.senai.amigopetter.service.AdocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdocaoServiceImpl implements AdocaoService {
    
    @Autowired
    private RepositoryAdocao repositoryAdocao;
    
    @Autowired
    private RepositoryPet repositoryPet;
    
    @Override
    public List<ModelAdocao> listarTodas() {
        return repositoryAdocao.findAll();
    }
    
    @Override
    public Optional<ModelAdocao> buscarPorId(Integer id) {
        return repositoryAdocao.findById(id);
    }
    
    @Override
    public ModelAdocao salvar(ModelAdocao adocao) {
     
        if (adocao.getAdotante() == null) {
            throw new RuntimeException("Adotante é obrigatório");
        }
        if (adocao.getDoador() == null) {
            throw new RuntimeException("Doador é obrigatório");
        }
        if (adocao.getPet() == null) {
            throw new RuntimeException("Pet é obrigatório");
        }
        
        
        if (adocao.getDtAdocao() == null) {
            adocao.setDtAdocao(LocalDateTime.now());
        }
        
        return repositoryAdocao.save(adocao);
    }
    
    @Override
    public ModelAdocao atualizar(Integer id, ModelAdocao adocao) {
        ModelAdocao existente = repositoryAdocao.findById(id)
            .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        
        StatusAdocao status = adocao.getStatusAdocao();
        String motivoAdotante = adocao.getMotivoAdotante();
        String motivoDoador = adocao.getMotivoDoador();
        
        existente.setStatusAdocao(status);
        existente.setMotivoAdotante(motivoAdotante);
        existente.setMotivoDoador(motivoDoador);
        existente.setObservacoes(adocao.getObservacoes());
        
        return repositoryAdocao.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryAdocao.existsById(id)) {
            throw new RuntimeException("Adoção não encontrada");
        }
        repositoryAdocao.deleteById(id);
    }
    
    @Override
    public List<ModelAdocao> listarPorAdotante(Integer adotanteId) {
        return repositoryAdocao.findByAdotanteId(adotanteId);
    }
    
    @Override
    public List<ModelAdocao> listarPorDoador(Integer doadorId) {
        return repositoryAdocao.findByDoadorId(doadorId);
    }
    
    @Override
    public List<ModelAdocao> listarPorPet(Integer petId) {
        return repositoryAdocao.findByPetId(petId);
    }
    
    @Override
    public List<ModelAdocao> listarPorStatus(StatusAdocao status) {
        return repositoryAdocao.findByStatusAdocao(status);
    }
    
    @Override
    public ModelAdocao solicitarAdocao(Integer adotanteId, Integer petId) {
        
        if (!petDisponivelParaAdocao(petId)) {
            throw new RuntimeException("Pet não está disponível para adoção");
        }
        
       
        ModelAdocao adocao = new ModelAdocao();
        
       
        ModelAdotante adotante = new ModelAdotante();
        adotante.setId(adotanteId);
        
        ModelPet pet = new ModelPet();
        pet.setId(petId);
        
       
        ModelDoador doador = new ModelDoador();
       
        
        adocao.setAdotante(adotante);
        adocao.setPet(pet);
        adocao.setDoador(doador);
        adocao.setDtAdocao(LocalDateTime.now());
        adocao.setStatusAdocao(StatusAdocao.PENDENTE);
        
        return repositoryAdocao.save(adocao);
    }
    
    @Override
    public ModelAdocao aprovarAdocao(Integer adocaoId, String motivoDoador) {
        ModelAdocao adocao = repositoryAdocao.findById(adocaoId)
            .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        
        adocao.setStatusAdocao(StatusAdocao.APROVADA);
        adocao.setMotivoDoador(motivoDoador);
        
      
        ModelPet pet = adocao.getPet();
        pet.setDisponibilidade(DisponibilidadePet.ADOTADO);
      
        
        return repositoryAdocao.save(adocao);
    }
    
    @Override
    public ModelAdocao recusarAdocao(Integer adocaoId, String motivoDoador) {
        ModelAdocao adocao = repositoryAdocao.findById(adocaoId)
            .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        
        adocao.setStatusAdocao(StatusAdocao.RECUSADA);
        adocao.setMotivoDoador(motivoDoador);
        
        return repositoryAdocao.save(adocao);
    }
    
    @Override
    public ModelAdocao cancelarAdocao(Integer adocaoId, String motivoAdotante) {
        ModelAdocao adocao = repositoryAdocao.findById(adocaoId)
            .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
        
        adocao.setStatusAdocao(StatusAdocao.CANCELADA);
        adocao.setMotivoAdotante(motivoAdotante);
        
      
        ModelPet pet = adocao.getPet();
        pet.setDisponibilidade(DisponibilidadePet.DISPONÍVEL);
       
        
        return repositoryAdocao.save(adocao);
    }
    
    @Override
    public boolean petDisponivelParaAdocao(Integer petId) {
        Optional<ModelPet> petOpt = repositoryPet.findById(petId);
        if (petOpt.isEmpty()) {
            return false;
        }
        
        ModelPet pet = petOpt.get();
        return pet.getDisponibilidade() == DisponibilidadePet.DISPONÍVEL;
    }
    
    @Override
    public boolean adotantePodeAdotar(Integer adotanteId) {
        return true;
    }
    
    @Override
    public Long contarAdocoesPorStatus(StatusAdocao status) {
        return repositoryAdocao.countByStatusAdocao(status);
    }
}