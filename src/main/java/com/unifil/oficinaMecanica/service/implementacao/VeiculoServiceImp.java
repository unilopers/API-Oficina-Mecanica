package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.dto.request.VeiculoRequestDTO;
import com.unifil.oficinaMecanica.dto.response.VeiculoResponseDTO;
import com.unifil.oficinaMecanica.entity.ClienteEntity;
import com.unifil.oficinaMecanica.entity.VeiculoEntity;
import com.unifil.oficinaMecanica.repository.ClienteRepository;
import com.unifil.oficinaMecanica.repository.VeiculoRepository;
import com.unifil.oficinaMecanica.service.interfaces.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoServiceImp implements VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean cadastrarNovoVeiculo(VeiculoRequestDTO dto) throws Exception {
        java.util.Optional<ClienteEntity> clienteOptional = clienteRepository.findById(dto.cpfCliente());

        if (clienteOptional.isEmpty()) {
            throw new Exception("Cliente não encontrado com o CPF: " + dto.cpfCliente());
        }

        ClienteEntity cliente = clienteOptional.get();

        try {
            VeiculoEntity veiculo = new VeiculoEntity();
            veiculo.setPlaca(dto.placa());
            veiculo.setMarca(dto.marca());
            veiculo.setModelo(dto.modelo());
            veiculo.setCor(dto.cor());

            veiculo.setCliente(cliente);

            veiculoRepository.save(veiculo);
            return true;

        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar cadastrar o veículo.\n" + e.getMessage());
        }
    }

    @Override
    public List<VeiculoResponseDTO> listarVeiculos() {
        List<VeiculoEntity> veiculos = veiculoRepository.findAll();

        return veiculos.stream()
                .map(entity -> new VeiculoResponseDTO(
                        entity.getPlaca(),
                        entity.getMarca(),
                        entity.getModelo(),
                        entity.getCor()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VeiculoResponseDTO buscarVeiculoPelaPlaca(String placa) {
        Optional<VeiculoEntity> veiculoOptional = veiculoRepository.findById(placa);

        if (veiculoOptional.isEmpty()) {
            return null;
        }

        VeiculoEntity entity = veiculoOptional.get();

        return new VeiculoResponseDTO(
                entity.getPlaca(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getCor()
        );
    }

    @Override
    public boolean atualizarInformacoes(String placa, VeiculoRequestDTO dto) throws Exception {
        Optional<VeiculoEntity> veiculoOptional = veiculoRepository.findById(placa);

        if (veiculoOptional.isEmpty()) {
            throw new Exception("Veículo não encontrado para atualização com placa: " + placa);
        }

        VeiculoEntity veiculoExistente = veiculoOptional.get();

        try {
            veiculoExistente.setPlaca(dto.placa());
            veiculoExistente.setMarca(dto.marca());
            veiculoExistente.setModelo(dto.modelo());
            veiculoExistente.setCor(dto.cor());

            veiculoRepository.save(veiculoExistente);
            return true;

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    @Override
    public boolean removerVeiculoPelaPlaca(String placa) throws Exception {
        if (!veiculoRepository.existsById(placa)) {
            throw new Exception("Veículo não encontrado com a placa: " + placa);
        }

        try {
            veiculoRepository.deleteById(placa);
            return true;
        } catch (Exception e) {
            throw new Exception("Não foi possível remover o veículo. Verifique se há Ordens de Serviço vinculadas.");
        }
    }
}
