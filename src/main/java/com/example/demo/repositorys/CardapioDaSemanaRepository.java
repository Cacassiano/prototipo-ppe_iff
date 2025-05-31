package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.CardapioDaSemana;

public interface CardapioDaSemanaRepository extends JpaRepository<CardapioDaSemana, Long> {
    
}
