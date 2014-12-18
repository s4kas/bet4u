package com.bmp.bet4u.common.resultado;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.equipa.EquipaBusiness;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.jornada.JornadaBusiness;
import com.bmp.bet4u.common.resultado.jogo.JogoBusiness;

public class ResultadoBusiness {
	
	public static void insertResultadosDaJornada(ResultadoJornadaEpocaCompeticao resultadosJornada) {
		IResultadoDAO resultadoDAO = new JdbcResultadoDAO();
		resultadoDAO.setDataSource(CommonConnection.getDataSource());
		
		for (Resultado resultado : resultadosJornada.getResultados()) {
			EquipaBusiness.insertEquipas(resultado.getJogo());
			JogoBusiness.insertJogo(resultado.getJogo());
			
			try {
				resultadoDAO.getResultadoByDataByEquipas(resultado);
			} catch (SemDados e) {
				resultadoDAO.insertResultado(resultado);
			}
			
			resultadoDAO.insertOcorrenciasDoResultado(resultado);
			JornadaBusiness.insertJornada(resultadosJornada.getJornada());
			
			try {
				resultadoDAO.existeResultadoJornadaEpocaCompeticao(resultadosJornada.getCompeticao(), 
						resultadosJornada.getEpoca(), resultado, resultadosJornada.getJornada());
			} catch (SemDados e) {
				resultadoDAO.insertResultadoJornadaEpocaCompeticao(resultadosJornada.getCompeticao(), 
						resultadosJornada.getEpoca(), resultado, resultadosJornada.getJornada());
			}
		}
	}
}
