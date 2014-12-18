package com.bmp.bet4u.common.competicao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.modalidade.Modalidade;
import com.bmp.bet4u.common.pais.Pais;

public class JdbcCompeticaoDAO implements ICompeticaoDAO {
	
	private static final StringBuffer SELECT_COMPETICOES_BY_PAIS = 
			new StringBuffer("SELECT c.idCompeticao, c.descricao, c.Pais_idPais, ").
			append("c.Modalidade_idModalidade, p.descricao, m.descricao ").
			append("FROM Competicao c, Pais p, Modalidade m ").
			append("WHERE c.Pais_idPais = p.idPais ").
			append("AND c.Modalidade_idModalidade = m.idModalidade ").
			append("AND c.Pais_idPais = ? ");
	
	private static final StringBuffer SELECT_COMPETICOES_BY_PAIS_BY_MODALIDADE = 
			new StringBuffer("SELECT c.idCompeticao, c.descricao, c.Pais_idPais, ").
			append("c.Modalidade_idModalidade, p.descricao, m.descricao ").
			append("FROM Competicao c, Pais p, Modalidade m ").
			append("WHERE c.Pais_idPais = p.idPais ").
			append("AND c.Modalidade_idModalidade = m.idModalidade ").
			append("AND c.Pais_idPais = ? ").
			append("AND c.Modalidade_idModalidade = ?");
	
	private static final StringBuffer SELECT_COMPETICAO_BY_COMPETICAO_BY_PAIS_BY_MODALIDADE = 
			new StringBuffer("SELECT c.idCompeticao, c.descricao, c.Pais_idPais, ").
			append("c.Modalidade_idModalidade, p.descricao, m.descricao ").
			append("FROM Competicao c, Pais p, Modalidade m ").
			append("WHERE c.Pais_idPais = p.idPais ").
			append("AND c.Modalidade_idModalidade = m.idModalidade ").
			append("AND c.idCompeticao = ? ").
			append("AND c.Pais_idPais = ? ").
			append("AND c.Modalidade_idModalidade = ?");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Competicao> getListaCompeticoesByPaisByModalidade(Pais pais,
			Modalidade modalidade) throws SemDados {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Competicao> competicoes = jdbcTemplate.query(
				SELECT_COMPETICOES_BY_PAIS_BY_MODALIDADE.toString(),
				new Object[]{pais.getId(), modalidade.getId()},
				new CompeticaoRowMapper());
		
		if (competicoes.isEmpty()) {
			throw new SemDados();
		}

		return competicoes;
	}

	public Competicao getCompeticaoByCompeticaoByPaisByModalidade(
			String idCompeticao, String codPais, String codModalidade)
			throws SemDados {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Competicao> competicoes = jdbcTemplate.query(
				SELECT_COMPETICAO_BY_COMPETICAO_BY_PAIS_BY_MODALIDADE.toString(),
				new Object[]{idCompeticao, codPais, codModalidade},
				new CompeticaoRowMapper());
		
		if (competicoes.isEmpty()) {
			throw new SemDados();
		}

		return competicoes.get(0);
	}

	@Override
	public List<Competicao> getListaCompeticoesByPais(Pais pais)
			throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Competicao> competicoes = jdbcTemplate.query(
				SELECT_COMPETICOES_BY_PAIS.toString(),
				new Object[]{pais.getId()},
				new CompeticaoRowMapper());
		
		if (competicoes.isEmpty()) {
			throw new SemDados();
		}

		return competicoes;
	}

}
