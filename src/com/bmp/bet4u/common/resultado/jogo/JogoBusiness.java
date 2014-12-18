package com.bmp.bet4u.common.resultado.jogo;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.exc.SemDados;

public class JogoBusiness {

	public static void insertJogo(Jogo jogo) {
		IJogoDAO jogoDAO = new JdbcJogoDAO();
		jogoDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			jogoDAO.getJogoByDataByEquipas(jogo);
		} catch (SemDados e) {
			jogoDAO.insertJogo(jogo);
		}
	}

}
