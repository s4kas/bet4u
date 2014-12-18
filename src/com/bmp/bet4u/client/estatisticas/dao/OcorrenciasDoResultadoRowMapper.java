package com.bmp.bet4u.client.estatisticas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bmp.bet4u.common.equipa.Equipa;
import com.bmp.bet4u.common.resultado.ocorrencia.Ocorrencia;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaResultado;

public class OcorrenciasDoResultadoRowMapper implements RowMapper<OcorrenciaResultado> {

	@Override
	public OcorrenciaResultado mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setId(rs.getString("Ocorrencia_id"));
		
		OcorrenciaResultado ocorrenciaResultado = new OcorrenciaResultado(ocorrencia);
		Equipa equipaOcorrencia = new Equipa();
		equipaOcorrencia.setId(rs.getInt("equipaOcorrencia"));
		ocorrenciaResultado.setEquipaOcorrencia(equipaOcorrencia);
		ocorrenciaResultado.setMinutoOcorrencia(rs.getInt("minutoOcorrencia"));
		
		return ocorrenciaResultado;
	}

}
