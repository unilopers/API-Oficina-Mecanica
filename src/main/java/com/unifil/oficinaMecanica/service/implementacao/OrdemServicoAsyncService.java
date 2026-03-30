package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.async.OrdemServicoWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrdemServicoAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(OrdemServicoAsyncService.class);

    @Autowired
    private OrdemServicoWorker worker;

    @Async("notificacaoTaskExecutor")
    public void processarOrdemAsync(Long ordemId) {
        try {
            logger.info("Iniciando processamento assíncrono para ordem ID: {}", ordemId);
            worker.executarProcessamento(ordemId);
            logger.info("Processamento assíncrono concluído para ordem ID: {}", ordemId);
        } catch (Exception e) {
            logger.error("Erro no processamento assíncrono para ordem ID: {}", ordemId, e);
        }
    }
}
