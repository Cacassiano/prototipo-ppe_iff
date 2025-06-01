package com.example.demo.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.cardapio_iff.Cardapio;
import com.example.demo.domain.cardapio_iff.Refeicao;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long>{
    Optional<Refeicao> findByCardapioDoDiaAndTipoRefeicaoAndPratoPrincipalAndBebida(Cardapio cardapio_do_dia, String tipo_refeicao, String prato_principal, String bebida);
}
