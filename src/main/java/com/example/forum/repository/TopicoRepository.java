package com.example.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	//Para criar uma consulta automática usando um atributo de uma tabela relacionada: findByEntidadeCampo
	Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);
}
