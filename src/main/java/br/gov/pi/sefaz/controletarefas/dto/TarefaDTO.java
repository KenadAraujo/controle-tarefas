package br.gov.pi.sefaz.controletarefas.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.gov.pi.sefaz.controletarefas.config.exception.ExcecaoValidacao;
import br.gov.pi.sefaz.controletarefas.models.Tarefa;
import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;


public class TarefaDTO extends AbstractDTO<Tarefa>{

	@NotNull(message = "O titulo precisa ser fornecido!")
	@NotEmpty(message = "O titulo não pode ser vazio!")
	private String titulo;
	
	@NotNull(message = "A descricao precisa ser fornecido!")
	@NotEmpty(message = "A descricao não pode ser vazio!")
	private String descricao;
	
	@NotNull(message = "A data da criacao precisa ser fornecida")
	private Date dataCriacao;
	
	private Date dataConclusao;
	
	@NotNull(message = "O status da tarefa precisa ser fornecido")
	@NotEmpty(message = "O status da tarefa não pode ser vazio")
	private String statusTarefa;

	public TarefaDTO() {}

	public TarefaDTO(Tarefa tarefa) {
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.dataCriacao = tarefa.getDataCriacao();
		this.dataConclusao = tarefa.getDataConclusao();
		this.statusTarefa = tarefa.getStatusTarefa().name().toString();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(String statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	@Override
	public Tarefa toModel() throws ExcecaoValidacao {
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo(this.getTitulo());
		tarefa.setDescricao(this.getDescricao());
		tarefa.setDataCriacao(this.getDataCriacao());
		tarefa.setDataConclusao(this.getDataConclusao());
		if(this.getStatusTarefa().equalsIgnoreCase("ABERTA")) {
			tarefa.setStatusTarefa(StatusTarefa.ABERTA);
		}else if(this.getStatusTarefa().equalsIgnoreCase("EM_ANDAMENTO")) {
			tarefa.setStatusTarefa(StatusTarefa.EM_ANDAMENTO);
		}else if(this.getStatusTarefa().equalsIgnoreCase("CONCLUIDA")){
			tarefa.setStatusTarefa(StatusTarefa.CONCLUIDA);
		}else {
			throw new ExcecaoValidacao("O status informado não é valido!");
		}
		return tarefa;
	}

}
