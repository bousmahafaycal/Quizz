public abstract class Question  {
	int points, tentative;
	String question;

	public Question(String question){
		this(question,5);
	}
	public Question(String question, int points){
		this(question,points,1);
	}
	public Question(String question, int points, int tentative){
		this.question = question;
		this.points = points;
		this.tentative = tentative;

	}

	//public abstract int verifierQuestion(Reponse r);

	// Return la réponse;
	public abstract Reponse getReponse();
	public abstract String sauvegarde();

	// A partir de la chaine de caractère crée par la sauvegarde, réouvrir l'objet. En réalité ça ne sert pas à grand chose de le mettre en abstract mais bon au moins ça force
	// cette fonction à exister dans toutes les classes crées 
	public abstract void ouvre (String chaine);

	public void ouvreQuestion(String chaine){
		String chainePoints = Outils.recupereBaliseAuto(chaine,"Points", 1, "Points", false);
		question = Outils.recupereBaliseAuto(chaine,"Ennonce",1,"Ennonce",false);
		tentative = Integer.parseInt(Outils.recupereBaliseAuto(chaine,"Tentative",1,"Tentative",false));
		points = Integer.parseInt(chainePoints);
	}

	public String sauvegardeQuestion(){
		String chaine = "<Points>"+points+"</Points><Ennonce>"+question+"</Ennonce>"+Outils.constitueBalise("Tentative",String.valueOf(tentative));
		return chaine;
	}
}