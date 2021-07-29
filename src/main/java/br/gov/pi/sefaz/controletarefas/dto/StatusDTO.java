package br.gov.pi.sefaz.controletarefas.dto;

import java.util.Objects;

public class StatusDTO {
	
	private String descricao;

	public StatusDTO(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
