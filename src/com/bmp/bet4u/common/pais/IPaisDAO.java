package com.bmp.bet4u.common.pais;

import java.util.List;
import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;

public interface IPaisDAO extends IDataAccessObject {

	public List<Pais> getListaPaises() throws SemDados;
	public Pais getPaisByCodigoPais(String codigoPais) throws SemDados;
	
}
