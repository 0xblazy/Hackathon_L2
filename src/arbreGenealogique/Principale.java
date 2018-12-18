package arbreGenealogique;

public class Principale{
    public static void main(String[] args){
	//variables locales
	Arbre2 a, a2,a3;
	Arbre2 afg,afd;

	a=new Arbre2(); // crée un arbre vide
	System.out.println(a.VIDE()); //affiche true
	a.COMPOSER(12,new Arbre2(),new Arbre2());
	System.out.println(a.VIDE()); //affiche  false
	System.out.println(a.RACINE()); // affiche 12
	afg=new Arbre2();
	afd=new Arbre2();
	afg.COMPOSER(14,new Arbre2(),new Arbre2());
	afd.COMPOSER(16,new Arbre2(),new Arbre2());
	a2=new Arbre2();
	a2.COMPOSER(a.RACINE(),afg,afd);
	System.out.println(a2.RACINE()); // affiche 12
	System.out.println(a2.FG().RACINE()); // affiche 14
	a3=a2.FG();
	System.out.println(a3.RACINE()); // affiche 14
	if(a3.FG().VIDE())
	    System.out.println("a3 fils g vide");
	else
	    System.out.println(a3.FG().RACINE());
	// affiche "a3 fils g vide"
	System.out.println(a3.FEUILLE()); // affiche true
	
	
    } // fin main
} // fin class
