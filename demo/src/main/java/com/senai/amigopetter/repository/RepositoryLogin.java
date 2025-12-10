package com.senai.amigopetter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senai.amigopetter.model.ModelLogin;

@Repository
public interface RepositoryLogin extends JpaRepository<ModelLogin, Integer> {
    java.util.Optional<ModelLogin> findByEmail(String email);
    Optional<ModelLogin> findByUsuario(String usuario);
    boolean existsByEmail(String email);
    boolean existsByUsuario(String usuario);
    Optional<ModelLogin> findByEmailAndSenha(String email, String senha);
}
