package com.example.demo.domain.cardapio_iff;

import java.util.List;

import com.example.demo.dtos.cardapio.RefeicoesDTO;

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

@Entity(name = "refeicao")
@Table(name = "refeicao")
@Getter
@Setter
@NoArgsConstructor
public class Refeicao {
    @Id @Column(nullable = false, unique = true, name = "refeicao_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long refeicao_id;
    
    @Column(nullable = true, unique = false, name = "tipo_refeicao", length = 12)
    String tipoRefeicao;

    @Column(nullable = false, name = "prato_principal")
    String pratoPrincipal;

    @Column(nullable = true, unique = false, name = "bebida")
    String bebida;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    Cardapio cardapioDoDia;

    @OneToMany(mappedBy = "refeicao")
    List<Requisicao> requisicoes;

    public Refeicao(RefeicoesDTO dto, Cardapio cardapio) {
        this.bebida = dto.bebida();
        this.pratoPrincipal = dto.prato_principal();
        this.tipoRefeicao = dto.tipo_refeicao();
        this.cardapioDoDia = cardapio;
    }

}
