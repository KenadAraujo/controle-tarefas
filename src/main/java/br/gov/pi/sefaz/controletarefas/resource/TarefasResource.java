package br.gov.pi.sefaz.controletarefas.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Recursos de Tarefas",description = "Todos os recursos relacionado a manipulação das tarefas")
public class TarefasResource {

	@Autowired
	private TarefaService tarefaService;
	
	@Operation(summary = "Cadastrar tarefas",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class))),
				@ApiResponse(description = "Ocorreu um erro na operação",responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class)))
			}
	)
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
	
	@Operation(summary = "Consulta tarefas",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = TarefaDTO.class))),
			}
	)
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
	
	@Operation(summary = "Consulta uma tarefa específica",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = TarefaDTO.class))),
				@ApiResponse(description = "Ocorreu um erro na operação",responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class)))
			}
	)
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
	
	@Operation(summary = "Exclui uma tarefa específica",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class))),
				@ApiResponse(description = "Ocorreu um erro na operação",responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class)))
			}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarTarefa(@PathVariable(name = "id")long idTarefa){
		List<MensagemDTO> mensagens = new ArrayList<>();
		Tarefa tarefa = tarefaService.buscarPorId(idTarefa);
		if(tarefa!=null) {
			tarefaService.deletar(tarefa);
		}else {
			mensagens.add(new MensagemDTO("Tarefa não existe!"));
		}
		
		if(mensagens.isEmpty()) {
			mensagens.add(new MensagemDTO("Excluído com sucesso!"));
			return ResponseEntity.ok(mensagens);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagens);
		}
	}
	
	@Operation(summary = "Altera uma tarefa específica",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class))),
				@ApiResponse(description = "Ocorreu um erro na operação",responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class)))
			}
	)
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarTarefa(@PathVariable(name = "id") long idTarefa,
											@Valid @RequestBody TarefaDTO tarefaNova,BindingResult result){
		
		List<MensagemDTO> mensagens = new ArrayList<>();
		if(result.hasErrors()) {
			mensagens.addAll(MensagemDTO.construirErros(result));
		}
		try {
			Tarefa tarefa =  tarefaNova.toModel();
			tarefa.setId(idTarefa);
			tarefaService.editar(tarefa);
		} catch (ExcecaoValidacao e) {
			mensagens.add(new MensagemDTO(e.getMessage()));
		}
		if(mensagens.isEmpty()) {
			mensagens.clear();
			mensagens.add(new MensagemDTO("Atualizado com sucesso!"));
			return ResponseEntity.ok(mensagens);
		}else {
			return ResponseEntity.badRequest().body(mensagens);
		}
	}
}
