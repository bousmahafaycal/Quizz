public class InterfaceQuizz  { // un peu équivalent à FabriqueQuizzInteractive

	

	public static void sauvegarderQuizz(Quizz q){
		boolean continuer = true;
		String nom;
		while (continuer) {
			System.out.println("Sous quel nom souhaitez vous enregistrer le quizz ? \n(Mettez STOP pour revenir au menu sans enregistrer)");
			nom = Outils.getString();
			if ( (! nom.equals("STOP") )&& (! nom.equals("banquealeatoire")) && q.exporter(nom)){
				System.out.println("Quizz enregistré !");
				continuer = false;
			}
			else if (nom.equals("STOP")){
				System.out.println("Quizz non enregistré, retour au menu précédent!");
				continuer = false;
			}
			else{
				System.out.println("Quizz non enregistré, merci de choisir un autre nom !");
			}
		}
		
	}

	public static void ajouterQuestion (Quizz q){
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

	public static  void ajouterQuestionSimple(Quizz q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();
		System.out.println("Donnez la réponse à cet énnonce : ");
		String reponse = Outils.getString();
		int tentative = 0;
		boolean continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de tentative : ");
			tentative = Outils.getInt();
			if (tentative > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de tentatives positif.");
			}
		}
		int points = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de points pour cette question : ");
			points = Outils.getInt();
			if (points > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de points positif.");
			}
		}

		boolean c = false;
		continuer = true;
		while (continuer){
			String [] tab = {"1 - La casse doit être respectée.","2 - La casse n'a pas besoin d'être respectée."};
			int casse = Interface.menu("Choissiez si la réponse doit prendre en compte la casse : ",tab,false);
			
			if (casse == 1){
				continuer=false;
				c = true;
			}
			else if (casse == 2){
				continuer = false;
				c = false;
			}
			else {
				System.out.println("Merci de choisir un choix possible.");
			}
		}

		ReponseString rep = new ReponseString(reponse,c);
		QuestionSimple quest = new QuestionSimple(question,rep,points,tentative);
		q.addQuestion(quest);

		
	}

	public static void ajouterQuestionMultiple (Quizz q){
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
			
		int tentative = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de tentatives autorisé pour cette question : ");
			tentative = Outils.getInt();
			if (tentative > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de tentative positive.");
			}
		}

		int points = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de points pour cette question : ");
			points = Outils.getInt();
			if (points > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de points positif.");
			}
		}

		boolean c = true;
		continuer = true;
		while (continuer){
			String [] tab = {"1 - La casse doit être respectée.","2 - La casse n'a pas besoin d'être respectée."};
			int casse = Interface.menu("Choissiez si la réponse doit prendre en compte la casse : ",tab, false);
			
			if (casse == 1){
				continuer=false;
				c = true;
			}
			else if (casse == 2){
				continuer = false;
				c = false;
			}
			else {
				System.out.println("Merci de choisir un choix possible.");
			}
		}

		boolean o = true;
		continuer = true;
		while (continuer){
			String [] tab = {"1 - L'ordre doit être respectée.","2 - L'ordre n'a pas besoin d'être respectée."};
			int ordre = Interface.menu("Choissiez si la réponse doit prendre en compte la casse : ",tab,false);
			
			if (ordre == 1){
				continuer=false;
				o = true;
			}
			else if (ordre == 2){
				continuer = false;
				o = false;
			}
			else {
				System.out.println("Merci de choisir un choix possible.");
			}
		}

		ReponseStringMultiple rep = new ReponseStringMultiple(reponse,o, c);
		QuestionMultiple quest = new QuestionMultiple(question,rep,points,tentative);
		q.addQuestion(quest);
	}

	public  static void ajouterQuestionEntierMultiple(Quizz q){
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

		
		int tentative = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de tentatives autorisé pour cette question : ");
			tentative = Outils.getInt();
			if (tentative > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de tentative positive.");
			}
		}

		int points = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de points pour cette question : ");
			points = Outils.getInt();
			if (points > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de points positif.");
			}
		}

		int tolerance = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez la tolérance pour cette question : ");
			tolerance = Outils.getInt();
			if (tolerance >= 0)
				continuer=false;
			else {
				System.out.println("Merci de donner une tolérance positive.");
			}
		}

		boolean o = true;
		continuer = true;
		while (continuer){
			String [] tab = {"1 - L'ordre doit être respectée.","2 - L'ordre n'a pas besoin d'être respectée."};
			int ordre = Interface.menu("Choissiez si la réponse doit prendre en compte la casse : ",tab, false);
			
			if (ordre == 1){
				continuer=false;
				o = true;
			}
			else if (ordre == 2){
				continuer = false;
				o = false;
			}
			else {
				System.out.println("Merci de choisir un choix possible.");
			}
		}

		
		ReponseEntierMultiple rep = new ReponseEntierMultiple(reponse,tolerance,o);
		QuestionEntierMultiple quest = new QuestionEntierMultiple(question,rep,points,tentative);
		q.addQuestion(quest);
		
	}


	public static void ajouterQuestionEntier(Quizz q){
		System.out.println("Donnez l'énnoncé de la question : ");
		String question = Outils.getString();
		System.out.println("Donnez la réponse à cet énnonce : ");
		int reponse = Outils.getInt();
		

		int points = 0;
		boolean continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de points pour cette question : ");
			points = Outils.getInt();
			if (points > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de points positif.");
			}
		}

		int tentative = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez le nombre de tentatives autorisé pour cette question : ");
			tentative = Outils.getInt();
			if (tentative > 0)
				continuer=false;
			else {
				System.out.println("Merci de donner un nombre de tentative positive.");
			}
		}

		int tolerance = 0;
		continuer = true;
		while (continuer){
			System.out.println("Donnez la tolérance pour cette question : ");
			tolerance = Outils.getInt();
			if (tolerance >= 0)
				continuer=false;
			else {
				System.out.println("Merci de donner une tolérance positive.");
			}
		}
		

		
		ReponseEntier rep = new ReponseEntier(reponse,tolerance);
		QuestionEntier quest = new QuestionEntier(question,rep,points,tentative);
		q.addQuestion(quest);
		
	}

	public static void menuPrincipal(){
		boolean continuer = true;
		while (continuer) {
			String []tab = {"0 - Retour au menu précédent","1 - Créer un nouveau quizz","2 - Choisir un quizz déja existant","3 - Supprimer un quizz"};
			int a = Interface.menu("Menu gestion de quizz : faites un choix : ",tab,true);
			
			if (a == 0)
				continuer = false;
			else if (a == 1){
				Quizz q = new Quizz();
				modifierQuizz(q);
			}
			else if (a == 2)
				choixExistant();

			else if (a == 3)
				supprimerQuizz();

			else
				System.out.println("Veuillez choisir un choix possible s'il vous plait.");
			
		}
	}

	public static void supprimerQuizz(){
		Sauvegarde sauv = new Sauvegarde("sauvegarde.f");
		boolean continuer  = true;
		while (continuer){
			System.out.println("--------------------------");
			System.out.println("Veuillez choisir l'un des choix suivants : ");
			System.out.println("0 - Revenir au menu précédent");
			Interface.afficherSauvegarde(sauv);
			int a = Outils.getInt();
			System.out.println("--------------------------");
			System.out.println();

			if (a< 0 || a > sauv.nom.size()){
				System.out.println("Attention, merci de bien vouloir choisir un choix possile !");
				System.out.println();
			}
			else if (a == 0){
				continuer = false;
			}
			else {
				sauv.supprimer(sauv.nom.get(a-1));
			}
		}
	}

	public static void choixExistant(){
		Sauvegarde sauv = new Sauvegarde("sauvegarde.f");
		boolean continuer  = true;
		while (continuer){
			System.out.println("--------------------------");
			System.out.println("Veuillez choisir l'un des choix suivants : ");
			System.out.println("0 - Revenir au menu précédent");
			Interface.afficherSauvegarde(sauv);
			int a = Outils.getInt();
			System.out.println("--------------------------");
			System.out.println();

			if (a< 0 || a > sauv.nom.size()){
				System.out.println("Attention, merci de bien vouloir choisir un choix possile !");
				System.out.println();
			}
			else if (a == 0){
				continuer = false;
			}
			else {
				Quizz q = new Quizz();
				q.ouvreQuizz(sauv.nom.get(a-1)+".f");
				modifierQuizz(q);
			}
		}
			
	}

	public static void modifierQuizz(Quizz q){
		boolean continuer = true;
		while (continuer) {
			String []tab = {"0 - Retour au menu précédent","1 - Ajouter une question","2 - Modifier l'ordre des questions", "3 - Supprimer une question", "4 - Sauvegarder le quizz"};
			int a = Interface.menu("Chosissez comment modifier le quizz : ",tab,true);
			
			if (a == 0)
				continuer = false;
			else if (a == 1)
				ajouterQuestion(q);
			else if (a == 2)
				modifierOrdreQuestion(q);
			else if (a == 3)
				supprimerQuestion(q);
			else if (a  == 4)
				sauvegarderQuizz(q);

			else
				System.out.println("Veuillez choisir un choix possible s'il vous plait.");
			
		}
			

	}

	public static void modifierOrdreQuestion(Quizz q){
		System.out.println("Voici la liste des questions :");
		System.out.println("0 - Revenir au menu");
		afficherQuestionQuizz(q);
		boolean continuer = true;
		int a = 0,b = 0;
		while (continuer) {
			System.out.println("Quelle question souhaitez vous changer de place ?");
			a = Outils.getInt();
			if (a== 0){
				return;
			}
			else if (a >= 1 && a<= q.liste.size())
				continuer = false;
			else{
				System.out.println("Merci de bien vouloir entrer un choix valide");
			}
		}
		continuer = true;
		while (continuer) {
			System.out.println("Ou souhaitez vous la mettre ?");
			b = Outils.getInt();
			if (b== 0){
				return;
			}
			else if (b >= 1 && b<= q.liste.size()+1)
				continuer = false;
			else{
				System.out.println("Merci de bien vouloir entrer un choix valide");
			}
		}
			
		q.insererQuestion(a-1,b-1);

	}

	public static void supprimerQuestion(Quizz q){
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

	public static void afficherQuestionQuizz(Quizz q){
		for (int i = 0; i < q.liste.size(); i++)
			System.out.println((i+1)+" - "+q.liste.get(i).question);
	}



}