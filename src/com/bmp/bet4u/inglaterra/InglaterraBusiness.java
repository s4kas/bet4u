package com.bmp.bet4u.inglaterra;

import java.util.List;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.competicao.CompeticaoBusiness;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.epoca.EpocaBusiness;
import com.bmp.bet4u.common.exc.Geral;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.modalidade.Modalidade;
import com.bmp.bet4u.common.modalidade.ModalidadeBusiness;
import com.bmp.bet4u.common.pais.Pais;
import com.bmp.bet4u.common.pais.PaisBusiness;
import com.bmp.bet4u.common.parser.ContextoCompeticao;
import com.bmp.bet4u.common.parser.ParserCommon;
import com.bmp.bet4u.inglaterra.parser.PremierLeague;

public class InglaterraBusiness {
	
	public static final String PREMIER_LEAGUE = "4";
	private static final String INGLATERRA = "EN";
	
	public static void parseAndInsertTodasEpocasPremierLeague() {
		//Primeira Liga Portuguesa de Futebol
		Competicao competicao;
		try {
			competicao = getPremierLeague();
		} catch (SemDados e) {
			return;
		}
				
		//Parser da premier League
		ContextoCompeticao premierLeague = new ContextoCompeticao(new PremierLeague());
				
		//Lista de epocas da premier league
		List<Epoca> epocasDaPremierLeague;
		epocasDaPremierLeague = EpocaBusiness.getEpocasDaCompeticao(competicao);
				
		//iterar sobre todas as jornadas de todas as epocas
		for (Epoca epoca : epocasDaPremierLeague) {
			try {
				if (!epoca.isTerminada()) {
					ParserCommon.parseAndInsertEpoca(competicao, epoca, premierLeague,
							(epoca.getNumeroEquipas() - 1) * 2);
				}
			} catch (Geral e) {
				System.out.println(e.getCodErro());
			}
		}
	}
	
	private static Competicao getPremierLeague() throws SemDados {
		Pais inglaterra = PaisBusiness.getPaisById(INGLATERRA);
		Modalidade futebol = ModalidadeBusiness.getFutebol();
		
		Competicao competicao = CompeticaoBusiness.getCompeticaoByCompeticaoByPaisByModalidade(
				PREMIER_LEAGUE, inglaterra.getId(), String.valueOf(futebol.getId()));
		competicao.setPais(inglaterra);
		competicao.setModalidade(futebol);
		return competicao;
	}
}
