package com.bmp.bet4u.common.exc;

public class Geral extends Exception {

	private static final long serialVersionUID = 8731515689589348085L;
	
	private String codErro;
	
	public Geral() {}
	
	public Geral(String codErro) {
		this.codErro = codErro;
	}

	public String getCodErro() {
		return codErro;
	}
	
}
