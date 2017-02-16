import java.util.*;

public class Interface {

	static Scanner sc = new Scanner (System.in);

	public static void main(String[] args) {
		System.out.println("Bienvenue dans le programme de Quizz de :");
		System.out.println("Bousmaha Fayçal");
		System.out.println();
		menu();
	}

	public static void menu(){
		boolean continuer = true;
		while(continuer){
			System.out.println("--------------------------");
			System.out.println("Veuillez choisir un chiffre : ");
			System.out.println("0 - Quitter");
			System.out.println("1 - Jouer");
			System.out.println("2 - Créer/Supprimer un quizz");
			System.out.println("3 - Modifier la banque aléatoire.");

			System.out.print("Votre choix : ");
			int a = Outils.getInt();
			System.out.println("--------------------------");
			System.out.println("");

			if (a==0)
				continuer = false;
			else if (a == 1)
				InterfaceJouer.menuJouer();
			else if (a==2)
				InterfaceQuizz.menuPrincipal();
			else if (a == 3)
				InterfaceBanqueAleatoire.modifierBanque();
			else {
				System.out.println("Merci de bien vouloir entrer un choix valide !");
			}
		}
			
	}


	

	public static void afficherSauvegarde(Sauvegarde sauv){
		for (int i = 0; i <  sauv.nom.size(); i++)
			System.out.println((i+1)+" - "+sauv.nom.get(i));
	
	}

	public static int menu (String phrase, String [] choix, boolean barre){
		if (barre)
			System.out.println("--------------------------");

		System.out.println(phrase);
		for (int i = 0; i < choix.length; i++){
			System.out.println(choix[i]);
		}
		System.out.print("Votre choix : ");
		int a = Outils.getInt();
		if (barre)
			System.out.println("--------------------------");
		System.out.println();
		return a;
	}

	
}