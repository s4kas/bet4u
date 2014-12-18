package com.bmp.bet4u.client.estatisticas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoEpocaJornada;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.resultado.jogo.Jogo;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaResultado;

public class JdbcEstatisticasDAO implements IEstatisticasDAO {
	
	private static final StringBuffer SELECT_RESULTADOS_ENTRE_EQUIPAS = 
			new StringBuffer("SELECT ")
			.append("Competicao_ocorre_Epoca_Epoca_anoInicio anoInicio, ")
			.append("Competicao_ocorre_Epoca_Epoca_anoFim anoFim, ")
			.append("Jornada_numJornada jornada, ")
			.append("Jogo_dataJogo data, ")
			.append("casa.idEquipa idCasa, ")
			.append("casa.nome equipaCasa, ")
			.append("totalPontosCasa golosCasa, ")
			.append("totalPontosFora golosFora, ")
			.append("fora.nome equipaFora, ")
			.append("fora.idEquipa idFora ")
			.append("FROM Competicao_ocorre_Epoca_tem_Jornada ")
			.append("LEFT JOIN Resultado r ON  ")
			.append("Resultado_Jogo_Equipa_idEquipaFora = r.Jogo_Equipa_idEquipaFora AND ")
			.append("Resultado_Jogo_Equipa_idEquipaCasa = r.Jogo_Equipa_idEquipaCasa AND ")
			.append("Resultado_Jogo_dataJogo = r.Jogo_dataJogo ")
			.append("LEFT JOIN Jogo ON ")
			.append("Jogo_Equipa_idEquipaFora = Equipa_idEquipaFora AND ")
			.append("Jogo_Equipa_idEquipaCasa = Equipa_idEquipaCasa AND ")
			.append("Jogo_dataJogo = dataJogo ")
			.append("LEFT JOIN Equipa fora ON Equipa_idEquipaFora = fora.idEquipa ")
			.append("LEFT JOIN Equipa casa ON Equipa_idEquipaCasa = casa.idEquipa ")
			.append("WHERE ")
			.append("casa.idEquipa = ? AND ")
			.append("fora.idEquipa = ? ")
			.append("ORDER BY ")
			.append("Jogo_dataJogo DESC");
	
	private static final StringBuffer SELECT_DEZ_RESULTADOS_DA_EQUIPA = 
			new StringBuffer("SELECT TOP 10 ")
			.append("Competicao_ocorre_Epoca_Epoca_anoInicio anoInicio, ")
			.append("Competicao_ocorre_Epoca_Epoca_anoFim anoFim, ")
			.append("Jornada_numJornada jornada, ")
			.append("Jogo_dataJogo data, ")
			.append("casa.idEquipa idCasa, ")
			.append("casa.nome equipaCasa, ")
			.append("totalPontosCasa golosCasa, ")
			.append("totalPontosFora golosFora, ")
			.append("fora.nome equipaFora, ")
			.append("fora.idEquipa idFora ")
			.append("FROM Competicao_ocorre_Epoca_tem_Jornada ")
			.append("LEFT JOIN Resultado r ON  ")
			.append("Resultado_Jogo_Equipa_idEquipaFora = r.Jogo_Equipa_idEquipaFora AND ")
			.append("Resultado_Jogo_Equipa_idEquipaCasa = r.Jogo_Equipa_idEquipaCasa AND ")
			.append("Resultado_Jogo_dataJogo = r.Jogo_dataJogo ")
			.append("LEFT JOIN Jogo ON ")
			.append("Jogo_Equipa_idEquipaFora = Equipa_idEquipaFora AND ")
			.append("Jogo_Equipa_idEquipaCasa = Equipa_idEquipaCasa AND ")
			.append("Jogo_dataJogo = dataJogo ")
			.append("LEFT JOIN Equipa fora ON Equipa_idEquipaFora = fora.idEquipa ")
			.append("LEFT JOIN Equipa casa ON Equipa_idEquipaCasa = casa.idEquipa ")
			.append("WHERE ")
			.append("casa.idEquipa = ? AND ")
			.append("fora.idEquipa = ? ")
			.append("ORDER BY ")
			.append("Jogo_dataJogo DESC");
	
	private static final StringBuffer SELECT_OCORRENCIAS_DO_JOGO = 
			new StringBuffer("SELECT ")
			.append("Ocorrencia_id, ")
			.append("minutoOcorrencia, ")
			.append("equipaOcorrencia ")
			.append("FROM Resultado_tem_Ocorrencia ")
			.append("WHERE Resultado_Jogo_dataJogo = ? AND ")
			.append("Resultado_Jogo_Equipa_idEquipaCasa = ? AND ")
			.append("Resultado_Jogo_Equipa_idEquipaFora = ?");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<ResultadoEpocaJornada> getResultadosEntreEquipas(int casa,
			int fora) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			return jdbcTemplate.query(SELECT_RESULTADOS_ENTRE_EQUIPAS.toString(), 
					new Object[] {casa, fora}, new ResultadoEpocaJornadaRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new SemDados();
		}
	}
	
	@Override
	public List<ResultadoEpocaJornada> getUltimosDezResultadosEquipa(int equipa)
			throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			return jdbcTemplate.query(SELECT_DEZ_RESULTADOS_DA_EQUIPA.toString(), 
					new Object[] {equipa}, new ResultadoEpocaJornadaRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new SemDados();
		}
	}

	@Override
	public List<OcorrenciaResultado> getOcorrencias(Jogo jogo) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			return jdbcTemplate.query(SELECT_OCORRENCIAS_DO_JOGO.toString(), 
					new Object[] {jogo.getDataJogoSQL(), jogo.getEquipaCasa().getId(), 
				jogo.getEquipaFora().getId()}, new OcorrenciasDoResultadoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<OcorrenciaResultado>();
		}
	}
}
