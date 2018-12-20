package arbreGenealogique;

public class Arbre2{
    private Personne r; //attribut qui représente la racine, peut être composé de plusieurs attributs et/ou d'attributs d'autres types
    private Arbre2 fg;
    private Arbre2 fd;
    
    public Arbre2(){    //constructeur, primitive CREER
    	r = null; // valeur connue par avance qui détermine le fait que l'arbre est vide. Peut être modifiée par vos soins et en fonction du sujet.
    }


    //Primitives
    //VIDE
    Boolean VIDE(){
    	return(this.r == null); // par convention ici on considère qu'un arbre est vide si sa racine=-10000
    }
    //FG
    Arbre2 FG(){
    	return(this.fg);
    }

    //FD
    Arbre2 FD(){
    	return(this.fd);
    }

    // RACINE
    Personne RACINE(){
    	return this.r;
    }
    
	void COMPOSER(Personne pr, Arbre2 pg, Arbre2 pd) {
		this.r=pr;
		this.fg=pg;
		this.fd=pd;
    }
   // autres méthodes
    
   boolean FEUILLE(){
       return(this.fg.VIDE() && this.fd.VIDE()); // a utiliser uniquement si on est sur que l'arbre n'est pas vide
   }

   // Correspond au sous algo AffichageMembre(a) => ICI a est l'objet Arbre2 qui appelle la m�thode
   public String toString() {		
	   Arbre2 arbreTemp = new Arbre2();
	   String s = "";
	   if (this.RACINE() != null) s += this.RACINE() + " :";
	   arbreTemp = this.FG();
	   while(arbreTemp != null) {
		   if(arbreTemp.RACINE() != null) {
			   s += " -" + arbreTemp.RACINE();
		   } else {
			   s += "\n";
		   }
		   arbreTemp = arbreTemp.FD();
	   }
	   arbreTemp = this.FG();
	   while(arbreTemp != null) {
		   if(arbreTemp.RACINE() != null) s += arbreTemp;			   
		   arbreTemp = arbreTemp.FD();
	   }
	   return s;
	}
} // fin classe
