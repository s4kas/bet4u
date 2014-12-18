package com.bmp.bet4u.common.resultado.jogo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.common.exc.SemDados;

public class JdbcJogoDAO implements IJogoDAO {
	
	private final static StringBuffer SELECT_JOGO = 
			new StringBuffer("SELECT dataJogo, Equipa_idEquipaCasa, Equipa_idEquipaFora ").
			append("FROM Jogo WHERE dataJogo = ? ").
			append("AND Equipa_idEquipaCasa = ? ").
			append("AND Equipa_idEquipaFora = ?");
	
	private final static StringBuffer INSERT_JOGO = 
			new StringBuffer("INSERT INTO Jogo ").
			append("(dataJogo, Equipa_idEquipaCasa, Equipa_idEquipaFora) ").
			append("values (?, ?, ?)");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Jogo getJogoByDataByEquipas(Jogo jogo) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Jogo> jogos = jdbcTemplate.query(SELECT_JOGO.toString(), 
				new Object[] {jogo.getDataJogoSQL(),jogo.getEquipaCasa().getId(), 
				jogo.getEquipaFora().getId()}, new JogoRowMapper());
		
		if (jogos.isEmpty()) {
			throw new SemDados();
		}
		
		return jogos.get(0);
	}
	
	public void insertJogo(Jogo jogo) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(INSERT_JOGO.toString(), new Object[] {jogo.getDataJogoSQL(),
			jogo.getEquipaCasa().getId(), jogo.getEquipaFora().getId()});
	}
}
