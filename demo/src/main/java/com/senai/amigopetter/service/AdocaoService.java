package com.senai.amigopetter.service;
import com.senai.amigopetter.model.ModelAdocao;
import com.senai.amigopetter.model.StatusAdocao;

import java.util.List;
import java.util.Optional;

public class AdocaoService {
    List<ModelAdocao> listarTodas();
    Optional<ModelAdocao> buscarPorId(Integer id);
    ModelAdocao salvar(ModelAdocao adocao);
    ModelAdocao atualizar(Integer id, ModelAdocao adocao);
    void deletar(Integer id);

    List<ModelAdocao> listarPorAdotante(Integer adotanteId);
    List<ModelAdocao> listarPorDoador(Integer doadorId);
    List<ModelAdocao> listarPorPet(Integer petId);
    List<ModelAdocao> listarPorStatus(StatusAdocao status);

     ModelAdocao solicitarAdocao(Integer adotanteId, Integer petId);
    ModelAdocao aprovarAdocao(Integer adocaoId, String motivoDoador);
    ModelAdocao recusarAdocao(Integer adocaoId, String motivoDoador);
    ModelAdocao cancelarAdocao(Integer adocaoId, String motivoAdotante);

    boolean petDisponivelParaAdocao(Integer petId);
    boolean adotantePodeAdotar(Integer adotanteId);
    Long contarAdocoesPorStatus(StatusAdocao status);
}
