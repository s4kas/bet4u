package com.bmp.bet4u.common.pais;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bmp.bet4u.common.exc.SemDados;

public class JdbcPaisDAO implements IPaisDAO {
	
	private static final StringBuffer SELECT_TODOS_PAISES = 
			new StringBuffer("SELECT p.idPais, p.descricao ").
			append("FROM Pais p ");
	
	private static final StringBuffer SELECT_PAIS_BY_CODPAIS = 
			new StringBuffer("SELECT p.idPais, p.descricao ").
			append("FROM Pais p ").append("WHERE p.idPais = ?");

	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Pais> getListaPaises() throws SemDados {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Pais> paises = jdbcTemplate.query(SELECT_TODOS_PAISES.toString(),
				new PaisRowMapper());
		
		if (paises.isEmpty()) {
			throw new SemDados();
		}
		
		return paises;
	}

	public Pais getPaisByCodigoPais(String codigoPais) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Pais> paises = jdbcTemplate.query(SELECT_PAIS_BY_CODPAIS.toString(),
				new Object[]{codigoPais},
				new PaisRowMapper());
		
		if (paises.isEmpty()) {
			throw new SemDados();
		}
		
		return paises.get(0);
	}

}
