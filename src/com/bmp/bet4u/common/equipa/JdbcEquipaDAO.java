package com.bmp.bet4u.common.equipa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bmp.bet4u.common.exc.SemDados;

public class JdbcEquipaDAO implements IEquipaDAO {

	private final static StringBuffer INSERT_EQUIPA = 
			new StringBuffer("INSERT INTO Equipa (nome) values (?)");
	
	private final static StringBuffer SELECT_EQUIPA = 
			new StringBuffer("SELECT idEquipa FROM Equipa WHERE nome = ?");
	
	private final static StringBuffer SELECT_EQUIPAS_BY_NOME =
			new StringBuffer("SELECT nome FROM Equipa WHERE nome LIKE ?");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insertEquipa(final Equipa equipa) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) 
		        		throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(INSERT_EQUIPA.toString(), 
		            		new String[] {"idEquipa"});
		            ps.setString(1, equipa.getNome());
		            return ps;
		        }
		    },
		keyHolder);
		
		equipa.setId(keyHolder.getKey().intValue());
	}

	public int getIdEquipaByNome(String nome) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			return jdbcTemplate.queryForObject(SELECT_EQUIPA.toString(), 
				Integer.class, new Object[] {nome});
		} catch (EmptyResultDataAccessException e) {
			throw new SemDados();
		}
	}

	@Override
	public List<String> getNomesEquipasComecadasPor(String termo)
			throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			return jdbcTemplate.queryForList(SELECT_EQUIPAS_BY_NOME.toString(), 
					new Object[] {"%" + termo + "%"}, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new SemDados();
		}
	}
}
