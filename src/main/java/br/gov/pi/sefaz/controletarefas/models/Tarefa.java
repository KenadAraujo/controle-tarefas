package br.gov.pi.sefaz.controletarefas.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.pi.sefaz.controletarefas.models.constants.StatusTarefa;

@Entity
@Table(name = "TAREFAS")
public class Tarefa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private int id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "DATA_CRIACAO")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(name = "DATA_CONCLUSAO")
	@Temporal(TemporalType.DATE)
	private Date dataConclusao;
	
	@Column(name = "STATUS_TAREFA")
	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}
	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	
}
