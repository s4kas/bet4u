package com.bmp.bet4u.client.estatisticas.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bmp.bet4u.client.estatisticas.beans.ResultadoJornada;

public class JdbcLMDAO implements ILMDAO {
	
	StringBuffer SELECT_FROM_RESULTADOS = new StringBuffer("SELECT epoca, jornada, equipaCasa, equipaFora, ").
			append("golosCasa, golosFora ").
			append("FROM resultados WHERE equipaCasa = ? or equipaFora = ?");
	
	private DataSource dataSource;
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<ResultadoJornada> getResultadosDaEquipa(Integer equipa) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.query(SELECT_FROM_RESULTADOS.toString(), new Integer[] {equipa, equipa},
				new ResultadoJornadaRowMapper());
	}	
	
}
