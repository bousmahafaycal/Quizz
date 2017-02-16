public class InterfaceBanqueAleatoire{
	public static void modifierBanque(){
		Quizz q2 = new Quizz();
		BanqueAleatoire q = new BanqueAleatoire(q2);
		boolean continuer = true;
		while (continuer) {
			String []tab = {"0 - Retour au menu précédent","1 - Ajouter une question", "2 - Supprimer une question"};
			int a = Interface.menu("Chosissez comment modifier la banque aleatoire : ",tab,true);
			
			if (a == 0)
				continuer = false;
			else if (a == 1)
				ajouterQuestion(q);
			else if (a == 2)
				supprimerQuestion(q);

			else
				System.out.println("Veuillez choisir un choix possible s'il vous plait.");
			
		}
			

	}

	public static void ajouterQuestion (BanqueAleatoire q){
		boolean continuer = true;
		while(continuer){
			String [] tab = {"0 - Retour au menu précédent","1 - QuestionSimple : Question qui attend une chaine de caractère en réponse.","2 - QuestionMultiple : Question qui attend plusieurs réponses de type chaine de caractère.",
			"3 - QuestionEntier : Question qui attend un entier en réponse.","4 - QuestionEntierMultiple : Question qui attend plusieurs entiers en réponse."};
			int a = Interface.menu("Quel est le type de question que vous souhaitez ajouter à ce quizz ?",tab,true);
			if (a == 0)
				continuer = false;
			else if (a == 1)
				ajouterQuestionSimple(q);
			else if (a == 2)
				ajouterQuestionMultiple(q);
			else if (a == 3)
				ajouterQuestionEntier(q);
			else if (a == 4)
				ajouterQuestionEntierMultiple(q);
			else
				System.out.println("Merci d'entrer un choix valable ! ");

		}
			
	}

	public static  void ajouterQuestionSimple(BanqueAleatoire q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();
		System.out.println("Donnez la réponse à cet énnonce : ");
		String reponse = Outils.getString();
		int tentative = InterfaceQuizz.getTentative();
		int points = InterfaceQuizz.getPoints();

		boolean c = InterfaceQuizz.getCasse();

		ReponseString rep = new ReponseString(reponse,c);
		QuestionSimple quest = new QuestionSimple(question,rep,points,tentative);
		q.addQuestion(quest);

		
	}

	public static void ajouterQuestionMultiple (BanqueAleatoire q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();

		int nb = 0;
		boolean continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de réponses à cette question : ");
			nb = Outils.getInt();
			if (nb > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de réponses positif.");
			}
		}
		String[] reponse = new String[nb];
		for (int i = 0; i < nb ; i++){
			System.out.println("Donnez la réponse n°"+(i+1)+"/"+nb+" à cet énnonce : ");
			 reponse[i] = Outils.getString();
		}
			
		int tentative = InterfaceQuizz.getTentative();

		int points = InterfaceQuizz.getPoints();

		boolean c = InterfaceQuizz.getCasse();

		boolean o = InterfaceQuizz.getOrdre();

		ReponseStringMultiple rep = new ReponseStringMultiple(reponse,o, c);
		QuestionMultiple quest = new QuestionMultiple(question,rep,points,tentative);
		q.addQuestion(quest);
	}

	public  static void ajouterQuestionEntierMultiple(BanqueAleatoire q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();

		int nb = 0;
		boolean continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de réponses à cette question : ");
			nb = Outils.getInt();
			if (nb > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de réponses positif.");
			}
		}
		int[] reponse = new int[nb];
		for (int i = 0; i < nb ; i++){
			System.out.println("Donnez la réponse n°"+(i+1)+"/"+nb+" à cet énnonce : ");
			 reponse[i] = Outils.getInt();
		}

		
		int tentative = InterfaceQuizz.getTentative();

		int points = InterfaceQuizz.getPoints();

		int tolerance = InterfaceQuizz.getTolerance();

		boolean o = InterfaceQuizz.getOrdre();

		
		ReponseEntierMultiple rep = new ReponseEntierMultiple(reponse,tolerance,o);
		QuestionEntierMultiple quest = new QuestionEntierMultiple(question,rep,points,tentative);
		q.addQuestion(quest);
		
	}


	public static void ajouterQuestionEntier(BanqueAleatoire q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();
		System.out.println("Donnez la réponse à cet énnonce : ");
		int reponse = Outils.getInt();
		

		int points = InterfaceQuizz.getPoints();

		int tentative = InterfaceQuizz.getTentative();

		int tolerance = InterfaceQuizz.getTolerance();
		

		
		ReponseEntier rep = new ReponseEntier(reponse,tolerance);
		QuestionEntier quest = new QuestionEntier(question,rep,points,tentative);
		q.addQuestion(quest);
		
	}

	public static void supprimerQuestion(BanqueAleatoire q){
		boolean continuer = true;
		while (continuer) {
			System.out.println("Voici la liste des questions :");
			System.out.println("0 - Revenir au menu");
			afficherQuestionQuizz(q);
			System.out.println("Choisissez un nombre pour supprimer une question ou revenir au menu");
			int a = Outils.getInt();
			if (a == 0)
				return ;
			else if (a >= 1 && a<= q.liste.size()){
				continuer = false;
				q.delQuestion(a);
			}
			else{
				System.out.println("Merci de bien vouloir entrer un choix valide");
			}

			
		}

	}

	public static void afficherQuestionQuizz(BanqueAleatoire q){
		for (int i = 0; i < q.liste.size(); i++)
			System.out.println((i+1)+" - "+q.liste.get(i).question);
	}
}