package com.bmp.bet4u.client.estatisticas.beans;

import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.jornada.Jornada;
import com.bmp.bet4u.common.resultado.Resultado;

public class ResultadoEpocaJornada extends Resultado {
	
	private Epoca epoca;
	private Jornada jornada;
	private Jogo pesquisaInicial;
	
	public ResultadoEpocaJornada() {}
	
	public Epoca getEpoca() {
		return epoca;
	}
	public void setEpoca(Epoca epoca) {
		this.epoca = epoca;
	}
	public Jornada getJornada() {
		return jornada;
	}
	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}
	public Jogo getPesquisaInicial() {
		return pesquisaInicial;
	}
	public void setPesquisaInicial(Jogo pesquisaInicial) {
		this.pesquisaInicial = pesquisaInicial;
	}
}
