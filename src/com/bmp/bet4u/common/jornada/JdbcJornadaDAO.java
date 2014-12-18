package com.bmp.bet4u.common.jornada;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.SemDados;

public class JdbcJornadaDAO implements IJornadaDAO {
	
	private static final StringBuffer SELECT_JORNADA_POR_COMPETICAO_E_NUMERO = 
			new StringBuffer("SELECT numJornada, descricao, jornadaTerminada ").
			append("FROM Competicao_ocorre_Epoca_tem_Jornada, Jornada ").
			append("WHERE Jornada_numJornada = numJornada ").
			append("AND Competicao_ocorre_Epoca_Competicao_idCompeticao = ? ").
			append("AND Competicao_ocorre_Epoca_Competicao_Pais_idPais = ? ").
			append("AND Competicao_ocorre_Epoca_Competicao_Modalidade_idModalidade = ? ").
			append("AND Competicao_ocorre_Epoca_Epoca_anoInicio = ? ").
			append("AND Competicao_ocorre_Epoca_Epoca_anoFim = ? ").
			append("AND numJornada = ? ");
	
	private static final StringBuffer SELECT_JORNADA_BY_NUMERO =
			new StringBuffer("SELECT numJornada, descricao FROM Jornada ").
			append("WHERE numJornada = ?");
	
	private static final StringBuffer INSERT_JORNADA = 
			new StringBuffer("INSERT INTO Jornada (numJornada, descricao) ").
			append("values (?, ?)");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Jornada getJornadaByCompeticaoByNumero(Epoca epoca, Competicao competicao, 
			int numeroJornada) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String idCompeticao = String.valueOf(competicao.getId());
		String idPais = competicao.getPais().getId();
		String idModalidade = String.valueOf(competicao.getModalidade().getId());
		String anoInicio = String.valueOf(epoca.getAnoInicio());
		String anoFim = String.valueOf(epoca.getAnoFim());
		String nJornada = String.valueOf(numeroJornada);
		
		List<Jornada> jornadas = jdbcTemplate.query(SELECT_JORNADA_POR_COMPETICAO_E_NUMERO.toString(), 
				new Object[] {idCompeticao, idPais, idModalidade, anoInicio, anoFim, nJornada},
				new JornadaCompeticaoRowMapper());
		
		if (jornadas.isEmpty()) {
			throw new SemDados();
		}
		
		return jornadas.get(0);
	}

	public void insertJornada(Jornada jornada) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(INSERT_JORNADA.toString(), new Object[] {
			jornada.getNumero(), jornada.getDescricao()});
	}

	public Jornada getJornadaByNumero(int numero) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Jornada> jornadas = jdbcTemplate.query(SELECT_JORNADA_BY_NUMERO.toString(), 
				new Object[] {numero},
				new JornadaRowMapper());
		
		if (jornadas.isEmpty()) {
			throw new SemDados();
		}
		
		return jornadas.get(0);
	}
	
}
