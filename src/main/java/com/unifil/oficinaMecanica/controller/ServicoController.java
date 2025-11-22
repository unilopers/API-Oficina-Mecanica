package com.unifil.oficinaMecanica.controller;

import com.unifil.oficinaMecanica.dto.request.ServicoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.ServicoResponseDTO;
import com.unifil.oficinaMecanica.service.interfaces.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<?> cadastrarServico(@RequestBody @Valid ServicoRequestDTO dto) {
        try {
            servicoService.cadastrarNovoServico(dto);
            return new ResponseEntity<>("Serviço criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listarServicos() {
        return ResponseEntity.ok(servicoService.getServicos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerServico(@PathVariable Long id) {
        try {
            servicoService.removerServico(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}