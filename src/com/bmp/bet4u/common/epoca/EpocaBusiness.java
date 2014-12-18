package com.bmp.bet4u.common.epoca;

import java.util.ArrayList;
import java.util.List;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.exc.SemDados;

public class EpocaBusiness {
	
	public static List<Epoca> getEpocasDaCompeticao(Competicao competicao) {
		//TODO INJECT
		IEpocaDAO epocaDAO = new JdbcEpocaDAO();
		epocaDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return epocaDAO.getListaEpocasByCompeticao(competicao);
		} catch (SemDados e) {
			return new ArrayList<Epoca>();
		}
	}

	public static void updateEpoca(Competicao competicao, Epoca epoca) throws SemDados {
		//TODO INJECT
		IEpocaDAO epocaDAO = new JdbcEpocaDAO();
		epocaDAO.setDataSource(CommonConnection.getDataSource());
		
		epocaDAO.updateEpoca(competicao, epoca);
	}
	
}
