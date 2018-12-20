package arbreGenealogique;

public class Principale{
    public static void main(String[] args){
		//variables locales
		Arbre2 a, a2,a3;
		Arbre2 afg,afd;
		
		Principale p = new Principale();
	
		a = new Arbre2();
		Arbre2 arbreUltime = new Arbre2(); // crée un arbre vide
	
		// MOI
		Personne p1 = new Personne("ant", "ben", new Date(15, 9, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p2 = new Personne("aqsdnt", "besqdn", new Date(15, 2, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p3 = new Personne("ant", "ben", new Date(15, 9, 843), "commune", false, new Conjoint[0], true, "info");
		Personne p4 = new Personne("ant", "ben", new Date(26, 9, 1998), "commune", false, new Conjoint[0], true, "info");
		
		Conjoint c = new Conjoint("ok", "zkj", new Date(20,3,1836), false);
		
		arbreUltime = p.creationArbre(p1);
		p.ajoutConjoint(c, arbreUltime, p1);
		// COMPOSER(personne_a_ajouter, fils, frere)
		
		/*System.out.println(a.VIDE()); //affiche true
		a.COMPOSER(p1,new Arbre2(),new Arbre2());
		System.out.println(a.VIDE()); //affiche  false
		System.out.println(a.RACINE()); // affiche 12
		afg=new Arbre2();
		afd=new Arbre2();
		afg.COMPOSER(p1,new Arbre2(),new Arbre2());
		afd.COMPOSER(p1,new Arbre2(),new Arbre2());
		a2=new Arbre2();
		a2.COMPOSER(a.RACINE(),afg,afd);
		System.out.println(a2.RACINE().getNom()); // affiche 12
		System.out.println(a2.FG().RACINE()); // affiche 14
		a3=a2.FG();
		System.out.println(a3.RACINE()); // affiche 14
		if(a3.FG().VIDE())
		    System.out.println("a3 fils g vide");
		else
		    System.out.println(a3.FG().RACINE());
		// affiche "a3 fils g vide"
		System.out.println(a3.FEUILLE()); // affiche true
		
		
		boolean test = new Date(15, 9, 1998).equals(new Date(15,9,1998));
		
		System.out.println("TEST " + test);*/
    } // fin main
    
    // SSALGO #1 CreationArbre(ancetre_ultime)
    public Arbre2 creationArbre(Personne ancetre_ultime) {
    	System.out.println("TEST");
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
    	    	System.out.println("taile + 1 " + taille + "taille normale" + pers.getConjoint().length);
    			newTab[taille-1] = c;
    			pers.setConjoint(newTab);
    		}
    	}
    	
    }
    
    // SSALGO #3 AjoutEnfant(enfant, parent, arbre)
	
	// SSALGO AffichageMembre(a)
	private void affichageMembre(Arbre2 a) {
		if (!a.VIDE()) {
			System.out.println(a.RACINE());
		}
		this.affichageMembre(a.FG());
		this.affichageMembre(a.FD());
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
		return (racine.getPrenom().equals(pers.getPrenom()) && racine.getNom().equals(pers.getNom()) && racine.getDateNaissance().equals(pers.getDateNaissance()));
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
