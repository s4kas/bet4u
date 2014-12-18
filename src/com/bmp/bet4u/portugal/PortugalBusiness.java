package com.bmp.bet4u.portugal;

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
import com.bmp.bet4u.portugal.parser.PrimeiraLiga;

public class PortugalBusiness {
	
	public static final String PRIMEIRA_LIGA = "1";
	private static final String PORTUGAL = "PT";
	
	public static void parseAndInsertTodasEpocasPrimeiraLiga() {
		//Primeira Liga Portuguesa de Futebol
		Competicao competicao;
		try {
			competicao = getPrimeiraLigaPortuguesaDeFutebol();
		} catch (SemDados e) {
			return;
		}
				
		//Parser da primeira liga portuguesa de futebol
		ContextoCompeticao primeiraLiga = new ContextoCompeticao(new PrimeiraLiga());
				
		//Lista de epocas da primeira liga portuguesa de futebol
		List<Epoca> epocasDaPrimeiraLiga;
		epocasDaPrimeiraLiga = EpocaBusiness.getEpocasDaCompeticao(competicao);
				
		//iterar sobre todas as jornadas de todas as epocas
		for (Epoca epoca : epocasDaPrimeiraLiga) {
			try {
				if (!epoca.isTerminada()) {
					ParserCommon.parseAndInsertEpoca(competicao, epoca, primeiraLiga,
							(epoca.getNumeroEquipas() - 1) * 2);
				}
			} catch (Geral e) {
				System.out.println(e.getCodErro());
			}
		}
	}
	
	
	
	private static Competicao getPrimeiraLigaPortuguesaDeFutebol() throws SemDados {
		Pais portugal = PaisBusiness.getPaisById(PORTUGAL);
		Modalidade futebol = ModalidadeBusiness.getFutebol();
		
		Competicao competicao = CompeticaoBusiness.getCompeticaoByCompeticaoByPaisByModalidade(
				PRIMEIRA_LIGA, portugal.getId(), String.valueOf(futebol.getId()));
		competicao.setPais(portugal);
		competicao.setModalidade(futebol);
		return competicao;
	}
}
