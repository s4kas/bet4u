package com.bmp.bet4u.client.estatisticas.beans;

public class Jogo {
	
	private Equipa equipaCasa;
	private Equipa equipaFora;
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
	
}
