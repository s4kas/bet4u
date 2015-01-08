package com.bmp.bet4u.client.estatisticas.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPInteger;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.REngineStdOutput;
import org.rosuda.REngine.RList;

public class RService {
	
	private static REngine re;
	private static REngine getSingletion() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (RService.re == null) {
			RService.re = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[]{"--vanilla"}, 
				new REngineStdOutput(), false);
		}
		return RService.re;
	}
	
	public double[] getLinearRegression(List<String> epocas, List<Integer> jornadas, 
			List<Integer> equipasCasa, List<Integer> equipasFora,
			List<Integer> totalGolos, List<Integer> pontossCasa, List<Integer> pontossFora,
			String epoca, Integer jornada, Integer equipaCasa, Integer equipaFora,
			Integer pontosCasa, Integer pontosFora) {
		
		RList list = new RList();
		list.put("epoca", new REXPString(epocas.toArray(new String[epocas.size()])));
		list.put("jornada", new REXPInteger(jornadas.stream().mapToInt(i->i).toArray()));
		list.put("equipaCasa", new REXPInteger(equipasCasa.stream().mapToInt(i->i).toArray()));
		list.put("equipaFora", new REXPInteger(equipasFora.stream().mapToInt(i->i).toArray()));
		list.put("totalGolos", new REXPInteger(totalGolos.stream().mapToInt(i->i).toArray()));
		list.put("pontosCasa", new REXPInteger(pontossCasa.stream().mapToInt(i->i).toArray()));
		list.put("pontosFora", new REXPInteger(pontossFora.stream().mapToInt(i->i).toArray()));
		
		return getRegression(list, epoca, jornada, equipaCasa, equipaFora, pontosCasa, pontosFora);
	}
	
	private double[] getRegression(RList list, String epoca, Integer jornada, Integer equipaCasa, Integer equipaFora,
			Integer pontosCasa, Integer pontosFora) {
		try {
			REngine re = RService.getSingletion();
			
			re.assign("x", REXP.createDataFrame(list));
			re.parseAndEval("fit <- glm (totalGolos~epoca+jornada+equipaCasa+equipaFora+pontosCasa+pontosFora, data=x, family='quasipoisson')");
			
			String command = String.format("round(predict(fit, data.frame(epoca='%s', jornada=%d, equipaCasa=%d, equipaFora=%d, pontosCasa=%d, pontosFora=%d), type='response'),3)",
						epoca, jornada, equipaCasa, equipaFora, pontosCasa, pontosFora);
			REXP response = re.parseAndEval(command);	
			
			return response.asDoubles();
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| IllegalAccessException | InvocationTargetException | REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new double[3];
	}

}
