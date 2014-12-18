package com.bmp.bet4u.common.resultado;

import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.jornada.Jornada;

public interface IResultadoDAO extends IDataAccessObject {
	
	Resultado getResultadoByDataByEquipas(Resultado resultado) throws SemDados;
	void insertResultado(Resultado resultado);
	void insertOcorrenciasDoResultado(Resultado resultado);
	boolean existeResultadoJornadaEpocaCompeticao(Competicao competicao, 
			Epoca epoca, Resultado resultado, Jornada jornada) throws SemDados;
	void insertResultadoJornadaEpocaCompeticao(Competicao competicao, 
			Epoca epoca, Resultado resultado, Jornada jornada);
}
