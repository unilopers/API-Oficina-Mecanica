package com.unifil.oficinaMecanica.controller;

import com.unifil.oficinaMecanica.dto.request.VeiculoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;
import com.unifil.oficinaMecanica.service.interfaces.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<?> cadastrarVeiculo(@RequestBody @Valid VeiculoRequestDTO dto) {
        try {
            veiculoService.cadastrarNovoVeiculo(dto);
            return new ResponseEntity<>("Veículo cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarVeiculos() {
        List<VeiculoResponseDTO> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscarVeiculoPorPlaca(@PathVariable String placa) {
        VeiculoResponseDTO veiculo = veiculoService.buscarVeiculoPelaPlaca(placa);

        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return new ResponseEntity<>("Veículo não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{placa}")
    public ResponseEntity<?> atualizarVeiculo(@PathVariable String placa, @RequestBody @Valid VeiculoRequestDTO dto) {
        try {
            veiculoService.atualizarInformacoes(placa, dto);
            return ResponseEntity.ok("Veículo atualizado com sucesso.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<?> removerVeiculo(@PathVariable String placa) {
        try {
            veiculoService.removerVeiculoPelaPlaca(placa);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}