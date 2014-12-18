package com.bmp.bet4u.common.competicao;

import java.util.List;
import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.modalidade.Modalidade;
import com.bmp.bet4u.common.pais.Pais;

public interface ICompeticaoDAO extends IDataAccessObject {

	public List<Competicao> getListaCompeticoesByPaisByModalidade(Pais pais,
			Modalidade modalidade) throws SemDados;
	public Competicao getCompeticaoByCompeticaoByPaisByModalidade(String idCompeticao,
			String codPais, String codModalidade) throws SemDados;
	public List<Competicao> getListaCompeticoesByPais(Pais pais) throws SemDados;
}
