public class InterfaceJouer{
	public static void menuJouer(){
		boolean continuer = true;
		while (continuer){
			System.out.println("--------------------------");
			System.out.println("Veuillez choisir la façon dont le choix des questions va etre fait :");
			System.out.println("0 - Retour au menu");
			System.out.println("1 - Génération aléatoire d'un quizz");
			System.out.println("2 - Choix d'un quizz existant");
			int a = Outils.getInt();
			System.out.println("--------------------------");
			System.out.println();
			if (a == 0)
				continuer = false;
			else if (a == 1){
				genererAleatoirement();
			}
			else if (a == 2)
				choixExistant();
			else {
				System.out.println("Merci de bien voulour prendre un choix valable");
			}
		}
		
	}


	public static void genererAleatoirement(){
		boolean continuer = true;
		Quizz q = new Quizz();
		BanqueAleatoire ba = new BanqueAleatoire(q);
		int b = 0;
		while (continuer){
			System.out.println("--------------------------");
			System.out.println("De combien de points souhaitez vous que le quizz soit constituer ?\n(mettre 0 si vous souhaitez revenir au menu)");
			b = Outils.getInt();
			System.out.println("--------------------------");
			System.out.println();
			if (b == 0)
				return;
			else if (b < 0) {
				System.out.println("Merci de bien voulour prendre un choix valable");
			}
			else {
				if (! ba.constitueQuizz(b))
					System.out.println("Il est impossible de créer un quizz à "+b+" points. Merci de bien vouloir essayer avec un autre nombre de points.");
				else 
					continuer = false;
			}

			
		}
		
			//constituerQuizz(q);
		
			// System.out.println("q.2size "+q.liste.size());
		
			//System.out.println("q.2size : "+q.liste.size());
		jouer(ba.q);
		System.out.println("Souhaitez vous enregistrer le quizz ? (YES/no)");
		String a = Outils.getString();
		if ( ! a.equals("no")){
			InterfaceQuizz.sauvegarderQuizz(ba.q);
		}
		
			
		
			
	}


	public static void choixExistant(){
		Sauvegarde sauv = new Sauvegarde("sauvegarde.f");
		System.out.println("--------------------------");
		System.out.println("Veuillez choisir l'un des choix suivants : ");
		System.out.println("0 - Revenir au menu Jouer");
		Interface.afficherSauvegarde(sauv);
		int a = Outils.getInt();
		System.out.println("--------------------------");
		System.out.println();

		if (a< 0 || a > sauv.nom.size()){
			System.out.println("Attention, merci de bien vouloir choisir un choix possile !");
			System.out.println();
			choixExistant();
		}
		else if (a == 0){
			menuJouer();
		}
		else {
			Quizz q = new Quizz();
			q.ouvreQuizz(sauv.nom.get(a-1)+".f");
			jouer(q);
		}
	}

	public static  void jouer(Quizz a){
		int nbPoints = 0, nbPointsTotal = 0;
		boolean rep = false;
		//Quizz a = new Quizz();
		//constituerQuizz(a);
		//a.ouvreQuizz("quizz.f");
		//System.out.println("size : "+a.liste.size());

		System.out.println("--------------------------");
		System.out.println("DEBUT DU QUIZZ");
		System.out.println("Ce quizz est sur "+a.getNbPoints()+" points.");
		System.out.println("--------------------------");
		System.out.println();

		for (int i = 0; i < a.liste.size() ;i++){
			System.out.println("Question n°:"+(i+1)+"/"+a.liste.size()+" :\n"+a.liste.get(i).question + " \n("+a.liste.get(i).getReponse().nbReponses()+" réponse(s)) ["+a.liste.get(i).points+" points]");
			
			for (int i3 = 0; i3 < a.liste.get(i).tentative; i3++){
				System.out.println("Tentative n°"+(i3+1)+"/"+a.liste.get(i).tentative+" : ");
			
				if (a.liste.get(i).getReponse() instanceof ReponseEntier){
					System.out.println("Donnez une réponse : ");
					int reponse = Outils.getInt();
					ReponseEntier b =  (ReponseEntier) (a.liste.get(i).getReponse()) ;
					rep = b.bonneReponse(reponse);
				}

				if (a.liste.get(i).getReponse() instanceof ReponseStringMultiple){
					String [] reponses = new String [a.liste.get(i).getReponse().nbReponses()];
					//System.out.println("Nombre de répon  =  " + reponses.length );
					int z ;
					for (int i2 = 0; i2 <  a.liste.get(i).getReponse().nbReponses(); i2++){
						z = i2 + 1;
						System.out.println("Donnez la réponse n°"+(z)+"/"+a.liste.get(i).getReponse().nbReponses()+" : ");
						reponses[i2] = Outils.getString();
					}
					ReponseStringMultiple b = (ReponseStringMultiple) (a.liste.get(i).getReponse()) ;
					rep = b.bonneReponse(reponses);
				}

				if (a.liste.get(i).getReponse() instanceof ReponseString ){
					System.out.println("Donnez la réponse : ");
					String reponse = Outils.getString();
					ReponseString b = (ReponseString) (a.liste.get(i).getReponse()) ;
					rep = b.bonneReponse(reponse);
				}

				if (a.liste.get(i).getReponse() instanceof ReponseEntierMultiple){
					int [] reponses =  new int [a.liste.get(i).getReponse().nbReponses()];
					int z;
					for (int i2 = 0; i2 <  a.liste.get(i).getReponse().nbReponses(); i2++){
						z = i2 +1;
						System.out.println("Donnez la réponse n°"+(z)+"/"+a.liste.get(i).getReponse().nbReponses()+" : ");
						reponses[i2] = Outils.getInt();

					}
					
					ReponseEntierMultiple b = (ReponseEntierMultiple) (a.liste.get(i).getReponse()) ;
					rep = b.bonneReponse(reponses);
				}
				if(rep == true){
					System.out.println("La ou les réponse(s) soumise(s) ont été acceptée(s).");
					nbPoints +=  a.liste.get(i).points;
					break;
				}else {
					System.out.println("Une ou plusieurs réponse(s) sont fausse(s).");
				}
			}
			for (int i2 = 0; i2 < a.liste.get(i).getReponse().getTabReponse().length; i2++)
				System.out.println("Réponse attendue n°"+(i2+1)+"/"+a.liste.get(i).getReponse().nbReponses()+" : "+a.liste.get(i).getReponse().getTabReponse()[i2]);
			nbPointsTotal +=  a.liste.get(i).points;
			System.out.println("Nombre de points : "+nbPoints+"/"+nbPointsTotal);
			System.out.println("--------------------------");
			System.out.println();
		}
		System.out.println("--------------------------");
		System.out.println("FIN DU QUIZZ");
		System.out.println("Nombre de points : "+nbPoints+"/"+nbPointsTotal);
		//System.out.println( a.exporter("bonjour"));
		System.out.println("--------------------------");
		System.out.println();
		System.out.println();


	}
}