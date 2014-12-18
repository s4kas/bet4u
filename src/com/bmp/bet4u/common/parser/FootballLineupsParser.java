package com.bmp.bet4u.common.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.bmp.bet4u.common.equipa.Equipa;
import com.bmp.bet4u.common.parser.util.DateUtils;
import com.bmp.bet4u.common.resultado.Resultado;
import com.bmp.bet4u.common.resultado.ResultadoJornadaEpocaCompeticao;
import com.bmp.bet4u.common.resultado.ocorrencia.OcorrenciaBusiness;

public class FootballLineupsParser {
	
	private final static String URL_BASE_JORNADA = "http://www.football-lineups.com/matchday/%s/%s";

	private static Document getSingletonDoc(String url) throws IOException {
		return Jsoup.connect(url).timeout(10000).get();
	}
	
	public static void parse(ResultadoJornadaEpocaCompeticao jornadaCompeticao, String competicao) 
			throws IOException, ParseException {
		int jornada = jornadaCompeticao.getJornada().getNumero();
		
		String url = String.format(URL_BASE_JORNADA, competicao, jornada);
		
		Elements article = getSingletonDoc(url).select("article");
		Elements mainarea = article.select("#mainarea");
		Element table = mainarea.select("table").get(2);
		Elements trs = table.children().first().children();
		
		boolean jogosSemResultados = false;
		for (int i = 2; i < trs.size(); i += 3) {
			Elements els = trs.get(i).children();
			
			//resultado
			Element resEl = els.get(3);
			String[] textoResultado = resEl.select("a").first().text().split("-");
			if (textoResultado.length == 0) {
				jogosSemResultados = true;
				continue;
			}
			Resultado resultado = new Resultado();
			resultado.setPontosCasa(Integer.parseInt(textoResultado[0].trim()));
			resultado.setPontosFora(Integer.parseInt(textoResultado[1].trim()));
			
			//data do jogo
			String dataText = els.first().select("a").first().text();
			Calendar dataJogo = DateUtils.getDate("dd-MMM-yy", dataText);
			resultado.getJogo().setDataJogo(dataJogo);
			
			//casa
			Element casaEl = els.get(2);
			Element casaEl2 = casaEl.select("a").first();
			String casaText = casaEl2.text();
			Equipa eqCasa = new Equipa(casaText);
			resultado.getJogo().setEquipaCasa(eqCasa);
			//ocorrencias casa
			Elements osCasa = casaEl.children().get(1).children().select("a");
			String[] textosCasa = casaEl.children().get(1).ownText().split("'");
			for (int j = 0; j < osCasa.size(); j++) {
				Element oCasa = osCasa.get(j);
				Element next = oCasa.nextElementSibling();
				Element nextNext = oCasa.nextElementSibling();
				if (next != null && next.tagName().equalsIgnoreCase("img")
						&& next.attr("src").contains("goaln.gif")) {
					continue;
				} else if (nextNext != null && nextNext.tagName().equalsIgnoreCase("img")
						&& nextNext.attr("src").contains("goaln.gif")) {
					continue;
				} else if (textosCasa.length > j && !textosCasa[j].trim().equals("")) {
					int minuto = Integer.parseInt(textosCasa[j].trim());
					resultado.getOcorrencias().add(OcorrenciaBusiness.
							getGoloFutebol(resultado.getJogo().getEquipaCasa(),
									minuto));
				}
			}
			
			//fora
			Element foraEl = els.get(4);
			Element foraEl4 = foraEl.select("a").first();
			String foraText = foraEl4.text();
			Equipa eqFora = new Equipa(foraText);
			resultado.getJogo().setEquipaFora(eqFora);
			//ocorrencias fora
			Elements osFora = foraEl.children().get(1).children().select("a");
			String[] textosFora = foraEl.children().get(1).ownText().split("'");
			for (int j = 0; j < osFora.size(); j++) {
				Element oFora = osFora.get(j);
				Element prev = oFora.previousElementSibling();
				if (prev != null && prev.tagName().equalsIgnoreCase("img")
						&& prev.attr("src").contains("goaln.gif")) {
					continue;
				} else if (textosFora.length > j && !textosFora[j].trim().equals("")) {
					int minuto = Integer.parseInt(textosFora[j].trim());
					resultado.getOcorrencias().add(OcorrenciaBusiness.
							getGoloFutebol(resultado.getJogo().getEquipaFora(),
									minuto));
				}
			}
			
			jornadaCompeticao.getResultados().add(resultado);
		}
		
		int numeroJogos = jornadaCompeticao.getEpoca().getNumeroEquipas() / 2;
		boolean jornadaTerminada = numeroJogos == jornadaCompeticao.getResultados().size();
		jornadaCompeticao.getJornada().setTerminada(jornadaTerminada && !jogosSemResultados);
	}
}