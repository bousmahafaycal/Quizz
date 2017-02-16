public class QuestionEntier extends Question {
	ReponseEntier  reponse;

	public QuestionEntier(String question, ReponseEntier reponse){
		this(question,reponse,5);
	}

	public QuestionEntier (String question, ReponseEntier reponse, int points){
		this(question, reponse,points,1);
	}

	public QuestionEntier (String question, ReponseEntier reponse, int points, int tentative){
		super(question, points, tentative);
		this.reponse = reponse;
	}

	public ReponseEntier getReponse(){
		return reponse;
	}

	public String sauvegarde(){
		String chaine = "<Question><Type>QuestionEntier</Type>"+ sauvegardeQuestion();
		chaine+= "<Reponse>"+reponse.reponse+"</Reponse><Tolerance>"+reponse.tolerance+"</Tolerance></Question>\n";
		return chaine;
	}
	
	public void ouvre (String chaine){
		ouvreQuestion(chaine);
		reponse.reponse = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"Reponse",1,"Reponse",false));
		String chaineCT = Outils.recupereBaliseAuto(chaine,"Tolerance",1,"Tolerance",false);
		reponse.tolerance = Integer.parseInt(chaineCT);
	}
}