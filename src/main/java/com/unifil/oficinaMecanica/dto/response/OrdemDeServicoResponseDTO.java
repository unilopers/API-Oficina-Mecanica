package com.unifil.oficinaMecanica.dto.response;

import com.unifil.oficinaMecanica.entity.OrdemDeServicoEntity;

import java.math.BigDecimal;

public record OrdemDeServicoResponseDTO(Long id,
                                        String status,
                                        String veiculoPlaca,
                                        String veiculoMarca,
                                        String servicoDescricao,
                                        BigDecimal servicoValor) {
}