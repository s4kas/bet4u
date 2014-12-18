package com.bmp.bet4u.client.estatisticas;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bmp.bet4u.client.estatisticas.beans.Jogo;
import com.bmp.bet4u.client.estatisticas.beans.ResultadoEpocaJornada;
import com.bmp.bet4u.client.estatisticas.business.EstatisticasBusiness;
import com.bmp.bet4u.common.equipa.EquipaBusiness;

@Controller
@RequestMapping(value = "/")
public class EstatisticasController {

	@RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
		model.addAttribute("jogo", new Jogo());
		return "estatisticas.get";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("jogo") Jogo jogo, Model model) {
		List<ResultadoEpocaJornada> estatisticas = EstatisticasBusiness.getEstatisticasByJogoOuEquipa(jogo);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(estatisticas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("estatisticas", json);
		return "estatisticas.list";
    }
	
	@RequestMapping(value = "/getNomesEquipas", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody List<String> getNomesEquipas(@RequestParam("term") String query) {
		List<String> listaEquipas = EquipaBusiness.getNomesEquipasComecadasPor(query);
 
		return listaEquipas;
	}
}
