package com.example.demo.services;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.cardapio_iff.Cardapio;
import com.example.demo.domain.cardapio_iff.CardapioDaSemana;
import com.example.demo.domain.cardapio_iff.Refeicao;
import com.example.demo.domain.cardapio_iff.Requisicao;
import com.example.demo.dtos.cardapio.CardapioDTO;
import com.example.demo.dtos.cardapio.RequisicaoDTO;
import com.example.demo.dtos.cardapio.RequisicoesPorTipoDTO;
import com.example.demo.repositorys.CardapioDaSemanaRepository;
import com.example.demo.repositorys.CardapioRepository;
import com.example.demo.repositorys.RefeicaoRepository;
import com.example.demo.repositorys.RequisicaoRepository;

@Service
public class DemoService {

    @Autowired
    private CardapioRepository cardapioRepository;
    @Autowired
    private CardapioDaSemanaRepository semanaRepository;
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Cardapio getCardapio(LocalDate data_hoje) throws Exception {
        return cardapioRepository  
                .findByData(data_hoje)
                .orElseThrow(() -> new Exception("cardapio nao existe"));
    }

    public String addCardapio(String semana_id, Set<CardapioDTO> cardapios) {
        CardapioDaSemana semana;
        if (semana_id !=null) {
            semana = semanaRepository.findById(semana_id).orElse(new CardapioDaSemana());
        } else {
            semana = new CardapioDaSemana();
        }
        semanaRepository.save(semana);
        cardapios.forEach(cardapio -> {
            Cardapio newCardapio = new Cardapio(cardapio, semana);
            cardapioRepository.save(newCardapio);
            cardapio.refeicoes().forEach(refeicao -> {
                refeicaoRepository.save(new Refeicao(refeicao, newCardapio));
            });
        });
        return semana.getSemana_id();
    }

    public RequisicoesPorTipoDTO requisicoesBytipo(String tipo_refeicao, LocalDate data) {
        Set<Long> refeicao_ids = cardapioRepository.findByData(data)
            .orElseThrow(() -> new RuntimeException("cardapio nao existe"))
            .getRefeicoes().stream()
                .filter(refeicao -> refeicao.getTipo_refeicao().equals(tipo_refeicao))
                .map(Refeicao::getRefeicao_id)
                .collect(Collectors.toSet());
        List<Requisicao> requisicoes = requisicaoRepository.findAll().stream()
            .filter(requisicao -> refeicao_ids.contains(requisicao.getRefeicao().getRefeicao_id()))
            .toList();
        Map<String, Long> requisicao_por_refeicao = requisicoes.stream()
            .map(Requisicao::getRefeicao)
            .collect(Collectors.groupingBy(
                Refeicao::getPrato_principal,
                Collectors.counting()
            ));
        return new RequisicoesPorTipoDTO(requisicoes.size(), requisicao_por_refeicao, requisicoes.stream().map(RequisicaoDTO::new).toList());
    }

}
