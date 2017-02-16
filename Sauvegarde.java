import java.util.ArrayList ;
public class Sauvegarde {
	//ArrayList<String> adresses = new ArrayList<String> ();
	ArrayList<String> nom  = new ArrayList<String>();
	String adresse;


	public Sauvegarde (String adresse){
		this.adresse = adresse;
		ouvrir();
	}

	public void sauvegarder(){
		String chaine = "";
		for (int i = 0; i < nom.size(); i++){
			//adresses.add(Outils.recupereBaliseAuto(chaine,"Adresse",(i+1),"Adresse",false));
			//chaine += Outils.constitueBalise("Adresse",adresse.get(i));
			chaine += Outils.constitueBalise("Nom", nom.get(i))+"\n";
		}
		Outils.ecrireFichier(adresse,chaine);
	}
	
	public void ouvrir(){
		// Tester la presence du fichier
		if (! Outils.testPresence(adresse))
			return;
		String chaine = Outils.lireFichier(adresse);
		int nb = Outils.compter(chaine,"<Nom");
		for (int i = 0; i < nb; i++){
			//adresses.add(Outils.recupereBaliseAuto(chaine,"Adresse",(i+1),"Adresse",false));
			nom.add(Outils.recupereBaliseAuto(chaine,"Nom",(i+1),"Nom",false));
		}
	}

	public boolean ajouter (String name){
		//System.out.println("ajouter");
		if (!verification(name))
			return false;
		//System.out.println("ajouter 2");
		nom.add(name);
		sauvegarder();
		return true;
	}

	public boolean verification(String name){
		for (int i = 0; i < nom.size() ;i++)
			System.out.println(i+" : "+nom.get(i));
		//System.out.println("size : "+nom.size());
		//System.out.println("contains "+nom.contains(name));
		return ! nom.contains(name);

	}

	public boolean supprimer (String name){
		for (int i = 0; i < nom.size(); i++){
			if (nom.get(i).equals(name)){
				nom.remove(i);
				sauvegarder();
				return true;
			}
			
		}
		return false;
		
	}






}