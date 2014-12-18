package test.java;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJSoup {
	
	public static void main (String[] args) {
		try {
			new TestJSoup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TestJSoup () throws IOException {
		parseURLTest();
	}
	
	private void parseURLTest () throws IOException {
		for (int i = 1 ; i < 30 ; i++) {
			//TODO validar se a jornada ja tem resultados

			Document doc = Jsoup.connect("http://www.ligaportugal.pt/oou/jornada/20132014/primeiraliga/" + i).get();
			Element el = doc.getElementById("matchdayTable");
			
			//Tratamos do cabecalho da tabela da jornada
			Elements header = el.select("thead > tr > th");
			System.out.println(header.text());
			
			
			//Tratamos do conteudo da tabela (os jogos e os respectivos resultados)
			Elements body = el.select("tbody > tr");
			for (Element e : body) {
				if (e.getElementsByClass("match-results") != null 
						&& !e.getElementsByClass("match-results").isEmpty()) { //valido se ja tenho resultado
					for (Element e2 : e.children()) {
						if (e2.elementSiblingIndex() == 0) { //equipa que joga em casa
							
							System.out.print(e2.text() + " ");
							
						} else if (e2.elementSiblingIndex() == 2) { //resultado do jogo
							String resultado = e2.getElementsByClass("match-results").text();
							String[] resultados = resultado.split(" - ");
							System.out.print(resultados[0] + " - " + resultados[1]);
							
						} else if (e2.elementSiblingIndex() == 4) { //equipa que joga fora
							
							System.out.print(" " + e2.text());
							
						}
					}
					System.out.println();
					
					//buscar ocorrencias do jogo
					Document detalhesJogo = Jsoup.connect("http://www.ligaportugal.pt" + e.attr("data-detail")).get();
					Element teams = detalhesJogo.getElementsByClass("teams").first();
					Elements jogadoresCasa = teams.children().first().//equipa da casa
							children().first().
							children().first().
							children().last().select("table > tbody > tr");
					
					for (Element jogador : jogadoresCasa) {
						for (Element ocorrencia : jogador.children()) {
							if (!ocorrencia.children().isEmpty()) {
								if (ocorrencia.siblingIndex() == 5) { // GOLOOOOO
									System.out.println(ocorrencia.children().first().text());
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
									System.out.println(ocorrencia.children().first().text());
								}
							}
						}
					}
					break;
				}
			}
			break;
		}
	}
}
