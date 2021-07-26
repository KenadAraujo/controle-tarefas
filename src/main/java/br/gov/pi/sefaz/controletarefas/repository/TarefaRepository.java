package br.gov.pi.sefaz.controletarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pi.sefaz.controletarefas.models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	public Tarefa findByTitulo(String titulo);
}
