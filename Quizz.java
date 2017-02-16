import java.util.*;
public class Quizz {
	ArrayList <Question> liste = new ArrayList<Question>();

	public void copy(Quizz q){
		liste = new ArrayList<Question>(q.liste);
	}

	public void addQuestion(Question q){
		liste.add(q);
	}

	public void delQuestion(int n){
		liste.remove(n);
	}

	public void delQuestion(Question n){
		liste.remove(n);
	}

	public int getNbPoints(){
		int nb = 0;
		for (int i = 0; i < liste.size(); i++)
			nb += liste.get(i).points;
		return nb;
	}

	public void insererQuestion(int a, int b){
		Question q = liste.get(a);
		liste.remove(a);
		liste.add(b,q);
	}


	public boolean exporter(String nom){
		// Doit vérifier que le nom n'est pas déja utiliser
		// Doit modifier le fichier registreQuizz.f
		Sauvegarde sauv = new Sauvegarde("sauvegarde.f");
		if (sauv.ajouter(nom)){
			enregistrerQuizz(nom+".f");
			sauv.sauvegarder();
			return true;
		}
		return false;
	}

	// PEUT ETRE CHANGER LE SYSTEME SANS LES INSTANCE OF ET LES IF POUR PLUS DE FACILITE LORS DE CREATIONS DE NOUVELLES CLASSES
	protected void enregistrerQuizz(String adresse){
		String chaine = "";
		Question q2;
		for (int i = 0; i < liste.size(); i++){
			q2 = liste.get(i);
			chaine += q2.sauvegarde();
		}
		//System.out.println(chaine);
		Outils.ecrireFichier(adresse, chaine);
	}

	// PEUT ETRE CHANGER LE SYSTEME SANS LES INSTANCE OF ET LES STRING POUR PLUS DE FACILITE LORS DE CREATIONS DE NOUVELLES CLASSES
	public void ouvreQuizz(String adresse){
		String chaine = Outils.lireFichier(adresse);
		//System.out.println(chaine);
		int nb = Outils.compter(chaine,"<Question");
		String chaine2,type;

		for (int i = 0 ; i < nb ; i++){

			//System.out.println("i : "+i);
			chaine2  = Outils.recupereBaliseAuto(chaine, "Question", (i+1), "Question", false);
			type = Outils.recupereBaliseAuto(chaine2, "Type", 1, "Type", false);
			//System.out.println("type : "+type);
			//System.out.println("chaine2 : "+chaine2);

			if(type.equals("QuestionSimple")){
				ReponseString r = new ReponseString("");
				QuestionSimple q = new QuestionSimple("",r);
				q.ouvre(chaine2);
				liste.add(q);
			}
			else if (type.equals("QuestionEntier")){
				ReponseEntier r = new ReponseEntier (0);
				QuestionEntier q = new QuestionEntier("",r);
				q.ouvre(chaine2);
				liste.add(q);
			}
			else if (type.equals("QuestionMultiple")){
				String [] rep = {""};
				ReponseStringMultiple r = new ReponseStringMultiple (rep);
				QuestionMultiple q = new QuestionMultiple("",r);
				q.ouvre(chaine2);
				liste.add(q);
			}
			else if (type.equals("QuestionEntierMultiple")){
				int [] rep = {0,1};
				ReponseEntierMultiple r = new ReponseEntierMultiple (rep);
				QuestionEntierMultiple q = new QuestionEntierMultiple("",r);
				q.ouvre(chaine2);
				liste.add(q);
			}


		}
		

		
	}

	
}