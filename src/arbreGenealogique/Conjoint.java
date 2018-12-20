package arbreGenealogique;

public class Conjoint {
	
	private String prenom;
	private String nom;
	private Date dateMariage;
	private boolean divorce;
	private Date dateDivorce;
	
	// s'il y a une date de divorce
	public Conjoint(String prenom, String nom, Date dateMariage, boolean divorce, Date dateDivorce) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setDateMariage(dateMariage);
		this.setDivorce(divorce);
		this.setDateDivorce(dateDivorce);
	}
	
	// pas de divorce
	public Conjoint(String prenom, String nom, Date dateMariage, boolean divorce) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setDateMariage(dateMariage);
		this.setDivorce(divorce);
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

	public Date getDateMariage() {
		return dateMariage;
	}

	public void setDateMariage(Date dateMariage) {
		this.dateMariage = dateMariage;
	}

	public boolean isDivorce() {
		return divorce;
	}

	public void setDivorce(boolean divorce) {
		this.divorce = divorce;
	}

	public Date getDateDivorce() {
		return dateDivorce;
	}

	public void setDateDivorce(Date dateDivorce) {
		this.dateDivorce = dateDivorce;
	}

}