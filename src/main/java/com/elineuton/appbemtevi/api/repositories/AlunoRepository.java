package com.elineuton.appbemtevi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elineuton.appbemtevi.api.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
