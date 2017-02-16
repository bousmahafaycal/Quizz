public class QuestionEntierMultiple extends Question {
	ReponseEntierMultiple reponse;

	public QuestionEntierMultiple(String question, ReponseEntierMultiple reponse){
		this(question,reponse,new Points(5));
	}

	public QuestionEntierMultiple(String question, ReponseEntierMultiple reponse, Points points){
		this(question,reponse,points, 1);
	}

	public QuestionEntierMultiple(String question, ReponseEntierMultiple reponse, Points points, int tentative){
		super(question, points,tentative);
		this.reponse = reponse;
	}

	public ReponseEntierMultiple getReponse(){
		return reponse;
	}

	public String sauvegarde(){
		String chaine =  "<Question><Type>QuestionEntierMultiple</Type>"+ sauvegardeQuestion();
		for (int i2 = 0; i2 < reponse.reponse.length; i2++){
			chaine+= "<Reponse>"+reponse.reponse[i2]+"</Reponse>";
		}
		chaine += "<Tolerance>"+reponse.tolerance+"</Tolerance>"+"<Ordre>"+reponse.ordre+"</Ordre></Question>\n";
		return chaine;
	}
	
	public void ouvre (String chaine){
		ouvreQuestion(chaine);
		String chaineCT = Outils.recupereBaliseAuto(chaine,"Tolerance",1,"Tolerance",false);
		reponse.tolerance = Integer.parseInt(chaineCT);
		int nbReponse = Outils.compter(chaine,"<Reponse");
		reponse.reponse = new int [nbReponse];
		for (int i = 0; i < nbReponse; i++){
			reponse.reponse[i] = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"Reponse",(i+1),"Reponse",false));
		}
	}
}