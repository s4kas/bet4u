package com.bmp.bet4u.common.jornada;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.SemDados;

public class JornadaBusiness {
	
	public static Jornada getJornadaByCompeticaoByNumero(Epoca epoca, Competicao competicao, 
			int numeroJornada) throws SemDados {
		//TODO INJECT
		IJornadaDAO jornadaDAO = new JdbcJornadaDAO();
		jornadaDAO.setDataSource(CommonConnection.getDataSource());
		
		return jornadaDAO.getJornadaByCompeticaoByNumero(epoca, competicao, numeroJornada);
	}
	
	public static void insertJornada(Jornada jornada) {
		//TODO INJECT
		IJornadaDAO jornadaDAO = new JdbcJornadaDAO();
		jornadaDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			jornadaDAO.getJornadaByNumero(jornada.getNumero());
		} catch (SemDados e) {
			jornadaDAO.insertJornada(jornada);
		}
	}
}
