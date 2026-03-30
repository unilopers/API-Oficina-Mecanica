package com.unifil.oficinaMecanica.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RelatorioServicosScheduler {

    private static final Logger log = LoggerFactory.getLogger(RelatorioServicosScheduler.class);

    @Scheduled(cron = "0 0 23 * * ?")
    public void gerarRelatorioDiarioOS() {
        String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("[CRON JOB]  [{}] A iniciar geração do relatório assíncrono de serviços...", dataAtual);

        try {
            log.info("[CRON JOB]  A procurar Ordens de Serviço finalizadas na base de dados...");
            Thread.sleep(2000); // Simulação do tempo de busca na base de dados

            int totalOsConcluidas = 14; 
            double valorTotalArrecadado = 4350.75;

            if (totalOsConcluidas < 0) {
                throw new RuntimeException("Inconsistência na contagem de Ordens de Serviço.");
            }

            log.info("[CRON JOB] ===========================================");
            log.info("[CRON JOB]  RELATÓRIO DIÁRIO GERADO COM SUCESSO");
            log.info("[CRON JOB]  Total de OS Finalizadas hoje: {}", totalOsConcluidas);
            log.info("[CRON JOB]  Valor Total Arrecadado: R$ {}", valorTotalArrecadado);
            log.info("[CRON JOB] ===========================================");

        } catch (InterruptedException e) {
            log.error("[CRON JOB]  Erro de interrupção: {}", e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("[CRON JOB]  Falha ao gerar relatório diário: {}. Uma nova tentativa será feita no próximo ciclo.", e.getMessage());
        }
    }
}