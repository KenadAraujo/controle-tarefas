package br.gov.pi.sefaz.controletarefas.models.constants;

public enum StatusTarefa {
	
	ABERTA,EM_ANDAMENTO,CONCLUIDA;

	public static StatusTarefa build(String status) {
		if("ABERTA".equalsIgnoreCase(status)) {
			return StatusTarefa.ABERTA;
		}
		if("EM_ANDAMENTO".equalsIgnoreCase(status)) {
			return StatusTarefa.EM_ANDAMENTO;
		}
		if("CONCLUIDA".equalsIgnoreCase(status)) {
			return StatusTarefa.CONCLUIDA;
		}
		return null;
	}
	
}
