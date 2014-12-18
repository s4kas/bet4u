package com.bmp.bet4u.common.resultado;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.common.equipa.Equipa;

public class ResultadoRowMapper implements RowMapper<Resultado> {

	public Resultado mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resultado resultado = new Resultado();
		resultado.getJogo().setDataJogoSQL(rs.getString("Jogo_dataJogo"));
		resultado.getJogo().setEquipaCasa(new Equipa(rs.getInt("Jogo_Equipa_idEquipaCasa")));
		resultado.getJogo().setEquipaFora(new Equipa(rs.getInt("Jogo_Equipa_idEquipaFora")));
		resultado.setPontosCasa(rs.getInt("totalPontosCasa"));
		resultado.setPontosFora(rs.getInt("totalPontosFora"));
		return resultado;
	}

}
