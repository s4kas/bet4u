package com.bmp.bet4u.common.epoca;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.exc.SemDados;

public class JdbcEpocaDAO implements IEpocaDAO {
	
	private static final StringBuffer SELECT_EPOCAS_BY_COMPETICAO = 
			new StringBuffer("SELECT anoInicio, anoFim, descricao, epocaTerminada, numeroEquipas ").
			append("FROM Epoca, Competicao_ocorre_Epoca ").
			append("WHERE Epoca_anoInicio = anoInicio ").
			append("AND Epoca_anoFim = anoFim ").
			append("AND Competicao_idCompeticao = ? ").
			append("AND Competicao_Pais_idPais = ? ").
			append("AND Competicao_Modalidade_idModalidade = ?");
	
	private static final StringBuffer UPDATE_EPOCA_COMPETICAO = 
			new StringBuffer("UPDATE Competicao_ocorre_Epoca ").
			append("SET epocaTerminada = ? WHERE Competicao_idCompeticao = ? ").
			append("AND Competicao_Pais_idPais = ? AND Competicao_Modalidade_idModalidade = ? ").
			append("AND Epoca_anoInicio = ? AND Epoca_anoFim = ?");
			
			
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Epoca> getListaEpocasByCompeticao(Competicao competicao) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Epoca> epocas = jdbcTemplate.query(
				SELECT_EPOCAS_BY_COMPETICAO.toString(),
				new Object[]{competicao.getId(), competicao.getPais().getId(), 
					competicao.getModalidade().getId()},
				new EpocaRowMapper());
		
		if (epocas.isEmpty()) {
			throw new SemDados();
		}

		return epocas;
	}

	public void updateEpoca(Competicao competicao, Epoca epoca) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(UPDATE_EPOCA_COMPETICAO.toString(), 
				new Object[] {epoca.isTerminada(),
			competicao.getId(),
			competicao.getPais().getId(),
			competicao.getModalidade().getId(),
			epoca.getAnoInicio(),
			epoca.getAnoFim()});
	}

}
