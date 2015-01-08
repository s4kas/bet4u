<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="main">

<form:form action="#" modelAttribute="jogo" method="POST">
<table>
	<tr>
    	<td>
			<form:label path="epoca">Epoca</form:label>
		</td>
		<td>
			<form:input path="epoca" />
		</td>
		<td width="50"></td>
		<td>
			<form:label path="jornada">Jornada</form:label>
		</td>
		<td>
			<form:input path="jornada" />
		</td>
	</tr>
    <tr>
    	<td>
			<form:label path="equipaCasa.nome">Equipa Casa</form:label>
		</td>
		<td>
			<form:input class="equipa" path="equipaCasa.nome" />
		</td>
		<td width="50"></td>
		<td>
			<form:label path="equipaFora.nome">Equipa Visitante</form:label>
		</td>
		<td>
			<form:input class="equipa" path="equipaFora.nome" />
		</td>
	</tr>
	<tr>
    	<td>
			<form:label path="pontosCasa">Pontos Equipa Casa</form:label>
		</td>
		<td>
			<form:input path="pontosCasa" />
		</td>
		<td width="50"></td>
		<td>
			<form:label path="pontosFora">Pontos Equipa Visitante</form:label>
		</td>
		<td>
			<form:input path="pontosFora" />
		</td>
	</tr>
	<tr height="50">
	</tr>
	<tr>
		<td colspan="5">
			<input type="reset" value="Limpar">
            <input type="submit" value="Pesquisar"/>
        </td>
	</tr>
</table>
</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$( ".equipa" ).autocomplete({
	        source: '${pageContext. request. contextPath}/getNomesEquipas'
	    });
	});
</script>