package com.bmp.bet4u.common.pais;

import java.io.Serializable;

public class Pais implements Serializable {
	
	private static final long serialVersionUID = -7948110219914452940L;
	
	private String id;
	private String descricao;
	
	public Pais() {}
	public Pais(Pais pais) {
		this(pais.id, pais.descricao);
	}
	public Pais(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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
	
	@Override
	public String toString() {
		return "{ id : " + id + ", descricao : " + descricao + " }";
	}
}
