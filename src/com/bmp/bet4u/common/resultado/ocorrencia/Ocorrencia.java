package com.bmp.bet4u.common.resultado.ocorrencia;

import com.bmp.bet4u.common.modalidade.Modalidade;

public class Ocorrencia {

	private String id;
	private String descricao;
	private Modalidade modalidade;
	
	public Ocorrencia(){};
	public Ocorrencia(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Modalidade getModalidade() {
		return modalidade;
	}
	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", descricao=" + descricao
				+ ", modalidade=" + modalidade + "]";
	}
}
