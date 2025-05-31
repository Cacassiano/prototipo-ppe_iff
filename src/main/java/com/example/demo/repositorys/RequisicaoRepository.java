package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long>{

}
