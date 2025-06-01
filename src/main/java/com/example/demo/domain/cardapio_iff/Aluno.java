package com.example.demo.domain.cardapio_iff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "alunos")
@Entity(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    @Id @Column(nullable = false, unique = true, length = 12, name = "matricula") 
    String matricula;

    @Column(nullable = false, unique = false, length = 30, name = "prim_nome")
    String prim_nome;

    @Column(nullable = false, unique = false, name = "sobrenome")
    String sobrenome;

    @Column(nullable = false, unique = false, name = "curso")
    String curso;

    @Column(nullable = false, unique = false, name = "pode_almocar")
    boolean pode_almocar;
}
