package com.bmp.bet4u.common.resultado.jogo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.common.equipa.Equipa;

public class JogoRowMapper implements RowMapper<Jogo> {

	public Jogo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Jogo jogo = new Jogo();
		jogo.setDataJogoSQL(rs.getString("dataJogo"));
		jogo.setEquipaCasa(new Equipa(rs.getInt("Equipa_idEquipaCasa")));
		jogo.setEquipaFora(new Equipa(rs.getInt("Equipa_idEquipaFora")));
		return jogo;
	}

}
