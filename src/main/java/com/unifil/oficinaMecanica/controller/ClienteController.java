package com.unifil.oficinaMecanica.controller;

import com.unifil.oficinaMecanica.dto.request.ClienteRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;
import com.unifil.oficinaMecanica.service.interfaces.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO dto) {
        try {
            clienteService.cadastrarNovoCliente(dto);
            return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> atualizarCliente(@PathVariable String cpf, @RequestBody @Valid ClienteRequestDTO dto) {
        try {
            if (!cpf.equals(dto.cpf())) {
                return new ResponseEntity<>("O CPF da URL difere do CPF do corpo da requisição.", HttpStatus.BAD_REQUEST);
            }
            clienteService.atualizarInformacoes(dto);
            return ResponseEntity.ok("Cliente atualizado com sucesso.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> removerCliente(@PathVariable String cpf) {
        try {
            clienteService.removerCliente(cpf);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}/veiculos")
    public ResponseEntity<List<VeiculoResponseDTO>> listarVeiculosDoCliente(@PathVariable String cpf) {
        List<VeiculoResponseDTO> veiculos = clienteService.getVeiculos(cpf);
        return ResponseEntity.ok(veiculos);
    }
}