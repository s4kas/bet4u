package com.bmp.bet4u.common.modalidade;

import java.util.HashMap;
import java.util.Map;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.exc.SemDados;

public class ModalidadeBusiness {
	
	public static final String FUTEBOL = "1";
	
	private static Map<String, Modalidade> modalidadesCache = new HashMap<String, Modalidade>();
	
	public static Modalidade getFutebol() throws SemDados {
		//TODO INJECT
		IModalidadeDAO modalidadeDAO = new JdbcModalidadeDAO();
		modalidadeDAO.setDataSource(CommonConnection.getDataSource());
		
		Modalidade futebol = modalidadesCache.get(FUTEBOL);
		if (futebol == null) {
			futebol = modalidadeDAO.getModalidadeById(FUTEBOL);
			modalidadesCache.put(FUTEBOL, futebol);
		}
		
		return futebol;
	}
	
}
