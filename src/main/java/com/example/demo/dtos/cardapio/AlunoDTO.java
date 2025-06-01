package com.example.demo.dtos.cardapio;

import com.example.demo.domain.cardapio_iff.Aluno;

public record AlunoDTO(String matricula, boolean pode_almocar, String prim_nome, String sobrenome, String curso) {
    public AlunoDTO(Aluno aluno) {
        this(
            aluno.getMatricula(),
            aluno.isPode_almocar(),
            aluno.getPrim_nome(),
            aluno.getSobrenome(),
            aluno.getCurso()
        );
    }
}
