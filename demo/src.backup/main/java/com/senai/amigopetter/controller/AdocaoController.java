package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelAdocao;
import com.senai.amigopetter.model.StatusAdocao;
import com.senai.amigopetter.service.AdocaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adocoes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdocaoController {
    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @GetMapping
    public ResponseEntity<List<ModelAdocao>> listarTodas() {
        List<ModelAdocao> adocoes = adocaoService.listarTodas();
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelAdocao> buscarPorId(@PathVariable Integer id) {
        return adocaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelAdocao adocao) {
        try {
            ModelAdocao adocaoSalva = adocaoService.salvar(adocao);
            return ResponseEntity.status(HttpStatus.CREATED).body(adocaoSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao criar adoção", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ModelAdocao adocao) {
        try {
            ModelAdocao adocaoAtualizada = adocaoService.atualizar(id, adocao);
            return ResponseEntity.ok(adocaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao atualizar adoção", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            adocaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/adotante/{idAdotante}")
    public ResponseEntity<List<ModelAdocao>> listarPorAdotante(@PathVariable Integer idAdotante) {
        List<ModelAdocao> adocoes = adocaoService.listarPorAdotante(idAdotante);
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/doador/{idDoador}")
    public ResponseEntity<List<ModelAdocao>> listarPorDoador(@PathVariable Integer idDoador) {
        List<ModelAdocao> adocoes = adocaoService.listarPorDoador(idDoador);
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/pet/{idPet}")
    public ResponseEntity<List<ModelAdocao>> listarPorPet(@PathVariable Integer idPet) {
        List<ModelAdocao> adocoes = adocaoService.listarPorPet(idPet);
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ModelAdocao>> listarPorStatus(@PathVariable String status) {
        StatusAdocao statusEnum;
        try {
            statusEnum = StatusAdocao.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        List<ModelAdocao> adocoes = adocaoService.listarPorStatus(statusEnum);
        return ResponseEntity.ok(adocoes);
    }
}