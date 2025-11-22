package com.unifil.oficinaMecanica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VeiculoRequestDTO(@NotBlank(message = "A placa é obrigatória.")
                                @Size(max = 7, message = "A placa deve ter no máximo 7 caracteres.")
                                String placa,

                                @NotBlank(message = "A marca é obrigatória.")
                                String marca,

                                @NotBlank(message = "O modelo é obrigatório.")
                                String modelo,

                                @NotBlank(message = "A cor é obrigatória.")
                                String cor,

                                @NotBlank(message = "O CPF do proprietário é obrigatório.")
                                @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos.")
                                String cpfCliente) {
}
