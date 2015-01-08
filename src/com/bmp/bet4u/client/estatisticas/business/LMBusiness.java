package com.bmp.bet4u.client.estatisticas.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.bmp.bet4u.client.estatisticas.beans.ResultadoJornada;
import com.bmp.bet4u.client.estatisticas.service.RService;

public class LMBusiness {
	
	private Map<TotalPontosKey, Map<Integer, Integer>> classificacao = new TreeMap<>();
	private RService rService = new RService();
	
	public double[] getFittedValues(List<ResultadoJornada> resultados, String epoca, Integer jornada,
			Integer equipaCasa, Integer equipaFora, Integer pontosCasa, Integer pontosFora) {
		
		for (ResultadoJornada resultado : resultados) {
			Integer pontosCasaL = 1, pontosForaL = 1;
			if (resultado.getGolosCasa() > resultado.getGolosFora()) {
				pontosCasaL = 3; pontosForaL = 0;
			} else if (resultado.getGolosFora() > resultado.getGolosCasa()) {
				pontosForaL = 3; pontosCasaL = 0;
			}
				
			saveTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaCasa(), pontosCasaL);
			saveTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaFora(), pontosForaL);
		}
		
		List<String> epocas = new ArrayList<>();
		List<Integer> jornadas = new ArrayList<>();
		List<Integer> equipasCasa = new ArrayList<>();
		List<Integer> equipasFora = new ArrayList<>();
		List<Integer> totalGolos = new ArrayList<>();
		List<Integer> pontossCasa = new ArrayList<>();
		List<Integer> pontossFora = new ArrayList<>();
		for (ResultadoJornada resultado : resultados) {
			epocas.add(resultado.getEpoca());
			jornadas.add(resultado.getJornada());
			equipasCasa.add(resultado.getEquipaCasa());
			equipasFora.add(resultado.getEquipaFora());
			totalGolos.add(resultado.getGolosCasa() + resultado.getGolosFora());
			pontossCasa.add(Integer.parseInt(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaCasa())));
			pontossFora.add(Integer.parseInt(getTotalPontos(resultado.getEpoca(), resultado.getJornada(), resultado.getEquipaFora())));
		}
		
		return rService.getLinearRegression(epocas, jornadas, equipasCasa, equipasFora, totalGolos, pontossCasa, pontossFora,
				epoca, jornada, equipaCasa, equipaFora, pontosCasa, pontosFora);
	}
	
	private void saveTotalPontos(String epoca, Integer jornada, Integer equipa, Integer pontos) {
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
	
	private String getTotalPontos(String epoca, Integer jornada, Integer equipa) {
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
	
}
