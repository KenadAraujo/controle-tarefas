package br.gov.pi.sefaz.controletarefas.config.exception;

public class ExcecaoValidacao extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ExcecaoValidacao() {
		super();
	}
	
	public ExcecaoValidacao(String mensagem) {
		super(mensagem);
	}
}
