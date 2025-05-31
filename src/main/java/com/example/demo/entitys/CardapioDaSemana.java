package com.example.demo.entitys;

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

@Entity(name = "CardapioDaSemana")
@Table(name = "CardapioDaSemana")
@Getter
@Setter
public class CardapioDaSemana {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "semana_id")
    Long semana_id;

    @OneToMany(mappedBy = "cardapio_da_semana")
    List<Cardapio> cardapios;

}

