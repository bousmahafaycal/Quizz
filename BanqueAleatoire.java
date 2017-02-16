import java.util.ArrayList;
import java.util.Random;

public class BanqueAleatoire extends Quizz{
	Quizz q;

	public BanqueAleatoire(Quizz q){
		this.q = q;
		if (!Outils.testPresence("banquealeatoire.f"))
			ajoutDur();
		ouvrir();
		sauvegarder();
	}

	public Quizz getQuizz(){
		return q;
	}

	public void addQuestion(Question q){
		super.addQuestion(q);
		sauvegarder();
	}

	public void delQuestion(int n){
		super.delQuestion(n);
		sauvegarder();
	}

	public void delQuestion(Question n){
		super.delQuestion(n);
		sauvegarder();

	}



	public void  sauvegarder (){
		enregistrerQuizz("banquealeatoire.f");
	}



	public void ouvrir(){
		ouvreQuizz("banquealeatoire.f");
	}

	// Tente de constituer un quizz avec le nombre de points demandé;
	public boolean constitueQuizz(int points){
		ArrayList<Quizz> quizzPossibles = new ArrayList<Quizz> ();
		ArrayList<Integer> dejaUtilise ;
		Quizz enCours ;



		for (int i = 0; i < liste.size(); i++){
			dejaUtilise = new ArrayList<Integer>();
			dejaUtilise.add(i);
			enCours = new Quizz();
			enCours.addQuestion(liste.get(i));
			constitueQuizz(enCours,dejaUtilise,points,quizzPossibles);

		}
		//System.out.println("quizzPossibles : "+quizzPossibles.size());
		//System.out.println("q.size "+q.getNbPoints());
		Random rand = new Random();
		if (quizzPossibles.size() != 0){
			q = quizzPossibles.get(rand.nextInt(quizzPossibles.size()));
		}
		else {
			return false;
		}
		
		//System.out.println("q.size "+q.getNbPoints());
		//System.out.println("Ennonce : "+q.liste.get(0).question);
		return true;
	}

	// Faut voir la complexité de l'algo, peut etre vaut il mieux utiliser cette méthode que dans certais recours et utiliser une méthode qui prend direct les questions aléatoirement si
	// c'est possible.
	protected void constitueQuizz (Quizz enCours, ArrayList<Integer> dejaUtilise, int pointsAObtenir, ArrayList<Quizz> quizzPossibles){
		if (enCours.getNbPoints()  == pointsAObtenir){
			quizzPossibles.add(enCours);
			//System.out.println("C'est bon !!!");
			return ;
		}

		if (enCours.getNbPoints() > pointsAObtenir)
			return;

		ArrayList<Integer> dejaUtilise2 ;
		Quizz enCours2;

		for (int i = 0; i < liste.size(); i++){
			if (! dejaUtilise.contains(i)){
				dejaUtilise2 = new ArrayList(dejaUtilise);
				dejaUtilise2.add(i);
				enCours2 = new Quizz();
				enCours2.copy(enCours);
				enCours2.addQuestion(liste.get(i));
				constitueQuizz(enCours2, dejaUtilise2, pointsAObtenir, quizzPossibles);
			}
		}
	}

	public void ajoutDur(){
		ReponseString r = new ReponseString("Olivier");
		String question = "Quelle est le prénom du personnage principale de la série Arrow ?";
		QuestionSimple q4 = new QuestionSimple(question,r,2);  
		liste.add(q4);

		r = new ReponseString("Marseille");
		question = "Quelle est le seul club français ayant gagné la champions league ?";
		QuestionSimple q = new QuestionSimple(question,r,2);  
		liste.add(q);

		question = "Quelle est l'année de la victoire en champions league de l'om ?? ";
		ReponseEntier r2 = new ReponseEntier(1993,0);
		QuestionEntier q2 = new QuestionEntier(question,r2);
		liste.add(q2);

		question = "Quels sont les noms de famille des deux derniers présidents de la république française ?";
		String reponses[] = {"Hollande","Sarkozy"};
		ReponseStringMultiple r3 = new ReponseStringMultiple(reponses);
		QuestionMultiple q3 = new QuestionMultiple(question,r3);
		liste.add(q3);


		question = "Quelles sont les années des deux titres de l'équipe de France de foorball";
		int reponsesInt[] = {1998,2000};
		ReponseEntierMultiple r5 = new ReponseEntierMultiple(reponsesInt,0,false);
		QuestionEntierMultiple q5 = new QuestionEntierMultiple(question,r5);
		liste.add(q5);
	}
	
}