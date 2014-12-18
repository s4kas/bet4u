package com.bmp.bet4u.common.modalidade;

import java.util.List;
import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;

public interface IModalidadeDAO extends IDataAccessObject {

	public List<Modalidade> getListaModalidades() throws SemDados;
	public Modalidade getModalidadeById(String idModalidade) throws SemDados;
}
