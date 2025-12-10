package com.senai.amigopetter.controller;

import com.senai.amigopetter.model.ModelLogin;
import com.senai.amigopetter.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth") 
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login") 
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            String email = credenciais.get("email");
            String senha = credenciais.get("senha");
            
            
            ModelLogin login = loginService.buscarPorEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            if (!login.getSenha().equals(senha)) {
                throw new RuntimeException("Senha incorreta");
            }
            
           
            return ResponseEntity.ok(login);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas", "message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ModelLogin login) {
        try {
            ModelLogin loginSalvo = loginService.salvar(login);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Erro ao criar login", "message", e.getMessage())
            );
        }
    }
}