package arbreGenealogique;


public class Principale{

	private Arbre2 arbreUltime;
	private Personne[] tab = new Personne[4];
	private int i = 0;
	public static void main(String[] args){

		Principale p = new Principale();

		Personne p1 = new Personne("Anne", "Laprevote", new Date(15, 9, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p2 = new Personne("Tom", "Wysocki", new Date(15, 10, 1998), "commune", false, new Conjoint[0], true, "info");
		Personne p3 = new Personne("Antoine", "Bentini", new Date(15, 3, 1999), "commune", false, new Conjoint[0], true, "info");
		Personne p4 = new Personne("Elise", "Perrot", new Date(26, 5, 1999), "commune", false, new Conjoint[0], true, "info");
		Personne p5 = new Personne("Nicolas", "Carbonnier", new Date(19,8,1999),"commune", false, new Conjoint[0], true, "info");
		Personne p6 = new Personne("Mickeal", "Jackson", new Date(1,1,2000) , "commune" , true, new Date(2,2,2002), "communeMort", new Conjoint[0], true, "info");

		Personne pInex = new Personne("No", "Body", new Date(0,0,0), "commune", false, new Conjoint[0], true, "info");

		Conjoint c = new Conjoint("Théo", "Martin", new Date(20,3,1836), false);

		p.arbreUltime = p.creationArbre(p1);
		p.ajoutConjoint(c, p.arbreUltime, p1);

		p.ajoutEnfant(p2, p1, p.arbreUltime);
		p.ajoutEnfant(p3, p1, p.arbreUltime);
		p.ajoutEnfant(p4, p2, p.arbreUltime);
		p.ajoutEnfant(p5, p3, p.arbreUltime);
		p.ajoutEnfant(p6, p5, p.arbreUltime);
		System.out.println(p.arbreUltime);
		
		System.out.println();
		
		// rechercheExistence
		System.out.println("Test rechercheExistence : \n  -Test 1 : " + p.rechercheExistence(p3, p.arbreUltime) 
			+ "\n  -Test 2 : " + p.rechercheExistence(pInex, p.arbreUltime));
		System.out.println();
		
		// rechercheVivant
		p.rechercheVivants(p.arbreUltime, p.tab, p.i);
		System.out.println("Test rechercheVivants");
		for(int i = 0 ; i < p.tab.length ; i++) {
			System.out.println(" -" + p.tab[i]);
		}
		System.out.println();
		
		// affichageEnfants
		System.out.println("Test affichageEnfants");
		p.affichageEnfants(p1, p.arbreUltime);
		System.out.println();
		
	}

	// SSALGO #1 CreationArbre(ancetre_ultime)
	private Arbre2 creationArbre(Personne ancetre_ultime) {
		Arbre2 a = new Arbre2();
		a.COMPOSER(ancetre_ultime, new Arbre2(), new Arbre2());
		return a;
	}

	// SSALGO #2 AjoutConjoint(conjoint, arbre, pers)
	private void ajoutConjoint(Conjoint c, Arbre2 arbreGen, Personne pers) {
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
	private boolean rechercheExistence(Personne p1, Arbre2 arbre){
		if (arbre.VIDE()){
			return false;
		} else {
			if(arbre.RACINE().getPrenom().equals(p1.getPrenom()) && arbre.RACINE().getNom().equals(p1.getNom())){
				return true;
			} else {
				if(this.rechercheExistence(p1, arbre.FG())) {
					return true;
				} else {
					return this.rechercheExistence(p1, arbre.FD());
				}
			}
		}
	}

	// SSALGO #5 RechercheVivants(arbre, tab, i)
	private void rechercheVivants(Arbre2 arbre, Personne[] tab, int i){
		if (!arbre.VIDE()){
			if (!arbre.RACINE().isMort()){
				tab[i]=arbre.RACINE();
				i++;
			}
			rechercheVivants(arbre.FD(),tab,i);
			rechercheVivants(arbre.FG(),tab,i);
		}
	}

	// SSALGO #6 AffichageEnfants(pers, arbre)
	private void affichageEnfants(Personne pers, Arbre2 arbre){
		if (!arbre.VIDE()){
			if (personneEgal(arbre.RACINE(), pers)){
				if (!arbre.FG().VIDE()){
					System.out.println(arbre.FG().RACINE());
				}
				if(!arbre.FG().FD().VIDE()){
					filsDroit(arbre.FG());
				}
			}
		}
	}


	// SSALGO #7 AffichageEnfantsN(pers, n, arbre)
	private void affichageEnfantsN (Personne pers, Arbre2 arbre, int n) {
		Arbre2 arbretemp;
		if (! arbre.VIDE ()){   //moi aussi déçue

			if (personneEgal (arbre.RACINE(), pers)) { 	 //cas où la personne donnée est dans la racine

				arbretemp = arbre.FG(); 
				if (n==1){					//si 
					this.affichageEnfantsN (pers, arbre, n);
				}
				else {

					while (! arbretemp.VIDE()){
						this.affichageEnfantsN (arbretemp.RACINE(), arbretemp , n - 1);
						arbretemp = arbretemp.FD();
					}
				}

				this.affichageEnfantsN (pers, arbre.FG(), n);
				this.affichageEnfantsN (pers, arbre.FD(), n);

			}
		}
	}


	// SSALGOs pour Fonzi
	private int nbEnfants (Arbre2 a){
		int enfants=0;
		if (a.FG().VIDE()){
			return enfants;
		}
		else{
			enfants = enfants ++;
			enfants = enfants + nbFreres(a.FG());
		}
		return enfants;
	}

	public int Maximum (Arbre2 arbre){
		int max; 
		int x; 
		int v1; 
		int v2; 

		if (!arbre.VIDE()){
			max = this.nbEnfants(arbre);
			if (!arbre.FG().VIDE()) {
				x = Maximum (arbre.FG()); 
				if (x > max) {
					max = x; 
				}
			}
			v1=max;
			if (!arbre.FD().VIDE()) {       
				x = Maximum (arbre.FD());
				if (x > max) {
					max = x; 

				}

			}
			v2=max;
			if (v1> v2) {
				return v1;
			} else {
				return v2;
			}
		} else {
			return 0 ; 
		}   
	}

	// SSALGO #10 AffichageCousin(arbre, pers)
	private void affichageCousin(Arbre2 a, Personne pers) {
		Arbre2 arbreTemp;
		boolean trouve;

		if(!a.VIDE()) {
			arbreTemp = new Arbre2();	
			arbreTemp = a;
			trouve = false;

			while (!arbreTemp.VIDE() && !trouve) {
				trouve = enfantDeRacine(arbreTemp, pers);
				arbreTemp = arbreTemp.FD();
			}

			if (trouve) {
				while (!a.VIDE()) {
					if (enfantDeRacine(a, pers))
						this.affichageEnfants(a.RACINE(), a);
					a = a.FD();
				}
			}
		}
	}

	// SSALGO #11 IntervalleNaissance(date1, date2, arbre)
	private void intervalleNaissance(Date date1, Date date2, Arbre2 a) {
		if (!a.VIDE()) {
			if (nbJours(a.RACINE().getDateNaissance()) >= nbJours(date1) && nbJours(a.RACINE().getDateNaissance()) <= nbJours(date2)) {
				System.out.println(a.RACINE());
			}
			intervalleNaissance(date1, date2, a.FD());
			intervalleNaissance(date1, date2, a.FG());
		}
	}

	// SSALGO #12 LienParente(arbre, pers1, pers2)
	private boolean lienParente(Arbre2 a, Personne pers1, Personne pers2) {
		boolean lien; 

		if (a.VIDE()) 
			return false;
		else {
			lien = false;
			if (personneEgal(a.RACINE(), pers1))
				lien = this.rechercheExistence(pers2, a);
			else {
				if (personneEgal(a.RACINE(), pers2))
					lien = this.rechercheExistence(pers1,a);
				else
					lien = (this.rechercheExistence(pers1, a) || this.rechercheExistence(pers2, a));
			}
			return lien;
		}
	}

	// SSALGO #13 VieuxCousin1(arbre, pers)
	private void vieuxCousin1 (Arbre2 a, Personne pers) {
		Arbre2 arbreTemp;
		boolean trouve;
		Personne max;

		if (!a.VIDE()) {
			arbreTemp = new Arbre2();
			arbreTemp = a;
			trouve = false;

			while (!arbreTemp.VIDE() && !trouve) {
				trouve = enfantDeRacine(arbreTemp, pers);
				arbreTemp = arbreTemp.FD();
			}

			if (trouve) {
				max = plusGrandEnfant(a.RACINE(), a);
				while (!a.VIDE()) {
					if (!enfantDeRacine(a, pers)) {
						if (nbJours(plusGrandEnfant(a.RACINE(), a).getDateNaissance()) > nbJours(max.getDateNaissance())) {
							max = plusGrandEnfant(a.RACINE(), a);
						}
					}
					a = a.FD();
				}
				System.out.println(max);
			} else {
				while (!a.VIDE()) {
					vieuxCousin1(a.FG(), pers);
					a = a.FD();
				}
			}
		}		
	}
	
	// SSALGO #14 VieuxCousin2(arbre, pers)
	private void vieuxCousin2(Arbre2 a, Personne pers) {
		Arbre2 arbreTemp;
		boolean trouve;
		Personne max;
		
		if (!a.VIDE()) {
			arbreTemp = new Arbre2();
			arbreTemp = a;
			trouve = false;
			
			while (!arbreTemp.VIDE() && !trouve) {
				trouve = petitEnfantDeRacine(arbreTemp, pers);
				arbreTemp = arbreTemp.FD();
			}
			
			if (trouve) {
				max = plusGrandEnfant(a.FG().RACINE(), a.FG());
				while (!a.VIDE()) {
					arbreTemp = arbreTemp.FG();
					while (!arbreTemp.VIDE()) {
						if (!enfantDeRacine(a, pers)) {
							if (nbJours(plusGrandEnfant(arbreTemp.RACINE(), arbreTemp).getDateNaissance()) > nbJours(max.getDateNaissance())) {
								max = plusGrandEnfant(arbreTemp.RACINE(), arbreTemp);
							}
						}
						arbreTemp = arbreTemp.FD();
					}
					arbreTemp = arbreTemp.FD();
				}
			} else {
				while (!a.VIDE()) {
					vieuxCousin2(a.FG(), pers);
					a = a.FD();
				}
			}
			
		}
	}
	
	// SSALGO #16 Incoherences(arbre)
	private void incoherences(Arbre2 arbre) {
        Personne fils, pere; 
        Arbre2 arbreTemp;
        
        if (!arbre.VIDE()){
            pere = (arbre.RACINE());
            arbreTemp = (arbre.FG());
            while (!arbreTemp.VIDE()){
                fils = arbreTemp.RACINE();
                if ((this.nbJours(pere.getDateNaissance()))< (this.nbJours(fils.getDateNaissance()))){
                    System.out.println("Incohérence entre " + pere + " et " + fils );
                }
                   arbreTemp = arbreTemp.FD();
            }
            this.incoherences(arbre.FG());
            this.incoherences(arbre.FD());
            
        }
        
    }
	
	// SSALGO #18 Heritiers(pers, arbre)
	private void heritiers(Personne pers, Arbre2 a) {
		Personne pere;
		Arbre2 arbreTemp;
		boolean trouve;
		
		if (!a.VIDE()) {
			if(personneEgal(a.RACINE(), pers)) {
				arbreTemp = a;
				arbreTemp = new Arbre2();
				
				while(!arbreTemp.VIDE()) {
					if (arbreTemp.RACINE().isMort()) {
						heritiers(arbreTemp.RACINE(), arbreTemp);
					} else {
						System.out.println(arbreTemp.RACINE());
					}
					arbreTemp = arbreTemp.FD();
				}
			} else {
				heritiers(pers, a.FG());
				heritiers(pers, a.FD());
			}
		}
	}
	
	// SSALGO #19 SansDescendance(arbre)
	private void sansDescendance(Arbre2 arbre){
        if (!arbre.VIDE()){
            if (!arbre.FG().VIDE()){
                this.sansDescendance(arbre.FG());
            }else{
                System.out.println(arbre.RACINE()+ " n'a pas de descendance!");
            }
            if (!arbre.FD().VIDE()){
                 this.sansDescendance(arbre.FD());
            }
        }
    }
	
	// SSALGO #20 Conjoints(pers)
	private void conjoint (Personne pers){
        for (int i = 0 ; i <= pers.getConjoint().length; i++) {
            System.out.println(pers.getConjoint()[i]);
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
