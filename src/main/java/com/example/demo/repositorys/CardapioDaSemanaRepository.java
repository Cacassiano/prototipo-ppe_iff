package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.cardapio_iff.CardapioDaSemana;

public interface CardapioDaSemanaRepository extends JpaRepository<CardapioDaSemana, String> {
    
}
