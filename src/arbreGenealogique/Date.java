package arbreGenealogique;

public class Date {
	
	private int jour;
	private int mois;
	private int annee;
	
	public Date(int jour, int mois, int annee) {
		this.setJour(jour);
		this.setMois(mois);
		this.setAnnee(annee);
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	// DateEgale dans les sous algos
	public boolean equals(Date date) {
		return (this.getJour() == date.getJour() && this.getMois() == date.getMois() && this.getAnnee() == date.getAnnee());
		
	}

}
