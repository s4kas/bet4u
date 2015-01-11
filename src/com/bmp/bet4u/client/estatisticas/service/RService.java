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
			List<String> equipasCasa, List<String> equipasFora,
			List<Integer> totalGolos, List<Integer> pontossCasa, List<Integer> pontossFora,
			String epoca, Integer jornada, Integer equipaCasa, Integer equipaFora,
			Integer pontosCasa, Integer pontosFora, boolean isCasa) {
		
		RList list = new RList();
		list.put("epoca", new REXPString(epocas.toArray(new String[epocas.size()])));
		list.put("jornada", new REXPInteger(jornadas.stream().mapToInt(i->i).toArray()));
		list.put("equipaCasa", new REXPString(equipasCasa.toArray(new String[equipasCasa.size()])));
		list.put("equipaFora", new REXPString(equipasFora.toArray(new String[equipasFora.size()])));
		list.put("totalGolos", new REXPInteger(totalGolos.stream().mapToInt(i->i).toArray()));
		list.put("pontosCasa", new REXPInteger(pontossCasa.stream().mapToInt(i->i).toArray()));
		list.put("pontosFora", new REXPInteger(pontossFora.stream().mapToInt(i->i).toArray()));
		
		return getRegression(list, epoca, jornada, equipaCasa, equipaFora, pontosCasa, pontosFora, isCasa);
	}
	
	private double[] getRegression(RList list, String epoca, Integer jornada, Integer equipaCasa, Integer equipaFora,
			Integer pontosCasa, Integer pontosFora, boolean isCasa) {
		try {
			REngine re = RService.getSingletion();
			
			double[] result = new double[3];
			
			re.assign("x", REXP.createDataFrame(list));
			if (isCasa) {
				re.parse(String.format("x <- subset(x, equipaCasa=='%s')", equipaCasa), true);
				re.parseAndEval("fit <- glm (totalGolos~epoca+jornada+equipaFora+pontosCasa+pontosFora, data=x, family='quasipoisson')");
				
				String command = String.format("round(predict(fit, data.frame(epoca='%s', jornada=%d, equipaFora='%s', pontosCasa=%d, pontosFora=%d), type='response'),3)",
						epoca, jornada, equipaFora, pontosCasa, pontosFora);
				REXP response = re.parseAndEval(command);
				result[0] = response.asDoubles()[0];
				
				command = String.format("e <- predict(fit, data.frame(epoca='%s', jornada=%d, equipaFora='%s', pontosCasa=%d, pontosFora=%d), type='link', se.fit = TRUE)",
						epoca, jornada, equipaFora, pontosCasa, pontosFora);
				re.parseAndEval(command);
				
			} else {
				re.parse(String.format("x <- subset(x, equipaFora=='%s')", equipaFora), true);
				re.parseAndEval("fit <- glm (totalGolos~epoca+jornada+equipaCasa+pontosCasa+pontosFora, data=x, family='quasipoisson')");
				
				String command = String.format("round(predict(fit, data.frame(epoca='%s', jornada=%d, equipaCasa='%s', pontosCasa=%d, pontosFora=%d), type='response'),3)",
						epoca, jornada, equipaCasa, pontosCasa, pontosFora);
				REXP response = re.parseAndEval(command);
				result[0] = response.asDoubles()[0];
				
				command = String.format("e <- predict(fit, data.frame(epoca='%s', jornada=%d, equipaCasa='%s', pontosCasa=%d, pontosFora=%d), type='link', se.fit = TRUE)",
						epoca, jornada, equipaCasa, pontosCasa, pontosFora);
				re.parseAndEval(command);
			}
			REXP upr = re.parseAndEval("round(fit$family$linkinv(e$fit + (1.96 * e$se.fit)),3)");
			REXP lwr = re.parseAndEval("round(fit$family$linkinv(e$fit - (1.96 * e$se.fit)),3)");
			
			result[1] = lwr.asDouble();
			result[2] = upr.asDouble();
			
			return result;
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| IllegalAccessException | InvocationTargetException | REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new double[3];
	}

}
