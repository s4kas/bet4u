package com.bmp.bet4u.common.resultado;

import java.util.ArrayList;
import java.util.List;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.jornada.Jornada;

public class ResultadoJornadaEpocaCompeticao {
	
	private List<Resultado> resultados;
	private Jornada jornada;
	private Epoca epoca;
	private Competicao competicao;
	
	public ResultadoJornadaEpocaCompeticao(Jornada jornada, 
			Epoca epoca, Competicao competicao) {
		this.jornada = jornada;
		this.epoca = epoca;
		this.competicao = competicao;
		this.resultados = new ArrayList<Resultado>();
	}
	
	public Jornada getJornada() {
		return jornada;
	}
	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}
	public List<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
	public Epoca getEpoca() {
		return epoca;
	}
	public void setEpoca(Epoca epoca) {
		this.epoca = epoca;
	}
	public Competicao getCompeticao() {
		return competicao;
	}
	public void setCompeticao(Competicao competicao) {
		this.competicao = competicao;
	}

	@Override
	public String toString() {
		return "ResultadoJornadaEpocaCompeticao [resultados=" + resultados
				+ ", jornada=" + jornada + ", epoca=" + epoca + ", competicao="
				+ competicao + "]";
	}
}
