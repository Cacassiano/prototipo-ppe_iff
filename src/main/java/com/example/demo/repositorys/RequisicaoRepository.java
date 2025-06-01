package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.cardapio_iff.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long>{
}
