package com.unifil.oficinaMecanica.service.interfaces;

import com.unifil.oficinaMecanica.dto.request.ClienteRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;

import java.util.List;

public interface ClienteService {
    boolean cadastrarNovoCliente(ClienteRequestDTO cliente) throws Exception;
    boolean atualizarInformacoes(ClienteRequestDTO cliente) throws Exception;
    boolean removerCliente(String cpf) throws Exception;
    List<VeiculoResponseDTO> getVeiculos(String cpf);
}
