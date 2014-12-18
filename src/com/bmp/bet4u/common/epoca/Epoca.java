package com.bmp.bet4u.common.epoca;

public class Epoca {
	
	private int anoInicio;
	private int anoFim;
	private String descricao;
	private boolean terminada;
	private int numeroEquipas;
	
	public Epoca(){}
	public Epoca(int anoInicio, int anoFim) {
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
	}
	public Epoca (int anoInicio, int anoFim, String descricao, boolean terminada) {
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.descricao = descricao;
		this.terminada = terminada;
	}
	
	public int getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}
	public int getAnoFim() {
		return anoFim;
	}
	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
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
	public int getNumeroEquipas() {
		return numeroEquipas;
	}
	public void setNumeroEquipas(int numeroEquipas) {
		this.numeroEquipas = numeroEquipas;
	}
	
	@Override
	public String toString() {
		return "Epoca [anoInicio=" + anoInicio + ", anoFim=" + anoFim
				+ ", descricao=" + descricao + ", terminada=" + terminada + "]";
	}	
}
