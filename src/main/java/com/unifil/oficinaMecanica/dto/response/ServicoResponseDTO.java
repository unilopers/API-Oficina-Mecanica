package com.unifil.oficinaMecanica.dto.response;

import java.math.BigDecimal;

public record ServicoResponseDTO(Long id,
                                 String descricao,
                                 BigDecimal valor,
                                 int duracaoEstimadaEmSegundos) {
}
