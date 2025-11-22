package com.unifil.oficinaMecanica.service.interfaces;

import com.unifil.oficinaMecanica.dto.request.ServicoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.ServicoResponseDTO;

import java.util.List;

public interface ServicoService {
    boolean cadastrarNovoServico(ServicoRequestDTO servico) throws Exception;
    List<ServicoResponseDTO> getServicos();
    boolean removerServico(Long id) throws Exception;
}
