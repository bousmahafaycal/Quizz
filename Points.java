public class Points{

	//  La méthode de calcul est la suivante :
	// nombre de bonne reponses * pointBonneReponse - nombre de bonne reponses * pointMauvaiseReponse + bonusToutBon si il n'y a aucune fausse réponse 
	// - malusToutFaux si il n'y a aucune bonne réponse


	int bonusToutBon, pointBonneReponse, pointMauvaiseReponse, malusToutFaux;
	boolean negatif;

	public Points (int bonusToutBon) {
		this (bonusToutBon, 0,0,0,true);
	}

	public Points(int bonusToutBon, int pointBonneReponse){
		this(bonusToutBon,pointBonneReponse,0,0,true);
	}

	public Points(int bonusToutBon, int pointBonneReponse, int pointMauvaiseReponse){
		this(bonusToutBon,pointBonneReponse,pointMauvaiseReponse,0,true);
	}

	public Points(int bonusToutBon, int pointBonneReponse, int pointMauvaiseReponse, int malusToutFaux){
		this(bonusToutBon,pointBonneReponse,pointMauvaiseReponse,malusToutFaux,true);
	}

	public Points(int bonusToutBon, int pointBonneReponse, int pointMauvaiseReponse, int malusToutFaux, boolean negatif){
		this.bonusToutBon = bonusToutBon;
		this.malusToutFaux = malusToutFaux;
		this.pointBonneReponse = pointBonneReponse;
		this.pointMauvaiseReponse = pointMauvaiseReponse;
		this.negatif = negatif;
	}

	// Fonctions :
	/* 
	- int calculPoints(int bonneReponse, int mauvaiseReponse, int nbReponses);
	- String sauvegardePoints();
	- void lireSauvegarde(String chaine);
	- int getMaxPoints(int nbReponses);

	*/


	// calcul le nombre de points selon la formule donnée tout  en haut
	public int calculPoints (int bonneReponse, int mauvaiseReponse, int nbReponses){
		if (bonneReponse < 0 || mauvaiseReponse < 0 || nbReponses <= 0)
			return 0;
		int points = bonneReponse * pointBonneReponse;
		points -= mauvaiseReponse * pointMauvaiseReponse;
		if (bonneReponse == nbReponses)
			points += bonusToutBon;
		else if(mauvaiseReponse == nbReponses)
			points -= malusToutFaux;
		if ( ! negatif && points < 0)
			points = 0;

		return points;

	}

	// permet de lire la sauvegarde ce qui permet d'ouvrir l'objet lié à la sauvegarde
	public void lireSauvegarde(String chaine){
		bonusToutBon  = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"BonusToutBon",1,"BonusToutBon",false));
		String chaineCT = Outils.recupereBaliseAuto(chaine,"Negatif",1,"Negatif",false);
		
		malusToutFaux =   Integer.parseInt(Outils.recupereBaliseAuto(chaine,"MalusToutFaux",1,"MalusToutFaux",false));
		pointBonneReponse = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"PointBonneReponse",1,"PointBonneReponse",false));
		pointMauvaiseReponse =  Integer.parseInt(Outils.recupereBaliseAuto(chaine,"PointMauvaiseReponse",1,"PointMauvaiseReponse",false));
		negatif = Boolean.parseBoolean(chaineCT);
	}

	// Permet de faire une chaine de caractère décrivant l'objet.
	public String sauvegardePoints(){
		String chaine = Outils.constitueBalise("Negatif",String.valueOf(negatif));
		chaine += Outils.constitueBalise("BonusToutBon",String.valueOf(bonusToutBon));
		chaine += Outils.constitueBalise("MalusToutFaux",String.valueOf(malusToutFaux));
		chaine +=  Outils.constitueBalise("PointBonneReponse",String.valueOf(pointBonneReponse));
		chaine += Outils.constitueBalise("PointMauvaiseReponse",String.valueOf(pointMauvaiseReponse));
		chaine = Outils.constitueBalise("Points",chaine);
		return chaine;
	}

	public int getMaxPoints(int nbReponses){
		return (bonusToutBon +  nbReponses * pointBonneReponse);
	}


}