package com.example.demo.dtos.cardapio;

import java.util.Set;

public record SemanaDTO(String semana_id, Set<CardapioDTO> cardapios) {
}
