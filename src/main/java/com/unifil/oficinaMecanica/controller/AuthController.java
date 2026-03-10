package com.unifil.oficinaMecanica.controller;

import com.unifil.oficinaMecanica.dto.request.LoginRequestDTO;
import com.unifil.oficinaMecanica.dto.request.RegisterRequestDTO;
import com.unifil.oficinaMecanica.dto.response.LoginResponseDTO;
import com.unifil.oficinaMecanica.dto.response.UserProfileResponseDTO;
import com.unifil.oficinaMecanica.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Registra um novo usuário", description = "Cria uma nova conta de usuário com nome, email e senha encriptados")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegisterRequestDTO dto) {
        try {
            authService.registrarUsuario(dto);
            return new ResponseEntity<>("Usuário registrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Faz login do usuário", description = "Valida as credenciais e retorna um token JWT")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {
        try {
            LoginResponseDTO response = authService.fazerLogin(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/profile")
    @Operation(summary = "Obtém o perfil do usuário autenticado", description = "Requer um token JWT válido no header Authorization")
    public ResponseEntity<?> obterPerfil() {
        try {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal == null || principal.equals("anonymousUser")) {
                return new ResponseEntity<>("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
            }

            String email = principal.toString();
            UserProfileResponseDTO profile = authService.obterPerfil(email);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}


