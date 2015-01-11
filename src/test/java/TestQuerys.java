package test.java;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.rosuda.REngine.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoJornada;
import com.bmp.bet4u.client.estatisticas.business.TotalPontosKey;
import com.bmp.bet4u.client.estatisticas.dao.ResultadoJornadaRowMapper;
import com.bmp.bet4u.common.CommonConnection;

public class TestQuerys {
	
	private static Map<TotalPontosKey, Map<Integer, Integer>> classificacao = new TreeMap<>();

	public static void main(String ... args) {
		
		
		String data = "select * from resultados";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(CommonConnection.getDataSource());
		
		List<ResultadoJornada> resultados = jdbcTemplate.query(data, new ResultadoJornadaRowMapper());
		
		for (ResultadoJornada resultado : resultados) {
			Integer pontosCasa = 1, pontosFora = 1;
			if (resultado.getGolosCasa() > resultado.getGolosFora()) {
				pontosCasa = 3; pontosFora = 0;
			} else if (resultado.getGolosFora() > resultado.getGolosCasa()) {
				pontosFora = 3; pontosCasa = 0;
			}
				
			saveTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaCasa(), pontosCasa);
			saveTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaFora(), pontosFora);
		}
		
		List<String> epoca = new ArrayList<>();
		List<Integer> jornada = new ArrayList<>();
		List<Integer> equipaCasa = new ArrayList<>();
		List<Integer> equipaFora = new ArrayList<>();
		List<Integer> totalGolos = new ArrayList<>();
		List<Integer> pontosCasa = new ArrayList<>();
		List<Integer> pontosFora = new ArrayList<>();
		System.out.println("epoca;jornada;equipaCasa;equipaFora;golosCasa;golosFora;pontosCasa;pontosFora");
		for (ResultadoJornada resultado : resultados) {
			System.out.print(resultado.getEpoca() + ";");epoca.add(resultado.getEpoca());
			System.out.print(resultado.getJornada() + ";");jornada.add(resultado.getJornada());
			System.out.print(resultado.getEquipaCasa() + ";");equipaCasa.add(resultado.getEquipaCasa());
			System.out.print(resultado.getEquipaFora() + ";");equipaFora.add(resultado.getEquipaFora());
			System.out.print(resultado.getGolosCasa() + ";");
			System.out.print(resultado.getGolosFora() + ";");totalGolos.add(resultado.getGolosCasa() + resultado.getGolosFora());
			System.out.print(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaCasa()) + ";");
			pontosCasa.add(Integer.parseInt(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaCasa())));
			System.out.println(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaFora()));
			pontosFora.add(Integer.parseInt(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaFora())));
		}
		
		RList list = new RList();
		list.put("epoca", new REXPString(epoca.toArray(new String[epoca.size()])));
		list.put("jornada", new REXPInteger(jornada.stream().mapToInt(i->i).toArray()));
		list.put("equipaCasa", new REXPInteger(equipaCasa.stream().mapToInt(i->i).toArray()));
		list.put("equipaFora", new REXPInteger(equipaFora.stream().mapToInt(i->i).toArray()));
		list.put("totalGolos", new REXPInteger(totalGolos.stream().mapToInt(i->i).toArray()));
		list.put("pontosCasa", new REXPInteger(pontosCasa.stream().mapToInt(i->i).toArray()));
		list.put("pontosFora", new REXPInteger(pontosFora.stream().mapToInt(i->i).toArray()));
		linearRegression(list);
	}
	
	private static void saveTotalPontos(String epoca, Integer jornada, Integer equipa, Integer pontos) {
		TotalPontosKey tpk = new TotalPontosKey();
		tpk.setEpoca(epoca);
		tpk.setEquipa(equipa);
		
		Map<Integer, Integer> pontosJornada = classificacao.get(tpk);
		if (pontosJornada == null) {
			pontosJornada = new TreeMap<>();
			pontosJornada.put(jornada, 0);
		}
		
		Integer totalPontos = pontosJornada.get(jornada);
		if (totalPontos == null) {
			totalPontos = 0;
		}
		
		totalPontos += pontos;
		pontosJornada.put((jornada + 1), totalPontos);
		
		classificacao.put(tpk, pontosJornada);
	}
	
	private static String getTotalPontos(String epoca, Integer jornada, Integer equipa) {
		TotalPontosKey tpk = new TotalPontosKey();
		tpk.setEpoca(epoca);
		tpk.setEquipa(equipa);
		
		Map<Integer, Integer> pontosJornada = classificacao.get(tpk);
		if (pontosJornada != null) {
			Integer totalPontos = pontosJornada.get(jornada);
			if (totalPontos != null) {
				return String.valueOf(totalPontos);
			}
		}
		
		return "0";
	}
	
	private static void linearRegression(RList list) {
		try {
			REngine re = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[]{"--vanilla"}, 
					new REngineStdOutput(), false);
			
			re.assign("x", REXP.createDataFrame(list));
			re.parseAndEval("fit <- lm (totalGolos~epoca+jornada+equipaCasa+equipaFora+pontosCasa+pontosFora, data=x)");

			REXP confidence = re.parseAndEval("predict(fit, data.frame(epoca='2014-2015', jornada=15, equipaCasa=18, equipaFora=10, pontosCasa=20, pontosFora=31), interval='confidence')");
			REXP predict = re.parseAndEval("predict(fit, data.frame(epoca='2014-2015', jornada=15, equipaCasa=18, equipaFora=10, pontosCasa=20, pontosFora=31), interval='predict')");
			
			System.out.println("   confidence:");
			System.out.println("model: " + confidence.asDoubles()[0]);
			System.out.println("min: " + confidence.asDoubles()[1]);
			System.out.println("max: " + confidence.asDoubles()[2]);
			System.out.println("");
			System.out.println("   predict:");
			System.out.println("model: " + predict.asDoubles()[0]);
			System.out.println("min: " + predict.asDoubles()[1]);
			System.out.println("max: " + predict.asDoubles()[2]);
			
			re.close();
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| IllegalAccessException | InvocationTargetException | REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
