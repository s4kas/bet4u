package com.bmp.bet4u.portugal.parser;

import java.io.IOException;
import java.text.ParseException;

import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.Geral;
import com.bmp.bet4u.common.parser.FootballLineupsParser;
import com.bmp.bet4u.common.parser.ICompeticaoParser;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;

public class PrimeiraLiga implements ICompeticaoParser {
	
	public void execute(ResultadoJornadaEpocaCompeticao jornadaCompeticao) throws Geral {
		
		try {
			String competicao = jornadaCompeticao.getCompeticao().getDescricao();
			competicao = competicao.replace(" ", "_");
			
			Epoca epoca = jornadaCompeticao.getEpoca();
			StringBuffer epocaSb = new StringBuffer(competicao).
					append("_").
					append(epoca.getAnoInicio()).
					append("-").
					append(epoca.getAnoFim());
			
			FootballLineupsParser.parse(jornadaCompeticao, epocaSb.toString());
		} catch (IOException e) {
			//TODO EXC
			throw new Geral(e.getMessage());
		} catch (ParseException e) {
			//TODO EXC
			throw new Geral(e.getMessage());
		}
	}

}