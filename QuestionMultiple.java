

public  class QuestionMultiple extends Question{
	ReponseStringMultiple reponse; 

	public QuestionMultiple(String question, ReponseStringMultiple reponse){
		this(question,reponse,new Points(5));
	}

	public QuestionMultiple(String question,  ReponseStringMultiple reponse, Points points){
		this(question,reponse,points,1);
	}
	public QuestionMultiple(String question,  ReponseStringMultiple reponse, Points points,int tentative){
		super(question, points,tentative);
		this.reponse = reponse;
	}

	public ReponseStringMultiple getReponse(){
		return reponse;
	}

	public String sauvegarde(){
		String chaine =  "<Question><Type>QuestionMultiple</Type>"+sauvegardeQuestion();
		for (int i2 = 0; i2 < reponse.reponse.length; i2++){
			chaine+= "<Reponse>"+reponse.reponse[i2]+"</Reponse>";
		}
		chaine += "<Casse>"+reponse.casse+"</Casse><Ordre>"+reponse.ordre+"</Ordre></Question>\n";
		return chaine;
	}

	public void ouvre (String chaine){
		ouvreQuestion(chaine);
		String chaineCT = Outils.recupereBaliseAuto(chaine,"Casse",1,"Casse",false);
		reponse.casse = Boolean.parseBoolean(chaineCT);
		int nbReponse = Outils.compter(chaine,"<Reponse");
		reponse.reponse = new String [nbReponse];
		for (int i = 0; i < nbReponse; i++){
			reponse.reponse[i] = Outils.recupereBaliseAuto(chaine,"Reponse",(i+1),"Reponse",false);
		}
	}
	
}