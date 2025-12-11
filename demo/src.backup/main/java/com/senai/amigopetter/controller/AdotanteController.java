package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelAdotante;
import com.senai.amigopetter.service.AdotanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adotantes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdotanteController {
    private final AdotanteService adotanteService;

    public AdotanteController(AdotanteService adotanteService) {
        this.adotanteService = adotanteService;
    }

    @GetMapping
    public ResponseEntity<List<ModelAdotante>> listarTodos() {
        List<ModelAdotante> adotantes = adotanteService.listarTodos();
        return ResponseEntity.ok(adotantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelAdotante> buscarPorId(@PathVariable Integer id) {
        return adotanteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelAdotante adotante) {
        try {
            ModelAdotante adotanteSalvo = adotanteService.salvar(adotante);
            return ResponseEntity.status(HttpStatus.CREATED).body(adotanteSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao criar adotante", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ModelAdotante adotante) {
        try {
            ModelAdotante adotanteAtualizado = adotanteService.atualizar(id, adotante);
            return ResponseEntity.ok(adotanteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao atualizar adotante", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            adotanteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}