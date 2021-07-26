package br.gov.pi.sefaz.controletarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pi.sefaz.controletarefas.models.Tarefa;
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
}
