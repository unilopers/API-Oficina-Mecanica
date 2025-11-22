package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.dto.request.ServicoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.ServicoResponseDTO;
import com.unifil.oficinaMecanica.entity.ServicoEntity;
import com.unifil.oficinaMecanica.repository.ServicoRepository;
import com.unifil.oficinaMecanica.service.interfaces.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoServiceImp implements ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public boolean cadastrarNovoServico(ServicoRequestDTO dto) throws Exception {
        try {
            ServicoEntity servico = new ServicoEntity();
            servico.setDescricao(dto.descricao());
            servico.setValor(dto.valor());
            servico.setDuracaoEstimadaEmSegundos(dto.duracaoEstimadaEmSegundos());
            servicoRepository.save(servico);
            return true;
        } catch(Exception e) {
            throw new Exception("Ocorreu um erro ao tentar cadastrar o serviço.\n" + e.getMessage());
        }
    }

    @Override
    public List<ServicoResponseDTO> getServicos() {
        List<ServicoEntity> servicos = servicoRepository.findAll();

        return servicos.stream()
                .map(entity -> new ServicoResponseDTO(
                        entity.getId(),
                        entity.getDescricao(),
                        entity.getValor(),
                        entity.getDuracaoEstimadaEmSegundos()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removerServico(Long id) throws Exception {
        try {
            servicoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar remover o serviço.\n" + e.getMessage());
        }
    }
}
