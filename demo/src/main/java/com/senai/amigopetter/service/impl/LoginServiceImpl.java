package com.senai.amigopetter.service.impl;

import com.senai.amigopetter.model.ModelLogin;
import com.senai.amigopetter.repository.RepositoryLogin;
import com.senai.amigopetter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private RepositoryLogin repositoryLogin;
    
    @Override
    public ModelLogin autenticar(String email, String senha) {
        return repositoryLogin.findByEmailAndSenha(email, senha)
            .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
    
    @Override
    public ModelLogin salvar(ModelLogin login) {
        
        if (login.getEmail() != null && emailExiste(login.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (login.getUsuario() != null && usuarioExiste(login.getUsuario())) {
            throw new RuntimeException("Usuário já cadastrado");
        }
        
        return repositoryLogin.save(login);
    }
    
    @Override
    public ModelLogin atualizar(Integer id, ModelLogin login) {
        ModelLogin existente = repositoryLogin.findById(id)
            .orElseThrow(() -> new RuntimeException("Login não encontrado"));
        
     
        if (login.getEmail() != null && 
            !login.getEmail().equals(existente.getEmail()) && 
            emailExiste(login.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (login.getUsuario() != null && 
            !login.getUsuario().equals(existente.getUsuario()) && 
            usuarioExiste(login.getUsuario())) {
            throw new RuntimeException("Usuário já cadastrado");
        }
        
        existente.setUsuario(login.getUsuario());
        existente.setEmail(login.getEmail());
        existente.setTelefone(login.getTelefone());
        existente.setSenha(login.getSenha());
        
        return repositoryLogin.save(existente);
    }
    
    @Override
    public void deletar(Integer id) {
        if (!repositoryLogin.existsById(id)) {
            throw new RuntimeException("Login não encontrado");
        }
        repositoryLogin.deleteById(id);
    }
    
    @Override
    public Optional<ModelLogin> buscarPorId(Integer id) {
        return repositoryLogin.findById(id);
    }
    
    @Override
    public Optional<ModelLogin> buscarPorEmail(String email) {
        return repositoryLogin.findByEmail(email);
    }
    
    @Override
    public Optional<ModelLogin> buscarPorUsuario(String usuario) {
        return repositoryLogin.findByUsuario(usuario);
    }
    
    @Override
    public boolean emailExiste(String email) {
        return repositoryLogin.existsByEmail(email);
    }
    
    @Override
    public boolean usuarioExiste(String usuario) {
        return repositoryLogin.existsByUsuario(usuario);
    }
}