package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
