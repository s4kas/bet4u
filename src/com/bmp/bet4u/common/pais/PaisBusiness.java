package com.bmp.bet4u.common.pais;

import java.util.ArrayList;
import java.util.List;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.exc.SemDados;

public class PaisBusiness {
	
	public static Pais getPaisById(String id) throws SemDados {
		IPaisDAO paisDAO = new JdbcPaisDAO();
		paisDAO.setDataSource(CommonConnection.getDataSource());
		
		return paisDAO.getPaisByCodigoPais(id);
	}
	
	public static List<Pais> getAllPaises() {
		IPaisDAO paisDAO = new JdbcPaisDAO();
		paisDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return paisDAO.getListaPaises();
		} catch (SemDados e) {
			return new ArrayList<Pais>();
		}
	}
	
}
