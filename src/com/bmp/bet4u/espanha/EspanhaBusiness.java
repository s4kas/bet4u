package com.bmp.bet4u.espanha;

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
import com.bmp.bet4u.espanha.parser.LaLiga;

public class EspanhaBusiness {

	public static final String LALIGA = "5";
	public static final String ESPANHA = "ES";
	
	public static void parseAndInsertTodasEpocasLaLiga() {
		Competicao competicao;
		try {
			competicao = getLaLiga();
		} catch (SemDados e) {
			return;
		}
				
		ContextoCompeticao laliga = new ContextoCompeticao(new LaLiga());
				
		List<Epoca> epocas = EpocaBusiness.getEpocasDaCompeticao(competicao);
				
		//iterar sobre todas as jornadas de todas as epocas
		for (Epoca epoca : epocas) {
			try {
				if (!epoca.isTerminada()) {
					ParserCommon.parseAndInsertEpoca(competicao, epoca, laliga,
							(epoca.getNumeroEquipas() - 1) * 2);
				}
			} catch (Geral e) {
				System.out.println(e.getCodErro());
			}
		}
	}
	
	
	
	private static Competicao getLaLiga() throws SemDados {
		Pais pais = PaisBusiness.getPaisById(ESPANHA);
		Modalidade futebol = ModalidadeBusiness.getFutebol();
		
		Competicao competicao = CompeticaoBusiness.getCompeticaoByCompeticaoByPaisByModalidade(
				LALIGA, pais.getId(), String.valueOf(futebol.getId()));
		competicao.setPais(pais);
		competicao.setModalidade(futebol);
		return competicao;
	}
	
}
