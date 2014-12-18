package com.bmp.bet4u.common.resultado.jogo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.bmp.bet4u.common.equipa.Equipa;

public class Jogo {
	
	private SimpleDateFormat SDF = new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	private Calendar dataJogo;
	private Equipa equipaCasa;
	private Equipa equipaFora;
	
	public Jogo() {
		this.dataJogo = Calendar.getInstance();
		this.equipaCasa = new Equipa();
		this.equipaFora = new Equipa();
	}
	
	public Calendar getDataJogo() {
		return dataJogo;
	}
	public String getDataJogoSQL() {
		return SDF.format(dataJogo.getTime());
	}
	public void setDataJogo(Calendar dataJogo) {
		this.dataJogo = dataJogo;
	}
	public void setDataJogoSQL(String dataJogo) {
		try {
			this.dataJogo.setTime(SDF.parse(dataJogo));
		} catch (ParseException e) {
			//TODO EXC
		}
	}
	public Equipa getEquipaCasa() {
		return equipaCasa;
	}
	public void setEquipaCasa(Equipa equipaCasa) {
		this.equipaCasa = equipaCasa;
	}
	public Equipa getEquipaFora() {
		return equipaFora;
	}
	public void setEquipaFora(Equipa equipaFora) {
		this.equipaFora = equipaFora;
	}
	
	@Override
	public String toString() {
		return "Jogo [dataJogo=" + dataJogo + ", equipaCasa=" + equipaCasa
				+ ", equipaFora=" + equipaFora + "]";
	}
	
}
