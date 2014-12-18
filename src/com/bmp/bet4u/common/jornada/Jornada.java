package com.bmp.bet4u.common.jornada;

public class Jornada {
	
	private static final String DESCRICAO = "Jornada";
	
	private int numero;
	private String descricao;
	private boolean terminada;
	
	public Jornada() {}
	public Jornada(int numero) {
		this.numero = numero;
		this.descricao = DESCRICAO + " " + numero;
		this.terminada = false;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isTerminada() {
		return terminada;
	}
	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}
	@Override
	public String toString() {
		return "Jornada [numero=" + numero + ", descricao=" + descricao
				+ ", terminada=" + terminada + "]";
	}
}
