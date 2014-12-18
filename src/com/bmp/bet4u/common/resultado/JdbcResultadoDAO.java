package com.bmp.bet4u.common.resultado;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.common.competicao.Competicao;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.exc.SemDados;
import com.bmp.bet4u.common.jornada.Jornada;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaResultadoPSSetter;

public class JdbcResultadoDAO implements IResultadoDAO {
	
	private final static StringBuffer SELECT_RESULTADO = 
			new StringBuffer("SELECT Jogo_dataJogo, Jogo_Equipa_idEquipaCasa, Jogo_Equipa_idEquipaFora, ").
			append("totalPontosCasa, totalPontosFora ").
			append("FROM Resultado WHERE Jogo_dataJogo = ? ").
			append("AND Jogo_Equipa_idEquipaCasa = ? ").
			append("AND Jogo_Equipa_idEquipaFora = ?");
	
	private final static StringBuffer INSERT_RESULTADO = 
			new StringBuffer("INSERT INTO Resultado ").
			append("(Jogo_dataJogo, Jogo_Equipa_idEquipaCasa, Jogo_Equipa_idEquipaFora, ").
			append("totalPontosCasa, totalPontosFora) ").
			append("values (?, ?, ?, ?, ?)");
	
	private final static StringBuffer INSERT_OCORRENCIA_RESULTADO = 
			new StringBuffer("INSERT INTO Resultado_tem_Ocorrencia ").
			append("(Resultado_Jogo_dataJogo, Resultado_Jogo_Equipa_idEquipaCasa, ").
			append("Resultado_Jogo_Equipa_idEquipaFora, Ocorrencia_id, minutoOcorrencia, ").
			append(" equipaOcorrencia) ").
			append("values (?, ?, ?, ?, ?, ?)");
	
	private final static StringBuffer INSERT_RESULTADOS_DA_JORNADA = 
			new StringBuffer("INSERT INTO Competicao_ocorre_Epoca_tem_Jornada ").
			append("(Competicao_ocorre_Epoca_Competicao_idCompeticao, ").
			append("Competicao_ocorre_Epoca_Competicao_Pais_idPais, ").
			append("Competicao_ocorre_Epoca_Competicao_Modalidade_idModalidade, ").
			append("Competicao_ocorre_Epoca_Epoca_anoInicio, ").
			append("Competicao_ocorre_Epoca_Epoca_anoFim, ").
			append("Resultado_Jogo_dataJogo, Resultado_Jogo_Equipa_idEquipaCasa, ").
			append("Resultado_Jogo_Equipa_idEquipaFora, Jornada_numJornada, jornadaTerminada) ").
			append("VALUES (?,?,?,?,?,?,?,?,?,?)");
	
	private final static StringBuffer SELECT_RESULTADOS_DA_JORNADA = 
			new StringBuffer("SELECT ").
			append("Competicao_ocorre_Epoca_Competicao_idCompeticao, ").
			append("Competicao_ocorre_Epoca_Competicao_Pais_idPais, ").
			append("Competicao_ocorre_Epoca_Competicao_Modalidade_idModalidade, ").
			append("Competicao_ocorre_Epoca_Epoca_anoInicio, ").
			append("Competicao_ocorre_Epoca_Epoca_anoFim, ").
			append("Resultado_Jogo_dataJogo, Resultado_Jogo_Equipa_idEquipaCasa, ").
			append("Resultado_Jogo_Equipa_idEquipaFora, Jornada_numJornada, jornadaTerminada ").
			append("FROM Competicao_ocorre_Epoca_tem_Jornada ").
			append("WHERE ").
			append("Competicao_ocorre_Epoca_Competicao_idCompeticao = ? AND ").
			append("Competicao_ocorre_Epoca_Competicao_Pais_idPais = ? AND ").
			append("Competicao_ocorre_Epoca_Competicao_Modalidade_idModalidade = ? AND ").
			append("Competicao_ocorre_Epoca_Epoca_anoInicio = ? AND ").
			append("Competicao_ocorre_Epoca_Epoca_anoFim = ? AND ").
			append("Resultado_Jogo_dataJogo = ? AND ").
			append("Resultado_Jogo_Equipa_idEquipaCasa = ? AND ").
			append("Resultado_Jogo_Equipa_idEquipaFora = ? AND ").
			append("Jornada_numJornada = ? ");
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public Resultado getResultadoByDataByEquipas(Resultado resultado)
			throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Resultado> resultados = jdbcTemplate.query(SELECT_RESULTADO.toString(), 
				new Object[] {resultado.getJogo().getDataJogoSQL(),resultado.getJogo().getEquipaCasa().getId(), 
				resultado.getJogo().getEquipaFora().getId()}, new ResultadoRowMapper());
		
		if (resultados.isEmpty()) {
			throw new SemDados();
		}
		
		return resultados.get(0);
	}

	public void insertResultado(Resultado resultado) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(INSERT_RESULTADO.toString(), new Object[] {resultado.getJogo().getDataJogoSQL(),
			resultado.getJogo().getEquipaCasa().getId(), resultado.getJogo().getEquipaFora().getId(),
			resultado.getPontosCasa(), resultado.getPontosFora()});
	}
	
	public void insertOcorrenciasDoResultado(Resultado resultado) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.batchUpdate(INSERT_OCORRENCIA_RESULTADO.toString(), 
				new OcorrenciaResultadoPSSetter(resultado));
	}

	public void insertResultadoJornadaEpocaCompeticao(Competicao competicao, 
			Epoca epoca, Resultado resultado, Jornada jornada) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(INSERT_RESULTADOS_DA_JORNADA.toString(), new Object[] {
			competicao.getId(),
			competicao.getPais().getId(),
			competicao.getModalidade().getId(),
			epoca.getAnoInicio(),
			epoca.getAnoFim(),
			resultado.getJogo().getDataJogoSQL(),
			resultado.getJogo().getEquipaCasa().getId(),
			resultado.getJogo().getEquipaFora().getId(),
			jornada.getNumero(),
			jornada.isTerminada()});
	}
	
	public boolean existeResultadoJornadaEpocaCompeticao(Competicao competicao, 
			Epoca epoca, Resultado resultado, Jornada jornada) throws SemDados {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> resultados = 
				jdbcTemplate.queryForList(SELECT_RESULTADOS_DA_JORNADA.toString(), new Object[] {
			competicao.getId(),
			competicao.getPais().getId(),
			competicao.getModalidade().getId(),
			epoca.getAnoInicio(),
			epoca.getAnoFim(),
			resultado.getJogo().getDataJogoSQL(),
			resultado.getJogo().getEquipaCasa().getId(),
			resultado.getJogo().getEquipaFora().getId(),
			jornada.getNumero()
		});
		
		if (resultados.isEmpty()) {
			throw new SemDados();
		}
		
		return true;
	}
}
