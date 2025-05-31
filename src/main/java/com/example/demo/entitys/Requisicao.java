package com.example.demo.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity(name = "requisicao")
@Table(name = "requisicao_de_refeicao")
@Getter
@Setter
public class Requisicao {
    
    @Id @Column(nullable = false, unique = true, name = "req_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long req_id;
    
    @ManyToOne
    @JoinColumn(name = "matricula")
    Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    Refeicao refeicao;
}
