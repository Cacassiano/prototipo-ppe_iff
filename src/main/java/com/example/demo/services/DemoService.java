package com.example.demo.services;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.cardapio_iff.Aluno;
import com.example.demo.domain.cardapio_iff.Cardapio;
import com.example.demo.domain.cardapio_iff.CardapioDaSemana;
import com.example.demo.domain.cardapio_iff.Refeicao;
import com.example.demo.domain.cardapio_iff.Requisicao;
import com.example.demo.dtos.cardapio.CardapioDTO;
import com.example.demo.dtos.cardapio.RequisicaoDTO;
import com.example.demo.dtos.cardapio.RequisicaoReqDTO;
import com.example.demo.dtos.cardapio.RequisicoesPorTipoDTO;
import com.example.demo.dtos.cardapio.SemanaDTO;
import com.example.demo.repositorys.AlunoRepository;
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
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CardapioDaSemanaRepository cardapioDaSemanaRepository;

    private void saveCardapios(CardapioDaSemana semana, List<CardapioDTO> cardapios) {
        cardapios.forEach(cardapio -> {
            Cardapio newCardapio = new Cardapio(cardapio, semana);
            try {
                cardapioRepository.save(newCardapio);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            cardapio.refeicoes().forEach(refeicao -> {
                refeicaoRepository.save(new Refeicao(refeicao, newCardapio));
            });
        });
    }

        public Cardapio getCardapio(LocalDate data_hoje) throws Exception {
            return cardapioRepository  
                    .findByData(data_hoje)
                    .orElseThrow(() -> new Exception("cardapio nao existe"));
        }
    
        public String addCardapio(String semana_id, List<CardapioDTO> cardapios) {
            CardapioDaSemana semana;
            if (semana_id !=null) {
                semana = semanaRepository.findById(semana_id).orElse(new CardapioDaSemana());
            } else {
                semana = new CardapioDaSemana();
            }
            semanaRepository.save(semana);
            this.saveCardapios(semana, cardapios);
            return semana.getSemana_id();
        }
    
        public RequisicoesPorTipoDTO requisicoesBytipo(String tipo_refeicao, LocalDate data) {
            Set<Long> refeicao_ids = cardapioRepository.findByData(data)
                .orElseThrow(() -> new RuntimeException("cardapio nao existe"))
                .getRefeicoes().stream()
                    .filter(refeicao -> refeicao.getTipoRefeicao().equals(tipo_refeicao))
                    .map(Refeicao::getRefeicao_id)
                    .collect(Collectors.toSet());
            List<Requisicao> requisicoes = requisicaoRepository.findAll().stream()
                .filter(requisicao -> refeicao_ids.contains(requisicao.getRefeicao().getRefeicao_id()))
                .toList();
            Map<String, Long> requisicao_por_refeicao = requisicoes.stream()
                .map(Requisicao::getRefeicao)
                .collect(Collectors.groupingBy(
                    Refeicao::getPratoPrincipal,
                    Collectors.counting()
                ));
            return new RequisicoesPorTipoDTO(requisicoes.size(), requisicao_por_refeicao, requisicoes.stream().map(RequisicaoDTO::new).toList());
        }
    
        public RequisicaoDTO createRequisicao(RequisicaoReqDTO body) throws Exception {
            Aluno aluno = alunoRepository.findById(body.matricula()).orElseThrow(() -> new Exception("Aluno nao existe"));
            Cardapio hoje_Cardapio = cardapioRepository.findByData(LocalDate.now()).orElseThrow(() -> new Exception("Cardapio nao existe"));
            Refeicao refeicao = refeicaoRepository.findByCardapioDoDiaAndTipoRefeicaoAndPratoPrincipalAndBebida(
                hoje_Cardapio, 
                body.refeicao().tipo_refeicao(), 
                body.refeicao().prato_principal(), 
                body.refeicao().bebida()
            ).orElseThrow(() -> new Exception("refeicao nao existe"));

            Requisicao req = new Requisicao(refeicao, aluno);
            requisicaoRepository.save(req);

            return new RequisicaoDTO(req);
        }

        public SemanaDTO getCardapioDaSemana(LocalDate data) throws Exception {
            CardapioDaSemana cardapio = cardapioDaSemanaRepository.getReferenceById(this.getCardapio(data).getCardapio_da_semana().getSemana_id());
            return new SemanaDTO(cardapio);
        }

        public void updateCardapio(List<CardapioDTO> updates) {
            List<Cardapio> cardapios = new ArrayList<>();            
        }

}
