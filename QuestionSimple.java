public  class QuestionSimple extends Question{
	ReponseString reponse ;

	public QuestionSimple(String question, ReponseString reponse){
		this(question,reponse,5);
	}

	public QuestionSimple(String question,  ReponseString reponse, int points){
		this(question,reponse,points,1);
	}

	public QuestionSimple(String question,  ReponseString reponse, int points, int tentative){
		super(question, points, tentative);
		this.reponse = reponse;
	}

	public ReponseString getReponse(){
		return reponse;
	}

	public String sauvegarde(){
		String chaine = "<Question><Type>QuestionSimple</Type>"+ sauvegardeQuestion();
		chaine+= "<Reponse>"+reponse.reponse+"</Reponse><Casse>"+reponse.casse+"</Casse></Question>\n";
		return chaine;
	}
	
	public void ouvre (String chaine){
		ouvreQuestion(chaine);
		reponse.reponse = Outils.recupereBaliseAuto(chaine,"Reponse",1,"Reponse",false);
		String chaineCT = Outils.recupereBaliseAuto(chaine,"Casse",1,"Casse",false);
		reponse.casse = Boolean.parseBoolean(chaineCT);
	}
}