package br.gov.pi.sefaz.controletarefas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.gov.pi.sefaz.controletarefas.models.Tarefa;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;
import br.gov.pi.sefaz.controletarefas.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	public void cadastrar(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}
	public void deletar(Tarefa tarefa) {
		tarefaRepository.delete(tarefa);
	}
	public void editar(Tarefa tarefaNova) {
		tarefaRepository.save(tarefaNova);
	}
	public Iterable<Tarefa> listar(int tamanhoDaPagina,int pagina,StatusTarefa status, Date dataInicio){
		Pageable pag = PageRequest.of(pagina, tamanhoDaPagina,Sort.by("dataCriacao").descending());
		Tarefa tarefa = new Tarefa();
		tarefa.setStatusTarefa(status);
		tarefa.setDataCriacao(dataInicio);
		
		Iterable<Tarefa> tarefas = tarefaRepository.findAll(Example.of(tarefa),pag);
		return tarefas;
	}
	public Tarefa buscarPorId(long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		return tarefa.isPresent()?tarefa.get():null;
	}
}
