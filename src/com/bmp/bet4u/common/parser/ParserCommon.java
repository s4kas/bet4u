package com.bmp.bet4u.common.parser;

import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.epoca.EpocaBusiness;
import com.bmp.bet4u.common.exc.Geral;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.jornada.Jornada;
import com.bmp.bet4u.common.jornada.JornadaBusiness;
import com.bmp.bet4u.common.resultado.ResultadoBusiness;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;

public class ParserCommon {

	public static void parseAndInsertEpoca(Competicao competicao, Epoca epoca, 
			ContextoCompeticao ctx, int numeroJornadas) throws Geral {
		boolean todasAsJornadasCompletas = true;
		boolean jornadaIniciada = true;
		for (int i = 1; i <= numeroJornadas && jornadaIniciada; i ++) {
			Jornada jornada;
			boolean semDados = false;
			try {
				jornada = JornadaBusiness.getJornadaByCompeticaoByNumero(epoca, competicao, i);
			} catch (SemDados sd) {
				semDados = true;
				jornada = new Jornada(i);
			}
				
			if (semDados || !jornada.isTerminada()) {
				todasAsJornadasCompletas = false;
				ResultadoJornadaEpocaCompeticao resultadosJornada = 
					new ResultadoJornadaEpocaCompeticao(jornada, epoca, competicao);
				
				ctx.executeParser(resultadosJornada);
				
				if (resultadosJornada.getResultados().isEmpty()) {
					jornadaIniciada = false;
				}
				
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new Geral(e.getMessage());
				}

				ResultadoBusiness.insertResultadosDaJornada(resultadosJornada);
			}
		}
			
		epoca.setTerminada(todasAsJornadasCompletas);
		//marcar epoca como terminada
		try {
			EpocaBusiness.updateEpoca(competicao, epoca);
		} catch (SemDados e) {
			return;
		}
	}
	
}
