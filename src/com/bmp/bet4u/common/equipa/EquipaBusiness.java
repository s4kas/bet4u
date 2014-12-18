package com.bmp.bet4u.common.equipa;

import java.util.ArrayList;
import java.util.List;

import com.bmp.bet4u.common.CommonConnection;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.resultado.jogo.Jogo;

public class EquipaBusiness {

	public static void insertEquipas(Jogo jogo) {
		IEquipaDAO equipaDAO = new JdbcEquipaDAO();
		equipaDAO.setDataSource(CommonConnection.getDataSource());
		
		//equipa da casa
		try {
			int id = equipaDAO.getIdEquipaByNome(jogo.getEquipaCasa().getNome());
			jogo.getEquipaCasa().setId(id);
		} catch (SemDados e) {
			equipaDAO.insertEquipa(jogo.getEquipaCasa());
		}
		
		//equipa visitante
		try {
			int id = equipaDAO.getIdEquipaByNome(jogo.getEquipaFora().getNome());
			jogo.getEquipaFora().setId(id);
		} catch (SemDados e) {
			equipaDAO.insertEquipa(jogo.getEquipaFora());
		}
	}
	
	public static List<String> getNomesEquipasComecadasPor(String termo) {
		IEquipaDAO equipaDAO = new JdbcEquipaDAO();
		equipaDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return equipaDAO.getNomesEquipasComecadasPor(termo);
		} catch (SemDados e) {
			return new ArrayList<String>();
		}
	}
	
	public static int getIdEquipaByNome(String nome) {
		IEquipaDAO equipaDAO = new JdbcEquipaDAO();
		equipaDAO.setDataSource(CommonConnection.getDataSource());
		
		try {
			return equipaDAO.getIdEquipaByNome(nome);
		} catch (SemDados e) {
			e.printStackTrace();
			return 0;
		}
	}

}
