package com.example.demo.controllers;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CardapioDTO;
import com.example.demo.entitys.Cardapio;
import com.example.demo.services.DemoService;

@RestController
public class DemoController {

    private DemoService service; 

    @GetMapping("/cardapio/hoje")
    public ResponseEntity<CardapioDTO> getCardapioHoje() throws Exception {
        LocalDate data_hoje = LocalDate.now();
        Cardapio cardapio = service.getCardapio(data_hoje);
        return ResponseEntity.ok(new CardapioDTO(cardapio));
    }
}
