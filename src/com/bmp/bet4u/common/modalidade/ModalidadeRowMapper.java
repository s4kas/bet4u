package com.bmp.bet4u.common.modalidade;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ModalidadeRowMapper implements RowMapper<Modalidade> {

	public Modalidade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Modalidade modalidade = new Modalidade();
		modalidade.setId(rs.getInt("m.idModalidade"));
		modalidade.setDescricao(rs.getString("m.descricao"));
		return modalidade;
	}

}
