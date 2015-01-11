package com.bmp.bet4u.client.estatisticas.dao;

import java.util.List;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoJornada;
import com.bmp.bet4u.common.IDataAccessObject;

public interface ILMDAO extends IDataAccessObject {
	
	public List<ResultadoJornada> getResultados();
	
}
