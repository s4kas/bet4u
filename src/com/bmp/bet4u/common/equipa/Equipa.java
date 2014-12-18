package com.bmp.bet4u.common.equipa;

import java.io.Serializable;

public class Equipa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	
	public Equipa() {}
	
	public Equipa(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Equipa(int id) {
		super();
		this.id = id;
	}

	public Equipa(String nome) {
		super();
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Equipa [id=" + id + ", nome=" + nome + "]";
	}
	
}
