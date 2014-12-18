package com.bmp.bet4u.common.jornada;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JornadaRowMapper implements RowMapper<Jornada> {
	
	public Jornada mapRow(ResultSet rs, int rowNum) throws SQLException {
		Jornada jornada = new Jornada();
		jornada.setNumero(rs.getInt("numJornada"));
		jornada.setDescricao(rs.getString("descricao"));
	    return jornada;
	}
	
}
