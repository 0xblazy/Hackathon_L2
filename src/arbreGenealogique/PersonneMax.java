package arbreGenealogique;

public class PersonneMax {
	private String prenom, nom;
	private int max;
	
	public PersonneMax(String prenom, String nom, int max) {
		this.prenom = prenom;
		this.nom = nom;
		this.max = max;
	}
	
	public String toString() {
		return this.nom + " " + this.prenom + " (max : " + this.max + ")";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	
}
