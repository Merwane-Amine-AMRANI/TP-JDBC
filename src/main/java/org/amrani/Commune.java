package org.amrani;

public class Commune {

	private String codeINSEE;
	private String nomCommune;
	private int codePostal;
	private String libelleAcheminement;
	
	
	
	public Commune(String codeINSEE, String nomCommune, int codePostal, String libelleAcheminement) {
		this.codeINSEE = codeINSEE;
		this.nomCommune = nomCommune;
		this.codePostal = codePostal;
		this.libelleAcheminement = libelleAcheminement;
	}
	

	public String getCodeINSEE() {
		return codeINSEE;
	}

	public void setCodeINSEE(String codeINSEE) {
		this.codeINSEE = codeINSEE;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Commune [codeINSEE=" + codeINSEE + ", nomCommune=" + nomCommune + ", codePostal=" + codePostal
				+ ", libelleAcheminement=" + libelleAcheminement + "]";
	}

	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}

	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}

}
