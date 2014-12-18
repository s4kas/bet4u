package com.bmp.bet4u.common.epoca;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EpocaRowMapper implements RowMapper<Epoca> {

	public Epoca mapRow(ResultSet rs, int rowNum) throws SQLException {
		Epoca epoca = new Epoca();
		epoca.setAnoInicio(rs.getInt("anoInicio"));
		epoca.setAnoFim(rs.getInt("anoFim"));
		epoca.setDescricao(rs.getString("descricao"));
		epoca.setTerminada(rs.getBoolean("epocaTerminada"));
		epoca.setNumeroEquipas(rs.getInt("numeroEquipas"));
		return epoca;
	}

}
