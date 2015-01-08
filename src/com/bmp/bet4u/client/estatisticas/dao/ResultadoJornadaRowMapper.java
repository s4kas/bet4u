package com.bmp.bet4u.client.estatisticas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoJornada;

public class ResultadoJornadaRowMapper implements RowMapper<ResultadoJornada> {

	@Override
	public ResultadoJornada mapRow(ResultSet rs, int arg1)
			throws SQLException {
		ResultadoJornada rj = new ResultadoJornada();
		
		rj.setEpoca(rs.getString("epoca"));
		rj.setJornada(rs.getInt("jornada"));
		rj.setEquipaCasa(rs.getInt("equipaCasa"));
		rj.setEquipaFora(rs.getInt("equipaFora"));
		rj.setGolosCasa(rs.getInt("golosCasa"));
		rj.setGolosFora(rs.getInt("golosFora"));
		
		return rj;
	}

}
