package com.example.demo.repositorys;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Cardapio;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>{
    Optional<Cardapio> findByData(LocalDate data);
}
