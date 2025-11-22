package com.unifil.oficinaMecanica.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(@NotBlank(message = "O CPF é obrigatório.")
                                @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
                                String cpf,

                                @NotBlank(message = "O nome é obrigatório.")
                                @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres.")
                                String nome,

                                @NotBlank(message = "O email é obrigatório.")
                                @Email(message = "Formato de email inválido.")
                                String email) {
}
