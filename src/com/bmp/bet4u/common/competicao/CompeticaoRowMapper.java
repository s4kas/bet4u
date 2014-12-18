package com.bmp.bet4u.common.competicao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.common.modalidade.Modalidade;
import com.bmp.bet4u.common.pais.Pais;

public class CompeticaoRowMapper implements RowMapper<Competicao> {

	public Competicao mapRow(ResultSet rs, int rowNum) throws SQLException {
		Competicao competicao = new Competicao();
		competicao.setId(rs.getInt("c.idCompeticao"));
		competicao.setDescricao(rs.getString("c.descricao"));
		
		String idPais = rs.getString("c.Pais_idPais");
		String descPais = rs.getString("p.descricao");
		competicao.setPais(new Pais(idPais, descPais));
		
		int idModalidade = rs.getInt("c.Modalidade_idModalidade");
		String descModalidade = rs.getString("m.descricao");
		competicao.setModalidade(new Modalidade(idModalidade, descModalidade));
		
		return competicao;
	}

}
