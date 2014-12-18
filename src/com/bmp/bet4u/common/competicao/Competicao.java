package com.bmp.bet4u.common.competicao;

import java.io.Serializable;

import com.bmp.bet4u.common.modalidade.Modalidade;
import com.bmp.bet4u.common.pais.Pais;


public class Competicao implements Serializable {

	private static final long serialVersionUID = -4840053558427433934L;
	
	private int id;
	private String descricao;
	private Pais pais;
	private Modalidade modalidade;
	
	public Competicao() {}
	public Competicao(int id, String descricao, Pais pais, Modalidade modalidade) {
		this.id = id;
		this.descricao = descricao;
		this.pais = pais;
		this.modalidade = modalidade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Modalidade getModalidade() {
		return modalidade;
	}
	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	@Override
	public String toString() {
		return "{ id : " + id + ", descricao : " + descricao + ", pais : "
				+ pais + ", modalidade : " + modalidade + " }";
	}
}
