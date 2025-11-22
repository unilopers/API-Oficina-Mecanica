package com.unifil.oficinaMecanica.repository;

import com.unifil.oficinaMecanica.entity.OrdemDeServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServicoEntity, Long> {
    List<OrdemDeServicoEntity> findByStatusNot(OrdemDeServicoEntity.Status status);
}
