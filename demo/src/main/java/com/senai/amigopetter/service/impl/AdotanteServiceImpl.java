package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.ModelAdotante;
import com.senai.amigopetter.repository.RepositoryAdotante;
import com.senai.amigopetter.service.AdotanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdotanteServiceImpl implements AdotanteService {
    
    @Autowired
    private RepositoryAdotante repositoryAdotante;
    
    @Override
    public List<ModelAdotante> listarTodos() {
        return repositoryAdotante.findAll();
    }
    
    @Override
    public Optional<ModelAdotante> buscarPorId(Integer id) {
        return repositoryAdotante.findById(id);
    }
    
    @Override
    public ModelAdotante salvar(ModelAdotante adotante) {
      
        if (adotante.getEmail() != null && emailExiste(adotante.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        return repositoryAdotante.save(adotante);
    }
    
    @Override
    public ModelAdotante atualizar(Integer id, ModelAdotante adotante) {
        ModelAdotante existente = repositoryAdotante.findById(id)
            .orElseThrow(() -> new RuntimeException("Adotante não encontrado"));
        

        if (adotante.getEmail() != null && 
            !adotante.getEmail().equals(existente.getEmail()) && 
            emailExiste(adotante.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        existente.setNome(adotante.getNome());
        existente.setTelefone(adotante.getTelefone());
        existente.setEmail(adotante.getEmail());
        
        return repositoryAdotante.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryAdotante.existsById(id)) {
            throw new RuntimeException("Adotante não encontrado");
        }
        repositoryAdotante.deleteById(id);
    }
    
    @Override
    public Optional<ModelAdotante> buscarPorEmail(String email) {
        return repositoryAdotante.findByEmail(email);
    }
    
    @Override
    public List<ModelAdotante> buscarPorNome(String nome) {
        return repositoryAdotante.findByNomeContainingIgnoreCase(nome);
    }
    
    @Override
    public Optional<ModelAdotante> buscarPorTelefone(String telefone) {
        return repositoryAdotante.findByTelefone(telefone);
    }
    
    @Override
    public boolean emailExiste(String email) {
        return repositoryAdotante.existsByEmail(email);
    }
    
    @Override
    public boolean telefoneExiste(String telefone) {
        return repositoryAdotante.findByTelefone(telefone).isPresent();
    }
    
    @Override
    public boolean podeAdotar(Integer adotanteId) {
       
        return true;
    }
    
    @Override
    public Long contarAdocoesDoAdotante(Integer adotanteId) {
       
        return 0L;
    }
}