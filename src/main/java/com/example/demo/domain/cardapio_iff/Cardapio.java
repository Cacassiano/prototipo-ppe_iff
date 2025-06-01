package com.example.demo.domain.cardapio_iff;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.dtos.cardapio.CardapioDTO;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cardapio_do_dia")
@Table(name = "cardapio_do_dia")
@Getter
@Setter
@NoArgsConstructor
public class Cardapio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "cardapio_id")
    Long cardapio_id;
    
    @Column(nullable = false, unique = false, name = "dia_da_semana", length = 15)
    String dia_da_semana;

    @Column(nullable = false, unique = true, name = "data")
    LocalDate data;

    @ManyToOne
    @JoinColumn(name = "semana_id")
    CardapioDaSemana cardapio_da_semana;
    
    @OneToMany(mappedBy = "cardapioDoDia")
    List<Refeicao> refeicoes;

    public Cardapio(CardapioDTO dto, CardapioDaSemana semana) {
        this.cardapio_da_semana = semana;
        this.data = dto.data();
        this.dia_da_semana = dto.dia_da_semana();
    }

}
