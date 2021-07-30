package br.gov.pi.sefaz.controletarefas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import br.gov.pi.sefaz.controletarefas.models.Tarefa;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>,QueryByExampleExecutor<Tarefa>{
	
	public Tarefa findByTitulo(String titulo);
	
	public Iterable<Tarefa> findByStatusTarefa(StatusTarefa status);
	
}
