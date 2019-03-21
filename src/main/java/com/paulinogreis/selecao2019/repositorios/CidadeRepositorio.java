package com.paulinogreis.selecao2019.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulinogreis.selecao2019.dominio.Cidade;

@Repository
public interface CidadeRepositorio extends JpaRepository<Cidade, Integer> {

}
