package br.gov.pi.sefaz.controletarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = 
	@Info(title = "API de Controle de Tarefas", 
		version = "1.0", 
		description = "Uma api de exemplo para Controle de Tarefas, usada como referência para futuros projetos"))
public class ControleTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleTarefasApplication.class, args);
	}

}
