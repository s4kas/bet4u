package com.bmp.bet4u.common.modalidade;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bmp.bet4u.common.exc.SemDados;

public class JdbcModalidadeDAO implements IModalidadeDAO {

	private static final StringBuffer SELECT_TODAS_MODALIDADE = 
			new StringBuffer("SELECT m.idModalidade, m.descricao ").
			append("FROM Modalidade m ");
	
	private static final StringBuffer SELECT_MODALIDADE_BY_ID = 
			new StringBuffer("SELECT m.idModalidade, m.descricao ").
			append("FROM Modalidade m ").
			append("WHERE m.idModalidade = ?");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Modalidade> getListaModalidades() throws SemDados {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Modalidade> modalidades = jdbcTemplate.query(SELECT_TODAS_MODALIDADE.toString(),
				new ModalidadeRowMapper());
		
		if (modalidades.isEmpty()) {
			throw new SemDados();
		}

		return modalidades;
		
	}

	public Modalidade getModalidadeById(String idModalidade) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Modalidade> modalidades = jdbcTemplate.query(SELECT_MODALIDADE_BY_ID.toString(),
				new Object[]{idModalidade},
				new ModalidadeRowMapper());
		
		if (modalidades.isEmpty()) {
			throw new SemDados();
		}

		return modalidades.get(0);
	}

}
