package com.bmp.bet4u.common.parser;

import com.bmp.bet4u.common.exc.Geral;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;

public interface ICompeticaoParser {

	void execute(ResultadoJornadaEpocaCompeticao jornadaCompeticao) throws Geral;
	
}
