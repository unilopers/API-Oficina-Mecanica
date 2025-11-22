package com.unifil.oficinaMecanica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
public class VeiculoEntity {
    @Id
    private String placa;

    private String marca;

    private String modelo;

    private String cor;

    @ManyToOne
    @JoinColumn(name = "cpf_cliente")
    private ClienteEntity cliente;
}