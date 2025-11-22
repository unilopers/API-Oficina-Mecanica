package com.unifil.oficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ServicoRequestDTO(@NotBlank(message = "A descrição do serviço é obrigatória.")
                                String descricao,

                                @NotNull(message = "O valor do serviço é obrigatório.")
                                @Positive(message = "O valor deve ser maior que zero.")
                                BigDecimal valor,

                                @NotNull(message = "A duração estimada é obrigatória.")
                                @Positive(message = "A duração deve ser maior que zero.")
                                int duracaoEstimadaEmSegundos) {
}
