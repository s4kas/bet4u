package com.bmp.bet4u.common.resultado.ocorrencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OcorrenciaRowMapper implements RowMapper<Ocorrencia> {

	public Ocorrencia mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ocorrencia oc = new Ocorrencia();
		oc.setId(rs.getString("id"));
		oc.setDescricao(rs.getString("descricao"));
		return oc;
	}

}
