package com.unifil.oficinaMecanica.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serviço assíncrono para auditoria de alterações na entidade Cliente.
 * Registra todas as operações (CREATE, UPDATE, DELETE) de forma não-bloqueante.
 * 
 * @author Pedro Reis (Auditoria de Alterações)
 */
@Service
public class AuditoriaClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriaClienteService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Registra a criação de um novo cliente de forma assíncrona.
     *
     * @param cpf  CPF do cliente criado
     * @param nome Nome do cliente criado
     * @param email Email do cliente criado
     */
    @Async("auditoriaExecutor")
    public void registrarCriacao(String cpf, String nome, String email) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            String mensagem = String.format(
                    "[AUDITORIA - CREATE] Timestamp: %s | Operação: CRIAÇÃO DE CLIENTE | CPF: %s | Nome: %s | Email: %s",
                    timestamp, cpf, nome, email
            );
            LOGGER.info(mensagem);
        } catch (Exception e) {
            LOGGER.error("Erro ao registrar auditoria de criação de cliente", e);
        }
    }

    /**
     * Registra a atualização de informações de um cliente de forma assíncrona.
     *
     * @param cpf        CPF do cliente atualizado
     * @param nomAntigo  Nome anterior
     * @param nomeNovo   Novo nome
     * @param emailAntigo Email anterior
     * @param emailNovo  Novo email
     */
    @Async("auditoriaExecutor")
    public void registrarAtualizacao(String cpf, String nomAntigo, String nomeNovo,
                                      String emailAntigo, String emailNovo) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            StringBuilder sb = new StringBuilder();
            sb.append("[AUDITORIA - UPDATE] Timestamp: ").append(timestamp);
            sb.append(" | Operação: ATUALIZAÇÃO DE CLIENTE | CPF: ").append(cpf);
            
            if (!nomAntigo.equals(nomeNovo)) {
                sb.append(" | Nome: '").append(nomAntigo).append("' → '").append(nomeNovo).append("'");
            }
            
            if (!emailAntigo.equals(emailNovo)) {
                sb.append(" | Email: '").append(emailAntigo).append("' → '").append(emailNovo).append("'");
            }
            
            LOGGER.info(sb.toString());
        } catch (Exception e) {
            LOGGER.error("Erro ao registrar auditoria de atualização de cliente", e);
        }
    }

    /**
     * Registra a exclusão de um cliente de forma assíncrona.
     *
     * @param cpf   CPF do cliente removido
     * @param nome  Nome do cliente removido
     * @param email Email do cliente removido
     */
    @Async("auditoriaExecutor")
    public void registrarDelecao(String cpf, String nome, String email) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            String mensagem = String.format(
                    "[AUDITORIA - DELETE] Timestamp: %s | Operação: EXCLUSÃO DE CLIENTE | CPF: %s | Nome: %s | Email: %s",
                    timestamp, cpf, nome, email
            );
            LOGGER.info(mensagem);
        } catch (Exception e) {
            LOGGER.error("Erro ao registrar auditoria de exclusão de cliente", e);
        }
    }
}
