package com.bmp.bet4u.common.resultado.ocorrencia;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.common.exc.SemDados;

public class JdbcOcorrenciaDAO implements IOcorrenciaDAO {

	private static final StringBuffer SELECT_OCORRENCIA_BY_ID =
			new StringBuffer("SELECT id, descricao FROM ").
			append("Ocorrencia WHERE id = ?");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Ocorrencia getOcorrenciaById(String idOcorrencia) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Ocorrencia> ocorrencias = jdbcTemplate.query(SELECT_OCORRENCIA_BY_ID.toString(), 
				new Object[] {idOcorrencia}, new OcorrenciaRowMapper());
		
		if (ocorrencias.isEmpty()) {
			throw new SemDados();
		}
		
		return ocorrencias.get(0);
	}
}
