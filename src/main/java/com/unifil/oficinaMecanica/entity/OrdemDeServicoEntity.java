package com.unifil.oficinaMecanica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordens_de_servicos")
@Getter
@Setter
public class OrdemDeServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="placa_veiculo")
    private VeiculoEntity veiculo;

    @ManyToOne
    @JoinColumn(name="servico_id")
    private ServicoEntity servico;

    public enum Status{
        EM_ABERTO,
        EM_ANDAMENTO,
        FINALIZADA
    }

    @Enumerated(EnumType.STRING)
    private Status status;
}
