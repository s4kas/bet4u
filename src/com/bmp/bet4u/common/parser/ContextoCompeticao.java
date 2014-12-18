package com.bmp.bet4u.common.parser;

import com.bmp.bet4u.common.exc.Geral;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;

public class ContextoCompeticao {
	
	private ICompeticaoParser parser;
	
	public ContextoCompeticao(ICompeticaoParser parser) {
		this.parser = parser;
	}
	
	public void executeParser (ResultadoJornadaEpocaCompeticao jornadaCompeticao) throws Geral {
		this.parser.execute(jornadaCompeticao);
	}
}
