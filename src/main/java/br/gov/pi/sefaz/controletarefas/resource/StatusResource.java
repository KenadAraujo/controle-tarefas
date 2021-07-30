package br.gov.pi.sefaz.controletarefas.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pi.sefaz.controletarefas.dto.MensagemDTO;
import br.gov.pi.sefaz.controletarefas.dto.StatusDTO;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/status-tarefa")
@Tag(name = "Recursos de Status da Tarefa",description = "Todos os recursos relacionado a manipulação dos status")
public class StatusResource {

	
	@Operation(summary = "Consulta os status possíveis de uma tarefa",
			responses = {
				@ApiResponse(description = "Sucesso na operação",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = StatusDTO.class))),
				@ApiResponse(description = "Ocorreu um erro na operação",responseCode = "400",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensagemDTO.class)))
			}
	)
	@GetMapping
	public ResponseEntity<?> listar(){
		List<StatusTarefa> status = Arrays.asList(StatusTarefa.values());
		List<StatusDTO> statusString = new ArrayList<>();
		status.forEach(e->{
			statusString.add(new StatusDTO(e.toString()));
		});
		return ResponseEntity.ok(statusString);
	}
}
