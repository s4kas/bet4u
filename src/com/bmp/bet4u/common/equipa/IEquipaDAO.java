package com.bmp.bet4u.common.equipa;

import java.util.List;

import com.bmp.bet4u.common.IDataAccessObject;
import com.bmp.bet4u.common.exc.SemDados;

public interface IEquipaDAO extends IDataAccessObject {
	
	public void insertEquipa(Equipa equipa);
	public int getIdEquipaByNome(String nome) throws SemDados;
	public List<String> getNomesEquipasComecadasPor(String termo) throws SemDados;
	
}
