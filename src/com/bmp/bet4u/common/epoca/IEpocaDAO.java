package com.bmp.bet4u.common.epoca;

import java.util.List;
import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.exc.SemDados;

public interface IEpocaDAO extends IDataAccessObject {

	public List<Epoca> getListaEpocasByCompeticao(Competicao competicao) throws SemDados;
	public void updateEpoca(Competicao competicao, Epoca epoca);
	
}
