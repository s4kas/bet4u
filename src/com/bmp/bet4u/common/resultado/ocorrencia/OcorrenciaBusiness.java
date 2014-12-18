package com.bmp.bet4u.common.resultado.ocorrencia;

import java.util.HashMap;
import java.util.Map;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.equipa.Equipa;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.modalidade.ModalidadeBusiness;

public class OcorrenciaBusiness {

	public static final String GOLO_FUTEBOL = "1";
	
	private static Map<String, Ocorrencia> ocorrenciasCache = new HashMap<String, Ocorrencia>();
	
	public static final OcorrenciaResultado getGoloFutebol(Equipa equipa,
			int minuto) {
		IOcorrenciaDAO ocorrenciaDAO = new JdbcOcorrenciaDAO();
		ocorrenciaDAO.setDataSource(CommonConnection.getDataSource());
		
		Ocorrencia goloFutebol = ocorrenciasCache.get(GOLO_FUTEBOL);
		
		if (goloFutebol == null) {
			try {goloFutebol = ocorrenciaDAO.getOcorrenciaById(GOLO_FUTEBOL);} 
			catch (SemDados e1) {/*TODO EXC*/}
			try {goloFutebol.setModalidade(ModalidadeBusiness.getFutebol());} 
			catch (SemDados e) {/*TODO EXC*/}
			ocorrenciasCache.put(GOLO_FUTEBOL, goloFutebol);
		}
		
		OcorrenciaResultado or = new OcorrenciaResultado(goloFutebol);
		or.setEquipaOcorrencia(equipa);
		or.setMinutoOcorrencia(minuto);
		return or;
	}

}
