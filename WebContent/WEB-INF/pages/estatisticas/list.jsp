<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main">

	<c:if test="${ empty estatisticas }">
		:(
	</c:if>
	<c:if test="${ not empty estatisticas }">
		<div id="accordion">
			<h3>Versus</h3>
			<div>
				<h6>Resultados e Golos</h6>
				<div id="results_goals_versus"></div>
				<h6>Golos por minuto</h6>
				<div id="goals_min_versus"></div>
				<h6>Jogos</h6>
				<div id="games_versus"></div>
			</div>
			<h3>Últimos 10 Jogos Equipa Casa</h3>
			<div>
				<h6>Resultados e Golos</h6>
				<div id="results_goals_home"></div>
				<h6>Golos por minuto</h6>
				<div id="goals_min_home"></div>
				<h6>Jogos</h6>
				<div id="games_home"></div>
			</div>
			<h3>Últimos 10 Jogos Equipa Fora</h3>
			<div>
				<h6>Resultados e Golos</h6>
				<div id="results_goals_away"></div>
				<h6>Golos por minuto</h6>
				<div id="goals_min_away"></div>
				<h6>Jogos</h6>
				<div id="games_away"></div>
			</div>
			<h3>LM</h3>
			<div>
				<h6>LM</h6>
				<div id="lm_regression"></div>
			</div>
		</div>
	</c:if>
	<div class="botoes"><button id="voltar">Voltar</button></div>
	
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$( "#voltar" ).click(function() {
			document.location.href = '${pageContext. request. contextPath}/';
		});
		
		$("#accordion").accordion({ heightStyle: "content" });
	});
	
	google.load('visualization', '1', {packages:['table']});
	google.load("visualization", "1", {packages:["corechart"]});
	
	var estatisticas = JSON.parse('${estatisticas}');
	var lm = JSON.parse('${lm}');
	prepareDataForCharts(estatisticas, lm);
	
    google.setOnLoadCallback(drawResultsGoalsVersus);
    google.setOnLoadCallback(drawGoalsMinutesVersus);
    google.setOnLoadCallback(drawGamesVersus);
    google.setOnLoadCallback(drawLM);
    
    function drawResultsGoalsVersus() {
    	var equipas = $("body").data("equipas");
    	var resultados = $("body").data("resultados");
    	var data = new google.visualization.DataTable();
    	data.addColumn('string', '');
    	data.addColumn('number', equipas["equipaCasa"]);
        data.addColumn('number', '');
        data.addColumn('number', equipas["equipaFora"]);
        data.addColumn('number', 'Total');
        data.addRows([
			['Resultados', resultados["vitoriasCasaVersus"], resultados["empatesVersus"], 
			 resultados["vitoriasForaVersus"], resultados["totalJogosVersus"]],
			['Golos', resultados["golosVersusCasa"], , resultados["golosVersusFora"], 
			 resultados["golosVersusTotal"]],
			['Media Golos/Jogo', resultados["mediaGolosVersusCasa"], , 
			 resultados["mediaGolosVersusFora"], resultados["mediaGolosVersusTotal"]]
        ]);
        
        var options = {
        	cssClassNames : {
        		'headerRow': 'tableHeaderRow',
        		'tableRow': 'tableRow',
        		'oddTableRow': 'tableRow',
        		'selectedTableRow': 'selectedTableRow',
        		'hoverTableRow': 'hoverTableRow',
        		'headerCell': '',
        		'tableCell': ''},
        	width: 750,
        	height: 200
        };
        
		var table = new google.visualization.Table(document.getElementById('results_goals_versus'));
    	table.draw(data, options);
    }
    
    function drawGoalsMinutesVersus() {
    	var equipas = $("body").data("equipas");
    	var golosCasa = $("body").data("golosCasa");
    	var golosFora = $("body").data("golosFora");
    	var data = google.visualization.arrayToDataTable([
			['Equipa', equipas["equipaCasa"], equipas["equipaFora"]],
			['0-15', golosCasa["casaMin0_15"], golosFora["foraMin0_15"]],
 			['15-30', golosCasa["casaMin15_30"], golosFora["foraMin15_30"]],
			['30-45', golosCasa["casaMin30_45"], golosFora["foraMin30_45"]],
			['45-60', golosCasa["casaMin45_60"], golosFora["foraMin45_60"]],
			['60-75', golosCasa["casaMin60_75"], golosFora["foraMin60_75"]],
			['75-90', golosCasa["casaMin75_90"], golosFora["foraMin75_90"]]
		]);
    	
    	var options = {
    		width: 750,
    	    height: 200,
    	    vAxis: {title: "Golos"},
    	    hAxis: {title: "Minutos"}
    	};

    	var chart = new google.visualization.ColumnChart(document.getElementById('goals_min_versus'));

    	chart.draw(data, options);
    }
    
    function drawGamesVersus() {
    	var equipas = $("body").data("equipas");
    	var jogosVersus = $("body").data("jogosVersus");
    	jogosVersus.unshift(['Jogos', equipas["equipaCasa"], equipas["equipaFora"]]);
    	
    	var data = google.visualization.arrayToDataTable(jogosVersus);
    	
    	var options = {
        	width: 750,
        	height: 300,
			vAxis: {title: "Minutos dos Golos", viewWindow : {min: 0, max: 100}},
			hAxis: {title: "Jogos"}
		};
    	
    	var chart = new google.visualization.ScatterChart(document.getElementById('games_versus'));
    	
    	chart.draw(data, options);
    }
    
    function drawLM() {
    	var confCasa = $("body").data("lmConfCasa");
    	var confFora = $("body").data("lmConfFora");
    	
    	var data = new google.visualization.DataTable();
    	data.addColumn('string', 'Intervalo');
    	data.addColumn('number', 'Modelo');
    	data.addColumn('number', 'Minimo');
    	data.addColumn('number', 'Maximo');
    	
    	data.addRows([
    	  			['Casa', confCasa[0] , confCasa[1], confCasa[2]],
    	  			['Fora', confFora[0] , confFora[1], confFora[2]]
    	          ]);
    	
    	var options = {
            	cssClassNames : {
            		'headerRow': 'tableHeaderRow',
            		'tableRow': 'tableRow',
            		'oddTableRow': 'tableRow',
            		'selectedTableRow': 'selectedTableRow',
            		'hoverTableRow': 'hoverTableRow',
            		'headerCell': '',
            		'tableCell': ''},
            	width: 750,
            	height: 200
            };
            
    	var table = new google.visualization.Table(document.getElementById('lm_regression'));
        table.draw(data, options);
    }
    
    function prepareDataForCharts(estatisticas, lm) {
    	$("body").data("lmConfCasa", lm.confCasa);
    	$("body").data("lmConfFora", lm.confFora);
    	
    	if (estatisticas.length > 0) {
    		$("body").data("equipas", {
    			"equipaCasa":estatisticas[0].pesquisaInicial.equipaCasa.nome,
    			"equipaFora":estatisticas[0].pesquisaInicial.equipaFora.nome
    		});
    	}
    	
    	var resultados = {
    		"vitoriasCasaVersus":0,"empatesVersus":0,"vitoriasForaVersus":0,"totalJogosVersus":0,
    		"golosVersusCasa":0,"golosVersusFora":0,"golosVersusTotal":0,
    		"mediaGolosVersusCasa":0,"mediaGolosVersusFora":0,"mediaGolosVersusTotal":0
    	};
    	
    	var golosCasa = {
    		"casaMin0_15":0,"casaMin15_30":0,"casaMin30_45":0,"casaMin45_60":0,
    		"casaMin60_75":0,"casaMin75_90":0
    	};
    	
		var golosFora = {
    		"foraMin0_15":0,"foraMin15_30":0,"foraMin30_45":0,"foraMin45_60":0,
    		"foraMin60_75":0,"foraMin75_90":0
    	};
		
		var jogosVersus = [];
    	
    	$.each(estatisticas, function(index, stat) {
    		var equipaCasa = stat.jogo.equipaCasa.nome;
    		var equipaCasaId = stat.jogo.equipaCasa.id;
    		var equipaFora = stat.jogo.equipaFora.nome;
    		var equipaForaId = stat.jogo.equipaFora.id;
    		
    		if ($("body").data("equipas")["equipaCasa"] == equipaCasa
    				&& $("body").data("equipas")["equipaFora"] == equipaFora) {
    			if (stat.pontosCasa > stat.pontosFora) {
    				resultados["vitoriasCasaVersus"] += 1;
    			} else if (stat.pontosCasa < stat.pontosFora) {
    				resultados["vitoriasForaVersus"] += 1;
    			} else {
    				resultados["empatesVersus"] += 1;
    			}
    			resultados["totalJogosVersus"] += 1;
    			resultados["golosVersusCasa"] += stat.pontosCasa;
    			resultados["golosVersusFora"] += stat.pontosFora;
    			resultados["golosVersusTotal"] += (stat.pontosCasa + stat.pontosFora);
    			
    			$.each(stat.ocorrencias, function (index, value) {
        			if (value.equipaOcorrencia.id == equipaCasaId) {
        				if (value.minutoOcorrencia >= 0 && value.minutoOcorrencia <= 15) {
        					golosCasa["casaMin0_15"] += 1;
        				} else if (value.minutoOcorrencia > 15 && value.minutoOcorrencia <= 30) {
        					golosCasa["casaMin15_30"] += 1;
        				} else if (value.minutoOcorrencia > 30 && value.minutoOcorrencia <= 45) {
        					golosCasa["casaMin30_45"] += 1;
        				} else if (value.minutoOcorrencia > 45 && value.minutoOcorrencia <= 60) {
        					golosCasa["casaMin45_60"] += 1;
        				} else if (value.minutoOcorrencia > 60 && value.minutoOcorrencia <= 75) {
        					golosCasa["casaMin60_75"] += 1;
        				} else if (value.minutoOcorrencia > 75) {
        					golosCasa["casaMin75_90"] += 1;
        				}
        				jogosVersus.push([new Date(stat.jogo.dataJogo), value.minutoOcorrencia, -10]);
        			} else if (value.equipaOcorrencia.id == equipaForaId) {
						if (value.minutoOcorrencia >= 0 && value.minutoOcorrencia <= 15) {
							golosFora["foraMin0_15"] += 1;
        				} else if (value.minutoOcorrencia > 15 && value.minutoOcorrencia <= 30) {
        					golosFora["foraMin15_30"] += 1;
        				} else if (value.minutoOcorrencia > 30 && value.minutoOcorrencia <= 45) {
        					golosFora["foraMin30_45"] += 1;
        				} else if (value.minutoOcorrencia > 45 && value.minutoOcorrencia <= 60) {
        					golosFora["foraMin45_60"] += 1;
        				} else if (value.minutoOcorrencia > 60 && value.minutoOcorrencia <= 75) {
        					golosFora["foraMin60_75"] += 1;
        				} else if (value.minutoOcorrencia > 75) {
        					golosFora["foraMin75_90"] += 1;
        				}
						jogosVersus.push([new Date(stat.jogo.dataJogo), -10, value.minutoOcorrencia]);
        			}
        		});
    		}
    	});
    	
    	var totalJogosVersus = resultados["totalJogosVersus"];
    	resultados["mediaGolosVersusCasa"] = (resultados["golosVersusCasa"] / totalJogosVersus);
    	resultados["mediaGolosVersusFora"] = (resultados["golosVersusFora"] / totalJogosVersus);
    	resultados["mediaGolosVersusTotal"] = (resultados["golosVersusTotal"] / totalJogosVersus);
    	
    	$("body").data("resultados", resultados);
    	$("body").data("golosCasa", golosCasa);
    	$("body").data("golosFora", golosFora);
    	$("body").data("jogosVersus", jogosVersus);
    }
</script>