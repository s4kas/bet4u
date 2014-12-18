package com.bmp.bet4u.suecia;

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
import com.bmp.bet4u.suecia.parser.Allsvenskan;

public class SueciaBusiness {

	public static final String ALLSVENSKAN = "3";
	public static final String SUECIA = "SE";
	
	public static void parseAndInsertTodasEpocasAllsvenskan() {
		Competicao competicao;
		try {
			competicao = getAllsvenskan();
		} catch (SemDados e) {
			return;
		}
				
		ContextoCompeticao primeiraLiga = new ContextoCompeticao(new Allsvenskan());
				
		List<Epoca> epocas = EpocaBusiness.getEpocasDaCompeticao(competicao);
				
		//iterar sobre todas as jornadas de todas as epocas
		for (Epoca epoca : epocas) {
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
	
	
	
	private static Competicao getAllsvenskan() throws SemDados {
		Pais suecia = PaisBusiness.getPaisById(SUECIA);
		Modalidade futebol = ModalidadeBusiness.getFutebol();
		
		Competicao competicao = CompeticaoBusiness.getCompeticaoByCompeticaoByPaisByModalidade(
				ALLSVENSKAN, suecia.getId(), String.valueOf(futebol.getId()));
		competicao.setPais(suecia);
		competicao.setModalidade(futebol);
		return competicao;
	}
	
}
