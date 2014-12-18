package com.bmp.bet4u.portugal.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.bmp.bet4u.common.epoca.Epoca;
import com.bmp.bet4u.common.equipa.Equipa;
import com.bmp.bet4u.common.parser.util.DateUtils;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaBusiness;
import com.bmp.bet4u.common.resultado.Resultado;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;

public class LigaPortuguesaParser {
	
	private static final String URL_BASE_SITE = "http://www.ligaportugal.pt";
	private static final String URL_BASE_JORNADA = "http://www.ligaportugal.pt/oou/jornada/";
	private static final String DDMMM = "dd/MMM";
	
	public static void parse(ResultadoJornadaEpocaCompeticao jornadaCompeticao,
			String competicao) throws IOException, ParseException {
		
		//vamos buscar a epoca e competicao
		Epoca epoca = jornadaCompeticao.getEpoca();
		StringBuffer epocaSb = new StringBuffer().
				append(epoca.getAnoInicio()).
				append(epoca.getAnoFim()).
				append("/");
				
		//numero jornada
		int numeroJornada = jornadaCompeticao.getJornada().getNumero();
		
		//conexao ao site
		Document doc = Jsoup.connect(URL_BASE_JORNADA + epocaSb + competicao + numeroJornada).get();
		Element el = doc.getElementById("matchdayTable");

		//Tratamos do conteudo da tabela (os jogos e os respectivos resultados)
		boolean jornadaTerminada = true;
		Elements body = el.select("tbody > tr");
		for (Element e : body) {
			if (e.getElementsByClass("match-results") != null 
					&& !e.getElementsByClass("match-results").isEmpty()) { //valido se ja tenho resultado
				Resultado resultado = new Resultado();
				
				for (Element e2 : e.children()) {
					if (e2.elementSiblingIndex() == 0) { //equipa que joga em casa
						
						Equipa casa = new Equipa(e2.text());
						resultado.getJogo().setEquipaCasa(casa);
								
					} else if (e2.elementSiblingIndex() == 2) { //resultado e data do jogo
						String dataText = e2.getElementsByClass("match-date").text();
						String[] data = dataText.split(" ");
						Calendar cal = DateUtils.getDate(DDMMM, data[0], 
								String.valueOf(epoca.getAnoInicio()), 
								String.valueOf(epoca.getAnoFim()));
						resultado.getJogo().setDataJogo(cal);
						
						String resultadoText = e2.getElementsByClass("match-results").text();
						String[] resultados = resultadoText.split(" - ");
						if (resultados[0].contains("(")) {
							int firstParenthesis = resultados[0].indexOf("(");
							int nextParenthesis = resultados[0].indexOf(")");
							resultados[0] = resultados[0].substring(firstParenthesis+1, nextParenthesis);
						}
						resultado.setPontosCasa(Integer.parseInt(resultados[0]));
						
						if (resultados[1].contains("(")) {
							int firstParenthesis = resultados[1].indexOf("(");
							int nextParenthesis = resultados[1].indexOf(")");
							resultados[1] = resultados[1].substring(firstParenthesis+1, nextParenthesis);
						}
						resultado.setPontosFora(Integer.parseInt(resultados[1]));
								
					} else if (e2.elementSiblingIndex() == 4) { //equipa que joga fora
						
						Equipa fora = new Equipa(e2.text());
						resultado.getJogo().setEquipaFora(fora);
						
					}
				}
				
				//buscar ocorrencias do jogo
				Document detalhesJogo = Jsoup.connect(URL_BASE_SITE + e.attr("data-detail")).get();
				Element teams = detalhesJogo.getElementsByClass("teams").first();
				if (teams != null) {
					Elements jogadoresCasa = teams.children().first().//equipa da casa
							children().first().
							children().first().
							children().last().select("table > tbody > tr");
					
					for (Element jogador : jogadoresCasa) {
						for (Element ocorrencia : jogador.children()) {
							if (!ocorrencia.children().isEmpty()) {
								if (ocorrencia.siblingIndex() == 5) { // GOLOOOOO
									String minutoString = ocorrencia.children().
											first().text();
									int minuto = 0;
									if (minutoString.contains("+")) {
										String[] minutos = minutoString.replace("'", "")
												.split("\\+");
										minuto = Integer.parseInt(minutos[0]) + 
												Integer.parseInt(minutos[1]);
									} else {
										minuto = Integer.parseInt(minutoString.replace("'", ""));
									}
									resultado.getOcorrencias().add(OcorrenciaBusiness.
											getGoloFutebol(resultado.getJogo().getEquipaCasa(),
													minuto));
								}
							}
						}
					}
					
					Elements jogadoresFora = teams.children().first().//equipa visitante
							children().first().
							children().last().
							children().last().select("table > tbody > tr");
					
					for (Element jogador : jogadoresFora) {
						for (Element ocorrencia : jogador.children()) {
							if (!ocorrencia.children().isEmpty()) {
								if (ocorrencia.siblingIndex() == 5) { // GOLOOOOO
									String minutoString = ocorrencia.children().
											first().text();
									int minuto = 0;
									if (minutoString.contains("+")) {
										String[] minutos = minutoString.replace("'", "")
												.split("\\+");
										minuto = Integer.parseInt(minutos[0]) + 
												Integer.parseInt(minutos[1]);
									} else {
										minuto = Integer.parseInt(minutoString.replace("'", ""));
									}
									resultado.getOcorrencias().add(OcorrenciaBusiness.
											getGoloFutebol(resultado.getJogo().getEquipaFora(),
													minuto));
								}
							}
						}
					}
				}
				
				//adiciono resultado a lista de resultados da jornada
				jornadaCompeticao.getResultados().add(resultado);
				
			} else { //tenho jogos sem resultado
				jornadaTerminada = false;
			}
		}
		//guardo o estado da jornada
		jornadaCompeticao.getJornada().setTerminada(jornadaTerminada);
	}
	
}
