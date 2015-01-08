package com.bmp.bet4u.client.estatisticas.beans;

import java.util.List;

public class Estatisticas {

	private List<ResultadoEpocaJornada> estatisticas;
	private LinearRegression lm;
	
	public List<ResultadoEpocaJornada> getEstatisticas() {
		return estatisticas;
	}
	public void setEstatisticas(List<ResultadoEpocaJornada> estatisticas) {
		this.estatisticas = estatisticas;
	}
	public LinearRegression getLm() {
		return lm;
	}
	public void setLm(LinearRegression lm) {
		this.lm = lm;
	}
	
}
