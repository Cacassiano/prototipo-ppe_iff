package com.example.demo.domain.cardapio_iff;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cardapio_da_semana")
@Table(name = "cardapio_da_semana")
@Getter
@Setter
public class CardapioDaSemana {
    @Id @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "semana_id")
    String semana_id;

    @OneToMany(mappedBy = "cardapio_da_semana")
    List<Cardapio> cardapios;

}

