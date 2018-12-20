package arbreGenealogique;

public class Principale{

	private Arbre2 arbreUltime;

	public static void main(String[] args){
		
		Principale p = new Principale();

		Personne p1 = new Personne("Anne", "Laprevote", new Date(15, 9, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p2 = new Personne("Tom", "Wysocki", new Date(15, 10, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p3 = new Personne("Antoine", "Bentini", new Date(15, 3, 1999), "commune", false, new Conjoint[0], true, "info");
		Personne p4 = new Personne("Elise", "Perrot", new Date(26, 5, 1999), "commune", false, new Conjoint[0], true, "info");
		Personne p5 = new Personne("Nicolas", "Carbonnier", new Date(19,8,1999),"commune", false, new Conjoint[0], true, "info");
		Personne p6 = new Personne("Mickeal", "Jackson", new Date(1,1,2000) , "commune" , true, new Date(2,2,2002), "communeMort", new Conjoint[0], true, "info");


		Conjoint c = new Conjoint("Théo", "Martin", new Date(20,3,1836), false);

		p.arbreUltime = p.creationArbre(p1);
		p.ajoutConjoint(c, p.arbreUltime, p1);

		p.ajoutEnfant(p2, p1, p.arbreUltime);
		p.ajoutEnfant(p3, p1, p.arbreUltime);
		p.ajoutEnfant(p4, p2, p.arbreUltime);
		p.ajoutEnfant(p5, p3, p.arbreUltime);
		p.ajoutEnfant(p6, p5, p.arbreUltime);
		System.out.println(p.arbreUltime);
	} // fin main

	// SSALGO #1 CreationArbre(ancetre_ultime)
	public Arbre2 creationArbre(Personne ancetre_ultime) {
		Arbre2 a = new Arbre2();
		a.COMPOSER(ancetre_ultime, new Arbre2(), new Arbre2());
		return a;
	}

	// SSALGO #2 AjoutConjoint(conjoint, arbre, pers)
	public void ajoutConjoint(Conjoint c, Arbre2 arbreGen, Personne pers) {
		int taille = pers.getConjoint().length + 1;

		Conjoint[] newTab = new Conjoint[taille];
		boolean trouve = false;
		Arbre2 arbreTemp = new Arbre2();

		if (!arbreGen.VIDE()) {
			arbreTemp = arbreGen;
			while(!arbreTemp.VIDE() && !trouve) {
				trouve = personneEgal(arbreTemp.RACINE(), pers);
				arbreTemp = arbreTemp.FD();
			}

			if (!trouve) {
				while (!arbreGen.VIDE()) {
					ajoutConjoint(c, arbreGen.FG(), pers);
				}
			} else {
				if (pers.getConjoint().length>0) {
					for (int i = 0; i < taille; i++) {
						System.out.println(i);
						newTab[i] = pers.getConjoint()[i];
					}
				}
				newTab[taille-1] = c;
				pers.setConjoint(newTab);
			}
		}

	}

	// SSALGO #3 AjoutEnfant(enfant, parent, arbre)
	private void ajoutEnfant(Personne enfant, Personne parent, Arbre2 a) {
		Arbre2 fils, arbreTemp;

		if (!a.VIDE()) {
			//System.out.println(this.arbreUltime.RACINE().getNom());
			//System.out.println(personneEgal(this.arbreUltime.RACINE(), parent));
			if (personneEgal(a.RACINE(), parent)) {
				fils = new Arbre2();
				fils.COMPOSER(enfant, new Arbre2(), a.FG());
				//this.arbreUltime = ajouterEnfantDroite(arbreUltime, enfant, parent);
				a.COMPOSER(a.RACINE(), fils, a.FD());
			} else {
				arbreTemp = new Arbre2();
				arbreTemp = a.FG();
				ajoutEnfant(enfant, parent, arbreTemp);
				a.COMPOSER(a.RACINE(), arbreTemp, a.FD());
				arbreTemp = a.FD();
				ajoutEnfant(enfant, parent, arbreTemp);
				a.COMPOSER(a.RACINE(), a.FG(), arbreTemp);
			}
		}
	}

	// SSALGO #4 RechercheExistence(pers, arbre)
	private boolean RechercheExistence(Arbre2 arbre, Personne p1){
		if (arbre.VIDE()){
			return false;
		} else {
			if(arbre.RACINE().getPrenom().equals(p1.getPrenom()) && arbre.RACINE().getNom().equals(p1.getNom())){
				return true;
			} else {
				if(this.RechercheExistence(arbre.FG(),p1)) {
					return true;
				} else {
					return this.RechercheExistence(arbre.FD(), p1);
				}
			}
		}
	}

	// SSALGO #5 RechercheVivants(arbre, tab, i)
	private void RechercheVivants(Arbre2 arbre, Personne[] tab, int i){
		if (!arbre.VIDE()){
			if (!arbre.RACINE().isMort()){
				tab[i]=arbre.RACINE();
				i++;
			}
			RechercheVivants(arbre.FD(),tab,i);
			RechercheVivants(arbre.FG(),tab,i);
		}
	}


	// SSALGO #7 AffichageEnfantsN(pers, n, arbre)
	public void AffichageEnfantsN (Personne pers, Arbre2 arbre, int n) {


		Arbre2 arbretemp;
		if (! arbre.VIDE ()){   //moi aussi déçue

			if (personneEgal (arbre.RACINE(), pers)) { 	 //cas où la personne donnée est dans la racine

				arbretemp = arbre.FG(); 
				if (n==1){					//si 
					AffichageEnfantsN (pers, arbre, n);
				}
				else {

					while (! arbretemp.VIDE()){
						AffichageEnfantsN (arbretemp.RACINE(), arbretemp , n - 1);
						arbretemp = arbretemp.FD();
					}
				}

				AffichageEnfantsN (pers, arbre.FG(), n);
				AffichageEnfantsN (pers, arbre.FD(), n);

			}
		}
	}

// SSALGO NbMembre(a)
private int nbMembre(Arbre2 a) {
	if(a.VIDE()) {
		return 0;
	} else {
		return 1 + this.nbMembre(a.FG()) + this.nbMembre(a.FD());
	}
}

// SSALGO DateEgale(d1, d2)
private boolean dateEgale(Date d1, Date d2) {
	return (this.nbJours(d1) == this.nbJours(d2));
}

// SSALGO PersonneEgale(p1,p2)
private boolean personneEgal(Personne racine, Personne pers) {
	return (racine.getPrenom().equals(pers.getPrenom()) && racine.getNom().equals(pers.getNom()) && this.dateEgale(racine.getDateNaissance(), pers.getDateNaissance()));
}

// SSALGO FilsDroit(arbre)
private void filsDroit(Arbre2 arbre) {
	if(!arbre.VIDE()) {
		System.out.println(arbre.FD().RACINE());
		this.filsDroit(arbre.FD());
	}
}

// SSALGO NbFreres(arbre)
private int nbFreres(Arbre2 arbre) {
	if(!arbre.VIDE()) {
		return 1 + this.nbFreres(arbre.FD());
	} else {
		return 0;
	}
}

// SSALGO NbJours(date)
private int nbJours(Date date) {
	int res = 0;
	res += date.getAnnee() * 365.25 + date.getJour();

	for (int i = 1 ; i <= date.getMois() ; i++) {
		if(i == 2) {
			if (date.getAnnee()%4 == 0) {
				res += 29;
			} else {
				res += 28;
			}
		} else if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
			res += 31;
		} else {
			res += 30;
		}
	}
	return res;
}

// SSALGO EnfantDeRacine(arbre, pers)
private boolean enfantDeRacine(Arbre2 arbre, Personne pers) {
	arbre = arbre.FG();
	boolean enfant = false;

	while(!arbre.VIDE() && !enfant) {
		enfant = this.personneEgal(arbre.RACINE(), pers);
		arbre = arbre.FD();
	}

	return enfant;
}

// SSALGO PetitEnfantDeRacine(arbre, pers)
private boolean petitEnfantDeRacine(Arbre2 arbre, Personne pers) {
	arbre = arbre.FG();
	boolean petitEnfant = false;

	while(!arbre.VIDE() && !petitEnfant) {
		petitEnfant = this.enfantDeRacine(arbre, pers);
		arbre = arbre.FD();
	}

	return petitEnfant;
}

// SSALGO PlusGrandEnfant(pers, arbre)
private Personne plusGrandEnfant(Personne pers, Arbre2 arbre) {
	Personne plusgrand = null;
	if(!arbre.VIDE()) {
		if(this.personneEgal(arbre.RACINE(), pers)) {
			arbre = arbre.FG();
			while(!arbre.VIDE()) {
				if(this.nbJours(arbre.RACINE().getDateNaissance()) > this.nbJours(plusgrand.getDateNaissance())) {
					plusgrand = arbre.RACINE();
				}
				arbre = arbre.FD();
			}
		}
		plusgrand = this.plusGrandEnfant(pers, arbre.FG());
		if(plusgrand == null) {
			plusgrand = this.plusGrandEnfant(pers, arbre.FD());
		}
	}
	return plusgrand;
}
} // fin class
