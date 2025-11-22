package com.unifil.oficinaMecanica.repository;

import com.unifil.oficinaMecanica.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
