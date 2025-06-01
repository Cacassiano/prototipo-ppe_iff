package com.example.demo.dtos.cardapio;

import com.example.demo.domain.cardapio_iff.Refeicao;

public record RefeicoesDTO(String tipo_refeicao, String prato_principal, String bebida) {
    public RefeicoesDTO(Refeicao refeicao) {
        this(
            refeicao.getTipo_refeicao(),
            refeicao.getPrato_principal(),
            refeicao.getBebida()
        );
    }
}
