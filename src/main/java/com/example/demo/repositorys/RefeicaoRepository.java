package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long>{

}
