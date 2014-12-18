package com.bmp.bet4u.common.resultado.ocorrencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import com.bmp.bet4u.common.resultado.Resultado;

public class OcorrenciaResultadoPSSetter implements
		BatchPreparedStatementSetter {
	
	private Resultado resultado;
	
	public OcorrenciaResultadoPSSetter(Resultado resultado) {
		this.resultado = resultado;
	}

	public void setValues(PreparedStatement ps, int i) throws SQLException {
		ps.setString(1, resultado.getJogo().getDataJogoSQL());
		ps.setInt(2, resultado.getJogo().getEquipaCasa().getId());
		ps.setInt(3, resultado.getJogo().getEquipaFora().getId());

		OcorrenciaResultado ocorrencia = resultado.getOcorrencias().get(i);
		ps.setInt(4, Integer.parseInt(ocorrencia.getOcorrencia().getId()));
		ps.setInt(5, ocorrencia.getMinutoOcorrencia());
		ps.setInt(6, ocorrencia.getEquipaOcorrencia().getId());
	}

	public int getBatchSize() {
		return resultado.getOcorrencias().size();
	}
}
