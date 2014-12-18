package com.bmp.bet4u.common.resultado.jogo;

import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;

public interface IJogoDAO extends IDataAccessObject {
	
	public Jogo getJogoByDataByEquipas(Jogo jogo) throws SemDados;
	public void insertJogo(Jogo jogo);
	
}
