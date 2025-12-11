package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelDoador;
import com.senai.amigopetter.model.ModelPet;
import com.senai.amigopetter.service.DoadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doadores")
public class DoadorController {
    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

    @GetMapping
    public ResponseEntity<List<ModelDoador>> listarTodos() {
        List<ModelDoador> doadores = doadorService.listarTodos();
        return ResponseEntity.ok(doadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDoador> buscarPorId(@PathVariable Integer id) {
        return doadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelDoador doador) {
        try {
            ModelDoador doadorSalvo = doadorService.salvar(doador);
            return ResponseEntity.status(HttpStatus.CREATED).body(doadorSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao criar doador", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ModelDoador doador) {
        try {
            ModelDoador doadorAtualizado = doadorService.atualizar(id, doador);
            return ResponseEntity.ok(doadorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao atualizar doador", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            doadorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pets") // NOVO do documento
    public ResponseEntity<List<ModelPet>> listarPetsDoDoador(@PathVariable Integer id) {
        List<ModelPet> pets = doadorService.listarPetsDoDoador(id);
        return ResponseEntity.ok(pets);
    }
}