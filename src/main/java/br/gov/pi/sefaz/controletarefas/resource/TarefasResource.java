package br.gov.pi.sefaz.controletarefas.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pi.sefaz.controletarefas.config.exception.ExcecaoValidacao;
import br.gov.pi.sefaz.controletarefas.dto.MensagemDTO;
import br.gov.pi.sefaz.controletarefas.dto.TarefaDTO;
import br.gov.pi.sefaz.controletarefas.models.Tarefa;
import br.gov.pi.sefaz.controletarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefasResource {

	@Autowired
	private TarefaService tarefaService;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody TarefaDTO tarefaNova,BindingResult result){
		List<MensagemDTO> mensagens = new ArrayList<>();
		if(result.hasErrors()) {
			mensagens.addAll(MensagemDTO.construirErros(result));
		}
		try {
			Tarefa tarefa = tarefaNova.toModel();
			tarefaService.cadastrar(tarefa);
		} catch (ExcecaoValidacao e) {
			mensagens.add(new MensagemDTO(e.getMessage()));
		}
		if(mensagens.size()>0) {
			return ResponseEntity.badRequest().body(mensagens);
		}
		mensagens.clear();
		mensagens.add(new MensagemDTO("Cadastro com sucesso!"));
		return ResponseEntity.ok(mensagens);
	}
}
