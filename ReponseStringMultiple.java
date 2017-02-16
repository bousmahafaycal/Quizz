class ReponseStringMultiple extends Reponse  {
	String [] reponse;
	boolean ordre, casse;
	public ReponseStringMultiple(String [] reponse){
		this(reponse,false,false);
	}

	public ReponseStringMultiple(String [] reponse, boolean ordre){
		this(reponse,ordre,false);
	}
	public ReponseStringMultiple(String [] reponse,boolean ordre, boolean casse){
		this.reponse = reponse;
		this.ordre = ordre;
		this.casse = casse;
	}

	public  int nbBonneReponse(String [] reponseUser){
		int nb = 0;
		boolean compter ;
		//Outils.afficheTableau(reponseUser, "reponseUser");
		//Outils.afficheTableau(reponse,"reponse");
		if (ordre){
			if (reponseUser.length == reponse.length){
				for (int i = 0; i < reponse.length ; i++){
					if (casse && reponseUser[i].equals(reponse[i]))
						nb++;
					else if ( (! casse) && reponseUser[i].toLowerCase().equals(reponse[i].toLowerCase()))
						nb++; 
				}
			}
				
		}
		else{
			for(int i = 0 ; i < reponse.length; i++){
				compter = true;
				for (int i2 = 0; i2 < reponseUser.length; i2++){
					//System.out.println("ReponseUser "+reponseUser[i].toLowerCase());
					//System.out.println("Reponse "+reponse[i].toLowerCase());
					if (compter && casse && reponse[i].equals(reponseUser[i2])){
						compter = false;
						nb++;
					}
					else if (compter && (! casse) && reponseUser[i2].toLowerCase().equals(reponse[i].toLowerCase())){
						compter = false;
						nb++;
					}
				}
			}
		}

			

		return nb;
	}

	public  int nbReponses (){
		return reponse.length;
	}

	public  boolean bonneReponse(String [] reponseUser){
		if (nbBonneReponse(reponseUser) == nbReponses())
			return true;
		return false;
	}

	public String [] getTabReponse(){
		return reponse;
	}

}