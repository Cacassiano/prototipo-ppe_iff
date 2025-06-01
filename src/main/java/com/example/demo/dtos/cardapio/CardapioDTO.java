package com.example.demo.dtos.cardapio;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.domain.cardapio_iff.Cardapio;

public record CardapioDTO(LocalDate data, String dia_da_semana, Set<RefeicoesDTO> refeicoes) {

    public CardapioDTO(Cardapio cardapio) {
        this(
            cardapio.getData(),
            cardapio.getDia_da_semana(),
            cardapio.getRefeicoes()
                .stream()
                .map(RefeicoesDTO::new)
                .collect(Collectors.toSet())
        );
    }

}
