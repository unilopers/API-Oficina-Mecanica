package com.unifil.oficinaMecanica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClienteEntity {
    @Id
    private String cpf;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<VeiculoEntity> veiculos;
}
