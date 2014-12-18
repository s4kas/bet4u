package com.bmp.bet4u.holanda;

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
import com.bmp.bet4u.holanda.parser.Eredivisie;

public class HolandaBusiness {
	
	private static final String EREDIVISIE = "2";
	private static final String HOLANDA = "NL";
	
	public static void parseAndInsertTodasEpocasEredivisie() {
		Competicao competicao;
		try {
			competicao = getEredivisie();
		} catch (SemDados e) {
			return;
		}
		
		ContextoCompeticao eredivisie = new ContextoCompeticao(new Eredivisie());
		
		List<Epoca> epocasEredivisie = EpocaBusiness.getEpocasDaCompeticao(competicao);
		
		//iterar sobre todas as jornadas de todas as epocas
		for (Epoca epoca : epocasEredivisie) {
			try {
				if (!epoca.isTerminada()) {
					ParserCommon.parseAndInsertEpoca(competicao, epoca, eredivisie,
							(epoca.getNumeroEquipas() - 1) * 2);
				}
			} catch (Geral e) {
				System.out.println(e.getCodErro());
			}
		}
	}
	
	private static Competicao getEredivisie() throws SemDados {		
		Pais holanda = PaisBusiness.getPaisById(HOLANDA);
		Modalidade futebol = ModalidadeBusiness.getFutebol();
		
		Competicao competicao = CompeticaoBusiness.getCompeticaoByCompeticaoByPaisByModalidade(
				EREDIVISIE, holanda.getId(), String.valueOf(futebol.getId()));
		competicao.setPais(holanda);
		competicao.setModalidade(futebol);
		return competicao;
	}
	
}
