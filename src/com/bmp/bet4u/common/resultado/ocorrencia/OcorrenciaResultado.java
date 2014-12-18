package com.bmp.bet4u.common.resultado.ocorrencia;

import com.bmp.bet4u.common.equipa.Equipa;


public class OcorrenciaResultado {

	private Ocorrencia ocorrencia;
	private int minutoOcorrencia;
	private Equipa equipaOcorrencia;
	
	public OcorrenciaResultado(Ocorrencia oc) {
		this.ocorrencia = oc;
	}
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	public int getMinutoOcorrencia() {
		return minutoOcorrencia;
	}
	public void setMinutoOcorrencia(int minutoOcorrencia) {
		this.minutoOcorrencia = minutoOcorrencia;
	}
	public Equipa getEquipaOcorrencia() {
		return equipaOcorrencia;
	}
	public void setEquipaOcorrencia(Equipa equipaOcorrencia) {
		this.equipaOcorrencia = equipaOcorrencia;
	}
	
}
