package com.unifil.oficinaMecanica.async;

import com.unifil.oficinaMecanica.entity.OrdemDeServicoEntity;
import com.unifil.oficinaMecanica.entity.ServicoEntity;
import com.unifil.oficinaMecanica.repository.OrdemDeServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RelatorioServicosScheduler {

    private static final Logger log = LoggerFactory.getLogger(RelatorioServicosScheduler.class);

    @Autowired
    private OrdemDeServicoRepository repository;

    @Scheduled(cron = "0 0 23 * * ?")
    public void gerarRelatorioDiarioOS() {
        String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("[CRON JOB] [" + dataAtual + "] Iniciando geracao do relatorio...");

        try {
            List<OrdemDeServicoEntity> todasAsOs = repository.findAll();

            List<OrdemDeServicoEntity> concluidas = todasAsOs.stream()
                    .filter(os -> os.getStatus() == OrdemDeServicoEntity.Status.FINALIZADA)
                    .toList();

            int total = concluidas.size();
            
            BigDecimal faturamento = concluidas.stream()
                    .filter(os -> os.getServico() != null && os.getServico().getValor() != null)
                    .map(os -> (BigDecimal) os.getServico().getValor())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            log.info("[CRON JOB] -------------------------------------------");
            log.info("[CRON JOB] RELATORIO DIARIO GERADO COM SUCESSO");
            log.info("[CRON JOB] Total de Ordens Concluidas: " + total);
            log.info("[CRON JOB] Faturamento Total: R$ " + faturamento.toString());
            log.info("[CRON JOB] -------------------------------------------");

        } catch (Exception e) {
            log.error("[CRON JOB] Erro ao processar dados do banco: " + e.getMessage());
        }
    }
}