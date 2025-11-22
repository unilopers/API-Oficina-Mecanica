package com.unifil.oficinaMecanica.service.interfaces;

import com.unifil.oficinaMecanica.dto.request.VeiculoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;

import java.util.List;

public interface VeiculoService {
     boolean cadastrarNovoVeiculo(VeiculoRequestDTO veiculo) throws Exception;
     List<VeiculoResponseDTO> listarVeiculos();
     VeiculoResponseDTO buscarVeiculoPelaPlaca(String placa);
     boolean atualizarInformacoes(String placa, VeiculoRequestDTO veiculo) throws Exception;
     boolean removerVeiculoPelaPlaca(String placa) throws Exception;
}
