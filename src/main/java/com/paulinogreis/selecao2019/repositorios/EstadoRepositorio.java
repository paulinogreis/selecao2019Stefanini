package com.paulinogreis.selecao2019.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulinogreis.selecao2019.dominio.Estado;

@Repository
public interface EstadoRepositorio extends JpaRepository<Estado, Integer> {

}
