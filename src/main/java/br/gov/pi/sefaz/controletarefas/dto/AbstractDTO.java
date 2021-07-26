package br.gov.pi.sefaz.controletarefas.dto;

import br.gov.pi.sefaz.controletarefas.config.exception.ExcecaoValidacao;

public abstract class AbstractDTO<T> {
	
	public abstract T toModel() throws ExcecaoValidacao;
}
