package com.bmp.bet4u.client.estatisticas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoEpocaJornada;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.equipa.Equipa;
import com.bmp.bet4u.common.jornada.Jornada;
import com.bmp.bet4u.common.resultado.jogo.Jogo;

public class ResultadoEpocaJornadaRowMapper implements RowMapper<ResultadoEpocaJornada> {

	public ResultadoEpocaJornada mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultadoEpocaJornada resultadoEpocaJornada = new ResultadoEpocaJornada();
		
		Epoca epoca = new Epoca(rs.getInt("anoInicio"), rs.getInt("anoFim"));
		resultadoEpocaJornada.setEpoca(epoca);
		
		Jornada jornada = new Jornada(rs.getInt("jornada"));
		resultadoEpocaJornada.setJornada(jornada);
		
		Equipa casa = new Equipa(rs.getInt("idCasa"), rs.getString("equipaCasa"));
		Equipa fora = new Equipa(rs.getInt("idFora"), rs.getString("equipaFora"));
		Jogo jogo = new Jogo();
		jogo.setEquipaCasa(casa);
		jogo.setEquipaFora(fora);
		jogo.setDataJogoSQL(rs.getString("data"));
		resultadoEpocaJornada.setJogo(jogo);
		resultadoEpocaJornada.setPontosCasa(rs.getInt("golosCasa"));
		resultadoEpocaJornada.setPontosFora(rs.getInt("golosFora"));
		
		return resultadoEpocaJornada;
	}

}
