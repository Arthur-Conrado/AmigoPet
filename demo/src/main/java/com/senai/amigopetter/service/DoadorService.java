package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelDoador;
import com.senai.amigopetter.model.ModelPet;
import java.util.List;
import java.util.Optional;

public class DoadorService {
    List<ModelDoador> listarTodos();
    Optional<ModelDoador> buscarPorId(Integer id);
    ModelDoador salvar(ModelDoador doador);
    ModelDoador atualizar(Integer id, ModelDoador doador);
    void deletar(Integer id);

    Optional<ModelDoador> buscarPorCpf(String cpf);
    Optional<ModelDoador> buscarPorEmail(String email);
    List<ModelDoador> buscarPorNome(String nome);
    Optional<ModelDoador> buscarPorLoginId(Integer loginId);

    boolean cpfExiste(String cpf);
    boolean emailExiste(String email);
    boolean loginJaVinculado(Integer loginId);

    List<ModelPet> listarPetsDoDoador(Integer doadorId);
    Long contarPetsDoDoador(Integer doadorId);
    Long contarAdocoesDoDoador(Integer doadorId);


}
