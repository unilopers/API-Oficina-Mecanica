package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.dto.request.ClienteRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;
import com.unifil.oficinaMecanica.entity.ClienteEntity;
import com.unifil.oficinaMecanica.entity.VeiculoEntity;
import com.unifil.oficinaMecanica.repository.ClienteRepository;
import com.unifil.oficinaMecanica.repository.VeiculoRepository;
import com.unifil.oficinaMecanica.service.interfaces.ClienteService;
import com.unifil.oficinaMecanica.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImp implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean cadastrarNovoCliente(ClienteRequestDTO dto) throws Exception {
        try {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setCpf(dto.cpf());
            cliente.setNome(dto.nome());
            cliente.setEmail(dto.email());
            clienteRepository.save(cliente);
            emailService.enviarEmailBoasVindas(dto.email(), dto.nome());
            return true;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar cadastrar o cliente.\n" + e.getMessage());
        }
    }

    @Override
    public boolean atualizarInformacoes(ClienteRequestDTO dto) throws Exception {
        try {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNome(dto.nome());
            cliente.setEmail(dto.email());
            clienteRepository.save(cliente);
            return true;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar atualizar as informações do cliente.\n" + e.getMessage());
        }
    }

    @Override
    public boolean removerCliente(String cpf) throws Exception {
        try {
            clienteRepository.deleteById(cpf);
            return true;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar remover o cliente.\n" + e.getMessage());
        }
    }

    @Override
    public List<VeiculoResponseDTO> getVeiculos(String cpf) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(cpf);

        if (clienteOptional.isEmpty()) {
            return new ArrayList<>();
        }

        List<VeiculoEntity> veiculosEntity = clienteOptional.get().getVeiculos();

        return veiculosEntity.stream()
                .map(entity -> new VeiculoResponseDTO(
                        entity.getPlaca(),
                        entity.getMarca(),
                        entity.getModelo(),
                        entity.getCor()
                ))
                .collect(Collectors.toList());
    }
}
