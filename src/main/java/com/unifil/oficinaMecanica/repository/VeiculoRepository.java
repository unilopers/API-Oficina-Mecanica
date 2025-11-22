package com.unifil.oficinaMecanica.repository;

import com.unifil.oficinaMecanica.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, String> {
}
