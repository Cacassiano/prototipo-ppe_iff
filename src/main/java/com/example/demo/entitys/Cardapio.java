package com.example.demo.entitys;

import java.time.LocalDate;
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
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cardapio_do_dia")
@Table(name = "cardapio_do_dia")
@Getter
@Setter
public class Cardapio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "cardapio_id")
    Long cardapio_id;
    
    @Column(nullable = false, unique = false, name = "dia_da_semana", length = 15)
    String dia_da_semana;

    @Column(nullable = false, unique = true, name = "data")
    LocalDate data;

    @ManyToOne
    @JoinColumn(name = "semana_id")
    public Long semana_id;
    
    @OneToMany(mappedBy = "cardapio_do_dia")
    List<Refeicao> refeicoes;

}
