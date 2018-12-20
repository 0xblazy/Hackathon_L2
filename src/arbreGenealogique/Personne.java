package arbreGenealogique;

public class Personne {
	
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String communeNaissance;
	private boolean mort;
		private Date dateMort;
		private String communeEnterrement;
	private Conjoint[] conjoint;
	private boolean homme;
	private String profession;
	
	// si la personne est morte :
	public Personne(String prenom, String nom, Date dateNaissance, String communeNaissance, boolean mort,
					Date dateMort, String communeEnterrement, Conjoint[] conjoint, boolean homme, String profession) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setDateNaissance(dateNaissance);
		this.setCommuneNaissance(communeNaissance);
		this.setMort(mort);
		this.setDateMort(dateMort);
		this.setCommuneEnterrement(communeEnterrement);
		this.setConjoint(conjoint);
		this.setHomme(homme);
		this.setProfession(profession);
	}
	 
	// si la personne n'est pas morte
	public Personne(String prenom, String nom, Date dateNaissance, String communeNaissance, boolean mort, Conjoint[] conjoint, boolean homme, String profession) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setDateNaissance(dateNaissance);
		this.setCommuneNaissance(communeNaissance);
		this.setMort(mort);
		this.setConjoint(conjoint);
		this.setHomme(homme);
		this.setProfession(profession);
	}
	
	// Correspond au sous algo AffichagePersonne(pers) => ICI pers est l'objet Personne qui appelle la méthode
	public String toString() {
		return this.nom + " " + this.prenom;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCommuneNaissance() {
		return communeNaissance;
	}

	public void setCommuneNaissance(String communeNaissance) {
		this.communeNaissance = communeNaissance;
	}

	public boolean isMort() {
		return mort;
	}

	public void setMort(boolean mort) {
		this.mort = mort;
	}

	public Date getDateMort() {
		return dateMort;
	}

	public void setDateMort(Date dateMort) {
		this.dateMort = dateMort;
	}

	public String getCommuneEnterrement() {
		return communeEnterrement;
	}

	public void setCommuneEnterrement(String communeEnterrement) {
		this.communeEnterrement = communeEnterrement;
	}

	public Conjoint[] getConjoint() {
		return conjoint;
	}

	public void setConjoint(Conjoint[] conjoint) {
		this.conjoint = conjoint;
	}

	public boolean isHomme() {
		return homme;
	}

	public void setHomme(boolean homme) {
		this.homme = homme;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
