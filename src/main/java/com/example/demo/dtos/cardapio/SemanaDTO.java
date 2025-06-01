package com.example.demo.dtos.cardapio;

import java.util.List;

import com.example.demo.domain.cardapio_iff.CardapioDaSemana;

public record SemanaDTO(String semana_id, List<CardapioDTO> cardapios) {

    public SemanaDTO(CardapioDaSemana semana) {
        this(
            semana.getSemana_id(),
            semana.getCardapios().stream()
                .map(CardapioDTO::new)
                .toList()
        );
    }
}
