package com.bmp.bet4u.common.competicao;

import java.util.ArrayList;
import java.util.List;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.pais.Pais;

public class CompeticaoBusiness {
	
	
	
	public static List<Competicao> getListaCompeticoesByPais(Pais pais) {
		ICompeticaoDAO competicaoDAO = new JdbcCompeticaoDAO();
		competicaoDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return competicaoDAO.getListaCompeticoesByPais(pais);
		} catch (SemDados e) {
			return new ArrayList<Competicao>();
		}
	}

	public static Competicao getCompeticaoByCompeticaoByPaisByModalidade(
			String competicao, String pais, String modalidade) {
		ICompeticaoDAO competicaoDAO = new JdbcCompeticaoDAO();
		competicaoDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return competicaoDAO.getCompeticaoByCompeticaoByPaisByModalidade(competicao, pais, modalidade);
		} catch (SemDados e) {
			return new Competicao();
		}
	}
}
