package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.cardapio_iff.Cardapio;
import com.example.demo.dtos.cardapio.CardapioDTO;
import com.example.demo.dtos.cardapio.RequisicaoDTO;
import com.example.demo.dtos.cardapio.RequisicaoReqDTO;
import com.example.demo.dtos.cardapio.RequisicoesPorTipoDTO;
import com.example.demo.dtos.cardapio.SemanaDTO;
import com.example.demo.services.DemoService;

@RestController
public class DemoController {

    @Autowired
    private DemoService service; 

    @GetMapping("/cardapio/hoje")
    public ResponseEntity<CardapioDTO> getCardapioHoje() throws Exception {
        LocalDate data_hoje = LocalDate.now();
        Cardapio cardapio = service.getCardapio(data_hoje);
        return ResponseEntity.ok(new CardapioDTO(cardapio));
    }

    @PostMapping("/cardapio/adicionar")
    public ResponseEntity<Map<String,String>> addCardapio(@RequestBody SemanaDTO request) {
        Map<String, String> respMap = new HashMap<>();
        if(request.cardapios() == null || request.cardapios().size() <1) {
            respMap.put("error", "Nenhum cardapio foi enviado");
            return ResponseEntity.badRequest().body(respMap);
        }
        String semana_id = service.addCardapio(request.semana_id(), request.cardapios());
        respMap.put("semana_id", semana_id);
        return ResponseEntity.status(201).body(respMap);
    }

    @GetMapping(value = "/requisicao/hoje/{tipo_refeicao}")
    public ResponseEntity<RequisicoesPorTipoDTO> getRequisicoesPorTipo(@PathVariable String tipo_refeicao) {
        LocalDate data_hoje = LocalDate.now();
        RequisicoesPorTipoDTO requisicoes = service.requisicoesBytipo(tipo_refeicao, data_hoje);
        return ResponseEntity.ok(requisicoes);
    }

    @PostMapping("/requisicao/hoje/create")
    public ResponseEntity<RequisicaoDTO> createRequisicao(@RequestBody RequisicaoReqDTO body) throws Exception {
        if (body ==null || (body.matricula() == null || body.matricula().length() <10)) 
            throw new Exception("Requisicao invalida");
        RequisicaoDTO resp = service.createRequisicao(body);
        return ResponseEntity.status(201).body(resp);
    }

    @GetMapping("/cardapio/semana/{data}")
    public ResponseEntity<SemanaDTO> getCardapioDaSemana(@PathVariable LocalDate data) throws Exception {
        return ResponseEntity.ok(service.getCardapioDaSemana(data));
    }
}
