package com.bmp.bet4u.client.estatisticas.dao;

import java.util.List;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoEpocaJornada;
import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.resultado.jogo.Jogo;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaResultado;

public interface IEstatisticasDAO extends IDataAccessObject {
	
	List<ResultadoEpocaJornada> getResultadosEntreEquipas(int casa, int Fora) throws SemDados;
	List<ResultadoEpocaJornada> getUltimosDezResultadosEquipa(int equipa) throws SemDados;
	List<OcorrenciaResultado> getOcorrencias(Jogo jogo);
	
}
