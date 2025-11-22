package com.unifil.oficinaMecanica.controller;

import com.unifil.oficinaMecanica.dto.request.OrdemDeServicoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.OrdemDeServicoResponseDTO;
import com.unifil.oficinaMecanica.service.interfaces.OrdemDeServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
public class OrdemDeServicoController {

    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    @PostMapping
    public ResponseEntity<?> abrirNovaOS(@RequestBody @Valid OrdemDeServicoRequestDTO dto) {
        try {
            ordemDeServicoService.cadastrarNovaOrdemDeServico(dto);
            return new ResponseEntity<>("Ordem de Serviço aberta com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemDeServicoResponseDTO>> listarOSEmAberto() {
        return ResponseEntity.ok(ordemDeServicoService.getOrdemDeServicosEmAberto());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus) {
        try {
            ordemDeServicoService.atualizarStatusDaOrdemDeServico(id, novoStatus);
            return ResponseEntity.ok("Status atualizado com sucesso.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarOS(@PathVariable Long id) {
        try {
            ordemDeServicoService.finalizarOrdemDeServico(id);
            return ResponseEntity.ok("Ordem de Serviço finalizada com sucesso.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerOS(@PathVariable Long id) {
        try {
            ordemDeServicoService.removerOrdemDeServico(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}