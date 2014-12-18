package com.bmp.bet4u.common.resultado.ocorrencia;

import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;

public interface IOcorrenciaDAO extends IDataAccessObject {

	public Ocorrencia getOcorrenciaById(String idOcorrencia) throws SemDados;
	
}
