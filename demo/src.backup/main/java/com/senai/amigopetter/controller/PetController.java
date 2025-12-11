package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelPet;
import com.senai.amigopetter.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }
    
    @GetMapping
    public ResponseEntity<List<ModelPet>> listarTodos() {
        List<ModelPet> pets = petService.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelPet> buscarPorId(@PathVariable Integer id) {
        return petService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelPet pet) {
        try {
            ModelPet petSalvo = petService.salvar(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Erro ao criar pet", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ModelPet pet) {
        try {
            ModelPet petAtualizado = petService.atualizar(id, pet);
            return ResponseEntity.ok(petAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Erro ao atualizar pet", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            petService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/disponiveis") 
    public ResponseEntity<List<ModelPet>> listarDisponiveis() {
        List<ModelPet> pets = petService.listarDisponiveis();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/especie/{idEspecie}") 
    public ResponseEntity<List<ModelPet>> listarPorEspecie(@PathVariable Integer idEspecie) {
        List<ModelPet> pets = petService.listarPorEspecie(idEspecie);
        return ResponseEntity.ok(pets);
    }


    @GetMapping("/filtro")
    public ResponseEntity<List<ModelPet>> filtrarPets(
            @RequestParam(required = false) String porte,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Boolean vacinado) {
      
        List<ModelPet> pets = petService.filtrarPets(porte, sexo, cor, vacinado);
        return ResponseEntity.ok(pets);
    }
}