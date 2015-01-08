package com.bmp.bet4u.client.estatisticas.business;

public class TotalPontosKey implements Comparable<TotalPontosKey> {

	private String epoca;
	private Integer equipa;
	
	public String getEpoca() {
		return epoca;
	}
	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	public Integer getEquipa() {
		return equipa;
	}
	public void setEquipa(Integer equipa) {
		this.equipa = equipa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((epoca == null) ? 0 : epoca.hashCode());
		result = prime * result + ((equipa == null) ? 0 : equipa.hashCode());
		return result;
	}
	@Override
	public int compareTo(TotalPontosKey o) {
		if (o == null) {
			return 1;
		}
		
		TotalPontosKey other = (TotalPontosKey)o;
		if (epoca.compareTo(other.epoca) > 0) {
			return 1;
		} else if (epoca.compareTo(other.epoca) == 0) {
			return equipa.compareTo(other.equipa);
		} else {
			return -1;
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TotalPontosKey [epoca=").append(epoca)
				.append(", equipa=").append(equipa).append("]");
		return builder.toString();
	}
}
