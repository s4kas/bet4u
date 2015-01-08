package com.bmp.bet4u.client.estatisticas.beans;

public class Jogo {
	
	private String epoca;
	private Integer jornada;
	private Equipa equipaCasa;
	private Equipa equipaFora;
	private Integer pontosCasa;
	private Integer pontosFora;
	public Jogo() {}
	public Jogo(Equipa casa, Equipa fora) {
		this.equipaCasa = casa;
		this.equipaFora = fora;
	}
	public Equipa getEquipaCasa() {
		return equipaCasa;
	}
	public void setEquipaCasa(Equipa equipaCasa) {
		this.equipaCasa = equipaCasa;
	}
	public Equipa getEquipaFora() {
		return equipaFora;
	}
	public void setEquipaFora(Equipa equipaFora) {
		this.equipaFora = equipaFora;
	}
	public String getEpoca() {
		return epoca;
	}
	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	public Integer getJornada() {
		return jornada;
	}
	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}
	public Integer getPontosCasa() {
		return pontosCasa;
	}
	public void setPontosCasa(Integer pontosCasa) {
		this.pontosCasa = pontosCasa;
	}
	public Integer getPontosFora() {
		return pontosFora;
	}
	public void setPontosFora(Integer pontosFora) {
		this.pontosFora = pontosFora;
	}
}
