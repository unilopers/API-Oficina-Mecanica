package com.unifil.oficinaMecanica.service.interfaces;

import com.unifil.oficinaMecanica.dto.request.OrdemDeServicoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.OrdemDeServicoResponseDTO;

import java.util.List;

public interface OrdemDeServicoService {
    boolean cadastrarNovaOrdemDeServico(OrdemDeServicoRequestDTO ordemDeServico) throws Exception;
    List<OrdemDeServicoResponseDTO> getOrdemDeServicosEmAberto();
    boolean atualizarStatusDaOrdemDeServico(Long id, String novoStatus) throws Exception;
    boolean finalizarOrdemDeServico(Long id) throws Exception;
    boolean removerOrdemDeServico(Long id) throws Exception;
}
