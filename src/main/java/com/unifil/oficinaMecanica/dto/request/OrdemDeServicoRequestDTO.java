package com.unifil.oficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrdemDeServicoRequestDTO(@NotBlank(message = "A placa do veículo é obrigatória.")
                                       String placaVeiculo,

                                       @NotNull(message = "O ID do serviço é obrigatório.")
                                       @Positive(message = "O ID do serviço deve ser um número válido.")
                                       Long idServico) {
}
