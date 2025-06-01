package com.example.demo.dtos.cardapio;

import java.util.List;
import java.util.Map;

public record RequisicoesPorTipoDTO(
        int contagem_total, 
        Map<String, Long> requisicoes_por_refeicao, 
        List<RequisicaoDTO> requisicoes
    ) {}
