class ReponseEntier extends Reponse {
	int tolerance;
	int reponse;
	public ReponseEntier(int reponse){
		this(reponse,0);
	}

	public ReponseEntier(int reponse, int tolerance){
		this.reponse = reponse;
		this.tolerance = tolerance;
	}


	public  int nbBonneReponse(int reponseUser){
		if (reponseUser >=  reponse - tolerance && reponseUser <= reponse + tolerance )
			return 1;
		return 0;
	}

	public  int nbReponses (){
		return 1;
	}

	public  boolean bonneReponse(int reponseUser){
		if (nbBonneReponse(reponseUser) == nbReponses())
			return true;
		return false;
	}

	public String [] getTabReponse(){
		String tab [] = new String [1];
		tab[0] = String.valueOf(reponse);
		return tab;
	}
}