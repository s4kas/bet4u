package com.bmp.bet4u.common.jornada;

import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.SemDados;

public interface IJornadaDAO extends IDataAccessObject {

	public Jornada getJornadaByCompeticaoByNumero(Epoca epoca, Competicao competicao, 
			int numeroJornada) throws SemDados;
	public Jornada getJornadaByNumero(int numero) throws SemDados;
	public void insertJornada(Jornada jornada);
	
}
