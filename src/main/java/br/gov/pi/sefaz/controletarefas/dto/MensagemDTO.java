package br.gov.pi.sefaz.controletarefas.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.validation.BindingResult;

public class MensagemDTO {
	
	private String mensagem;
	private Date data;
	
	public MensagemDTO() {
		this.data = new Date();
	}
	public MensagemDTO(String mensagem, Date data) {
		super();
		this.mensagem = mensagem;
		this.data = data;
	}
	public MensagemDTO(String message) {
		super();
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public static List<MensagemDTO> construirErros(BindingResult result) {
		List<MensagemDTO> erros = new ArrayList<>();
		result.getAllErrors().forEach(e->{
			erros.add(new MensagemDTO(e.getDefaultMessage(),new Date()));
		});
		return erros;
	}
}
