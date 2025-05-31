package com.example.demo.entitys;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "refeicao")
@Table(name = "refeicao")
public class Refeicao {
    @Id @Column(nullable = false, unique = true, name = "refeicao_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long refeicao_id;
    
    @Column(nullable = false, unique = false, name = "tipo_refeicao", length = 12)
    String tipo_refeicao;

    @Column(nullable = false, name = "prato_principal")
    String prato_principal;

    @Column(nullable = true, unique = false, name = "bebida")
    String bebida;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    Cardapio cardapio_do_dia;

    @OneToMany(mappedBy = "refeicao")
    List<Requisicao> requisicoes;
}
