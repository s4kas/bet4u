package com.bmp.bet4u.client;

import com.bmp.bet4u.espanha.EspanhaBusiness;
import com.bmp.bet4u.holanda.HolandaBusiness;
import com.bmp.bet4u.inglaterra.InglaterraBusiness;
import com.bmp.bet4u.portugal.PortugalBusiness;
import com.bmp.bet4u.suecia.SueciaBusiness;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InglaterraBusiness.parseAndInsertTodasEpocasPremierLeague();
		PortugalBusiness.parseAndInsertTodasEpocasPrimeiraLiga();
		HolandaBusiness.parseAndInsertTodasEpocasEredivisie();
		SueciaBusiness.parseAndInsertTodasEpocasAllsvenskan();		
		EspanhaBusiness.parseAndInsertTodasEpocasLaLiga();
	}
}
