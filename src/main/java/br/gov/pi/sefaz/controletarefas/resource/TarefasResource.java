package br.gov.pi.sefaz.controletarefas.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pi.sefaz.controletarefas.config.exception.ExcecaoValidacao;
import br.gov.pi.sefaz.controletarefas.dto.MensagemDTO;
import br.gov.pi.sefaz.controletarefas.dto.TarefaDTO;
import br.gov.pi.sefaz.controletarefas.models.Tarefa;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;
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
	@GetMapping
	public ResponseEntity<?> listar(@RequestParam(value = "status",required = false)String status,
									@RequestParam(value = "dataInicial",required = false)
										@DateTimeFormat(pattern = "dd/MM/yyyy")Date dataInicial,
									@RequestParam(value = "tamanhoDaPagina",defaultValue = "10") int tamanhoDaPagina,
									@RequestParam(value = "pagina",defaultValue = "0") int pagina){
		StatusTarefa statusTarefa = null;
		if(status!=null) {
			statusTarefa = StatusTarefa.build(status);
		}
		Iterable<Tarefa> tarefas = tarefaService.listar(tamanhoDaPagina, pagina, statusTarefa, dataInicial);
		List<TarefaDTO> tarefasDtos = new ArrayList<>();
		tarefas.forEach(e->{
			tarefasDtos.add(new TarefaDTO(e));
		});
		return ResponseEntity.ok(tarefasDtos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> listarEspecifico(@PathVariable(name = "id") long idTarefa){
		Tarefa tarefa = tarefaService.buscarPorId(idTarefa);
		if(tarefa!=null) {
			TarefaDTO tarefaDTO = new TarefaDTO(tarefa);
			return ResponseEntity.ok(tarefaDTO);			
		}else {
			return ResponseEntity.ok(new MensagemDTO("Não há dados para serem mostrados!"));
		}
	}
}
