package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.service.interfaces.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImp.class);

    @Override
    @Async("notificacaoTaskExecutor")
    public void enviarEmailBoasVindas(String email, String nome) {
        try {
            Thread.sleep(2000); //*utilizo o thread sleep para simular o tempo gasto para enviar um email
            logger.info("Email de boas-vindas enviado com sucesso para {} ({})", nome, email);
        } catch (InterruptedException e) {
            logger.error("Erro ao enviar email de boas-vindas para {}: {}", email, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
