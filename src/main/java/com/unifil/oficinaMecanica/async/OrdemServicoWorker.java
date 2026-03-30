package com.unifil.oficinaMecanica.async;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrdemServicoWorker {

    private static final Logger logger = LoggerFactory.getLogger(OrdemServicoWorker.class);

    public void executarProcessamento(Long ordemId) {
        logger.info("Executando cálculo de custo para ordem ID: {}", ordemId);
        try {
            Thread.sleep(2000); // Simula tempo de processamento
            logger.info("Custo calculado e notificação enviada para ordem ID: {}", ordemId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Processamento interrompido para ordem ID: {}", ordemId);
        }
    }
}

