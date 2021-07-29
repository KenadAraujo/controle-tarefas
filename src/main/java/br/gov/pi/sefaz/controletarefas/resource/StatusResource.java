package br.gov.pi.sefaz.controletarefas.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pi.sefaz.controletarefas.dto.StatusDTO;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;

@RestController
@RequestMapping("/status-tarefa")
public class StatusResource {

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
