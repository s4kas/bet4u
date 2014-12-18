package com.bmp.bet4u.client.estatisticas.business;

import java.util.ArrayList;
import java.util.List;
import com.bmp.bet4u.client.estatisticas.beans.Jogo;
import com.bmp.bet4u.client.estatisticas.beans.ResultadoEpocaJornada;
import com.bmp.bet4u.client.estatisticas.dao.IEstatisticasDAO;
import com.bmp.bet4u.client.estatisticas.dao.JdbcEstatisticasDAO;
import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.equipa.EquipaBusiness;
import com.bmp.bet4u.common.exc.SemDados;

public class EstatisticasBusiness {

	
	public static List<ResultadoEpocaJornada> getEstatisticasByJogoOuEquipa(Jogo jogo) {
		IEstatisticasDAO dao = new JdbcEstatisticasDAO();
		dao.setDataSource(CommonConnection.getDataSource());
		
		List<ResultadoEpocaJornada> resultados = new ArrayList<ResultadoEpocaJornada>();
		
		String nomeEquipaCasa = jogo.getEquipaCasa().getNome();
		int idEquipaCasa = 0;
		if (!nomeEquipaCasa.equals("")) {
			idEquipaCasa = EquipaBusiness.getIdEquipaByNome(nomeEquipaCasa);
			
		}
		
		String nomeEquipaFora = jogo.getEquipaFora().getNome();
		int idEquipaFora = 0;
		if (!nomeEquipaFora.equals("")) {
			idEquipaFora = EquipaBusiness.getIdEquipaByNome(nomeEquipaFora);
		}
		
		if (idEquipaCasa != 0 && idEquipaFora !=0) {
			setResultadosVersus(dao, resultados, idEquipaCasa, idEquipaFora, jogo);
		}
		
		if (idEquipaCasa != 0) {
			//setResultadosEquipa(dao, resultados, idEquipaCasa, jogo);
		}
		
		if (idEquipaFora !=0) {
			//TODO
		}
		
		return resultados;
	}

	private static void setResultadosEquipa(IEstatisticasDAO dao,
			List<ResultadoEpocaJornada> resultados, int idEquipa, Jogo jogo) {
		try {
			List<ResultadoEpocaJornada> resultadosVersus = 
					dao.getUltimosDezResultadosEquipa(idEquipa);
			resultados.addAll(resultadosVersus);
			
			for (ResultadoEpocaJornada resultado : resultados) {
				resultado.setOcorrencias(dao.getOcorrencias(resultado.getJogo()));
				resultado.setPesquisaInicial(jogo);
			}
			
		} catch (SemDados e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setResultadosVersus(IEstatisticasDAO dao, List<ResultadoEpocaJornada> resultados, 
			int idEquipaCasa, int idEquipaFora, Jogo jogo) {
		try {
			List<ResultadoEpocaJornada> resultadosVersus = 
					dao.getResultadosEntreEquipas(idEquipaCasa, idEquipaFora);
			resultados.addAll(resultadosVersus);
			
			for (ResultadoEpocaJornada resultado : resultados) {
				resultado.setOcorrencias(dao.getOcorrencias(resultado.getJogo()));
				resultado.setPesquisaInicial(jogo);
			}
			
		} catch (SemDados e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
