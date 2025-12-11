package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelLogin;
import java.util.Optional;

public interface LoginService {
    ModelLogin autenticar(String email, String senha);

    ModelLogin salvar(ModelLogin login);
    ModelLogin atualizar(Integer id, ModelLogin login);
    void deletar(Integer id);
    Optional<ModelLogin> buscarPorId(Integer id);

    Optional<ModelLogin> buscarPorEmail(String email);
    Optional<ModelLogin> buscarPorUsuario(String usuario);

    boolean emailExiste(String email);
    boolean usuarioExiste(String usuario);
}
