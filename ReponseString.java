class ReponseString extends Reponse {
	String  reponse;
	boolean casse;
	public ReponseString (String reponse){
		this(reponse,false);
	}

	public ReponseString (String reponse,boolean casse){
		this.reponse = reponse;
		this.casse = casse;
	}

	public  int nbBonneReponse(String reponseUser){
		String reponse2 = this.reponse;
		if (!casse){
			reponseUser = reponseUser.toLowerCase();
			reponse2 = reponse2.toLowerCase();
		}


		if (reponseUser.equals(reponse2))
			return 1;
		return 0;
	}

	public  int nbReponses (){
		return 1;
	}

	public  boolean bonneReponse(String  reponseUser){
		if (nbBonneReponse(reponseUser) == nbReponses())
			return true;
		return false;
	}

	public String [] getTabReponse(){
		String tab [] = new String [1];
		tab[0] = reponse;
		return tab;
	}
	
}