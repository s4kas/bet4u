package com.bmp.bet4u.common.modalidade;

import java.io.Serializable;

public class Modalidade implements Serializable {

	private static final long serialVersionUID = -7089697081837759713L;
	
	private int id;
	private String descricao;
	
	public Modalidade(){}
	public Modalidade(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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
	@Override
	public String toString() {
		return "{ id : " + id + ", descricao : " + descricao + " }";
	}
}
