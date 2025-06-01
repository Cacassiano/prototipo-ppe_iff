package com.example.demo.dtos.cardapio;

import com.example.demo.domain.cardapio_iff.Requisicao;

public record RequisicaoDTO(RefeicoesDTO refeicoesDTO, AlunoDTO alunoDTO) {
    public RequisicaoDTO(Requisicao requisicao) {
        this(
            new RefeicoesDTO(requisicao.getRefeicao()),
            new AlunoDTO(requisicao.getAluno())
        );
    }
}
