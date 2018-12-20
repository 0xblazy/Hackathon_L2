package arbreGenealogique;

public class Principale{
    public static void main(String[] args){
		//variables locales
		Arbre2 a, a2,a3;
		Arbre2 afg,afd;
		
		Principale p = new Principale();
	
		a = new Arbre2();
		Arbre2 arbreUltime = new Arbre2(); // crÃ©e un arbre vide
	
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
    
    // SSALGO qui crée un arbre contenant l'ancêtre ultime en Racine et 2 fils vides
    public Arbre2 creationArbre(Personne ancetre_ultime) {
    	System.out.println("TEST");
    	Arbre2 a = new Arbre2();
    	a.COMPOSER(ancetre_ultime, new Arbre2(), new Arbre2());
    	return a;
    }
    
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

	private boolean personneEgal(Personne racine, Personne pers) {
		return (racine.getPrenom().equals(pers.getPrenom()) && racine.getNom().equals(pers.getNom()) && racine.getDateNaissance().equals(pers.getDateNaissance()));
	}
} // fin class
