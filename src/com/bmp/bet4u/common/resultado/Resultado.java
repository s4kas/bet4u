package com.bmp.bet4u.common.resultado;

import java.util.ArrayList;
import java.util.List;

import com.bmp.bet4u.common.resultado.jogo.Jogo;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaResultado;

public class Resultado {
	
	private Jogo jogo;
	private List<OcorrenciaResultado> ocorrencias;
	private int pontosCasa;
	private int pontosFora;
	
	public Resultado() {
		this.jogo = new Jogo();
		this.ocorrencias = new ArrayList<OcorrenciaResultado>();
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<OcorrenciaResultado> getOcorrencias() {
		return ocorrencias;
	}
	public void setOcorrencias(List<OcorrenciaResultado> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	public int getPontosCasa() {
		return pontosCasa;
	}
	public void setPontosCasa(int pontosCasa) {
		this.pontosCasa = pontosCasa;
	}
	public int getPontosFora() {
		return pontosFora;
	}
	public void setPontosFora(int pontosFora) {
		this.pontosFora = pontosFora;
	}

	@Override
	public String toString() {
		return "Resultado [jogo=" + jogo + ", pontosCasa=" + pontosCasa
				+ ", pontosFora=" + pontosFora + "]";
	}
	
}
