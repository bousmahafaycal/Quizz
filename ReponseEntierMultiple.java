class ReponseEntierMultiple extends Reponse {
	int tolerance;
	int [] reponse;
	boolean ordre;

	public ReponseEntierMultiple(int [] reponse){
		this(reponse,0,false);
	}

	public ReponseEntierMultiple(int [] reponse,int tolerance){
		this(reponse,tolerance,false);
	}
	public ReponseEntierMultiple(int [] reponse,int tolerance,boolean ordre){
		this.reponse = reponse;
		this.tolerance = tolerance;
		this.ordre = ordre;
		//System.out.println("NEW ENTIER MULTIPLE");
	}


	// reponseUser.length doit etre égal à 
	public  int nbBonneReponse(int[] reponseUser){
		int nb = 0;
		boolean compter ;
		if (ordre){
			if (reponseUser.length == reponse.length){
				for (int i = 0; i < reponse.length ; i++){
					//System.out.println("ReponseUser "+reponseUser[i]);
					//System.out.println("Reponse "+reponse[i]);
					if (reponseUser[i] >=  reponse[i] - tolerance && reponseUser[i] <= reponse[i] + tolerance )
						nb++;
				}
			}
		}else {
			for(int i = 0 ; i < reponse.length; i++){
				compter = true;
				//System.out.println("ReponseUser "+reponseUser[i]);
				//System.out.println("Reponse "+reponse[i]);
				for (int i2 = 0; i2 < reponseUser.length; i2++){
					if (compter && reponseUser[i2] >=  reponse[i] - tolerance && reponseUser[i2] <= reponse[i] + tolerance){
						nb++;
						compter = false;
					}
				}
			}
		}

			
		
		
		return nb;
	}

	public  int nbReponses (){
		return reponse.length;
	}

	public  boolean bonneReponse(int [] reponseUser){
		if (nbBonneReponse(reponseUser) == nbReponses())
			return true;
		return false;
	}

	public String [] getTabReponse(){
		String tab [] = new String [reponse.length];
		for (int i = 0; i < tab.length; i++)
			tab[i] = String.valueOf(reponse[i]);
		return tab;
	}
}