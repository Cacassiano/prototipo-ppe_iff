package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.entitys.Cardapio;
import com.example.demo.repositorys.CardapioRepository;

@Service
public class DemoService {

    private CardapioRepository cardapioRepository;

    public Cardapio getCardapio(LocalDate data_hoje) throws Exception {
        return cardapioRepository  
                .findByData(data_hoje)
                .orElseThrow(() -> new Exception("cardapio nao existe"));
    }

}
