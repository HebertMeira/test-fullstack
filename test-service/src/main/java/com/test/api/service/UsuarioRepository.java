package com.test.api.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.api.response.UsuarioResponse;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioResponse, Long> {

}
