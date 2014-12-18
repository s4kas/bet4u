package com.bmp.bet4u.common.pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PaisRowMapper implements RowMapper<Pais> {

	public Pais mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pais pais = new Pais();
		pais.setId(rs.getString("p.idPais"));
		pais.setDescricao(rs.getString("p.descricao"));
		return pais;
	}

}
