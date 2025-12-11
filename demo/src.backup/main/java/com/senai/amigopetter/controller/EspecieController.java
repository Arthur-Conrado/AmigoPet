package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelEspecie;
import com.senai.amigopetter.service.EspecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/especies")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EspecieController {
    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public ResponseEntity<List<ModelEspecie>> listarTodas() {
        List<ModelEspecie> especies = especieService.listarTodas();
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelEspecie> buscarPorId(@PathVariable Integer id) {
        return especieService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelEspecie especie) {
        try {
            ModelEspecie especieSalva = especieService.salvar(especie);
            return ResponseEntity.status(HttpStatus.CREATED).body(especieSalva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao criar espécie", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ModelEspecie especie) {
        try {
            ModelEspecie especieAtualizada = especieService.atualizar(id, especie);
            return ResponseEntity.ok(especieAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao atualizar espécie", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            especieService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}