package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.ModelDoador;
import com.senai.amigopetter.model.ModelPet;
import com.senai.amigopetter.repository.RepositoryDoador;
import com.senai.amigopetter.repository.RepositoryPet;
import com.senai.amigopetter.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoadorServiceImpl implements DoadorService {
    
    @Autowired
    private RepositoryDoador repositoryDoador;
    
    @Autowired
    private RepositoryPet repositoryPet;
    
    @Override
    public List<ModelDoador> listarTodos() {
        return repositoryDoador.findAll();
    }
    
    @Override
    public Optional<ModelDoador> buscarPorId(Integer id) {
        return repositoryDoador.findById(id);
    }
    
    @Override
    public ModelDoador salvar(ModelDoador doador) {
      
        if (doador.getCpf() != null && cpfExiste(doador.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (doador.getEmail() != null && emailExiste(doador.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
    
        if (doador.getDt_registro() == null) {
            doador.setDt_registro(LocalDateTime.now());
        }
        if (doador.getDt_update() == null) {
            doador.setDt_update(LocalDateTime.now());
        }
        
        return repositoryDoador.save(doador);
    }
    
    @Override
    public ModelDoador atualizar(Integer id, ModelDoador doador) {
        ModelDoador existente = repositoryDoador.findById(id)
            .orElseThrow(() -> new RuntimeException("Doador não encontrado"));
        
      
        if (doador.getCpf() != null && 
            !doador.getCpf().equals(existente.getCpf()) && 
            cpfExiste(doador.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (doador.getEmail() != null && 
            !doador.getEmail().equals(existente.getEmail()) && 
            emailExiste(doador.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
  
        existente.setNome(doador.getNome());
        existente.setCpf(doador.getCpf());
        existente.setTelefone(doador.getTelefone());
        existente.setEmail(doador.getEmail());
        existente.setDt_nascimento(doador.getDt_nascimento());
        existente.setDt_update(LocalDateTime.now());
        
       
        if (doador.getLogin() != null) {
            existente.setLogin(doador.getLogin());
        }
        
        return repositoryDoador.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryDoador.existsById(id)) {
            throw new RuntimeException("Doador não encontrado");
        }
        repositoryDoador.deleteById(id);
    }
    
    @Override
    public Optional<ModelDoador> buscarPorCpf(String cpf) {
        return repositoryDoador.findByCpf(cpf);
    }
    
    @Override
    public Optional<ModelDoador> buscarPorEmail(String email) {
        return repositoryDoador.findByEmail(email);
    }
    
    @Override
    public List<ModelDoador> buscarPorNome(String nome) {
        return repositoryDoador.findByNomeContainingIgnoreCase(nome);
    }
    
    @Override
    public Optional<ModelDoador> buscarPorLoginId(Integer loginId) {
        return repositoryDoador.findByLoginId(loginId);
    }
    
    @Override
    public boolean cpfExiste(String cpf) {
        return repositoryDoador.existsByCpf(cpf);
    }
    
    @Override
    public boolean emailExiste(String email) {
        return repositoryDoador.existsByEmail(email);
    }
    
    @Override
    public boolean loginJaVinculado(Integer loginId) {
        return repositoryDoador.existsByLoginId(loginId);
    }
    
    @Override
    public List<ModelPet> listarPetsDoDoador(Integer doadorId) {
   
        return List.of();
    }
    
    @Override
    public Long contarPetsDoDoador(Integer doadorId) {
        return (long) listarPetsDoDoador(doadorId).size();
    }
    
    @Override
    public Long contarAdocoesDoDoador(Integer doadorId) {
       
        return 0L;
    }
}