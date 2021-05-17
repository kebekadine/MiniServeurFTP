package serveur;
import java.io.PrintStream;

public class CommandExecutor {
	
	//public static boolean userOk = false ;
	//public static boolean pwOk = false ;
	public static boolean userOk [] = new boolean[10];
	public static boolean pwOk [] = new boolean [10];
public void initialize() {
		
		for(int i=0;i<10;i++)
		{
			userOk [i]= false;
			pwOk[i]= false;		
		}
	}
	
	public static void executeCommande(PrintStream ps, String commande, Dossier_client client,int numcli)  {
		System.out.println(CommandExecutor.userOk);
		System.out.println(CommandExecutor.pwOk);
		if(userOk[numcli] && pwOk[numcli]) {
			// Changer de repertoire. Un (..) permet de revenir au repertoire superieur
			if(commande.split(" ")[0].equals("cd")) (new CommandeCD(ps, commande, numcli)).execute(client);
	
			// Telecharger un fichier
			if(commande.split(" ")[0].equals("get")) (new CommandeGET(ps, commande, numcli)).execute(client);
			
			// Afficher la liste des fichiers et des dossiers du repertoire courant
			if(commande.split(" ")[0].equals("ls")) (new CommandeLS(ps, commande, numcli)).execute(client);
		
			// Afficher le repertoire courant
			if(commande.split(" ")[0].equals("pwd")) (new CommandePWD(ps, commande, numcli)).execute(client);
			
			// Envoyer (uploader) un fichier
			if(commande.split(" ")[0].equals("stor")) (new CommandeSTOR(ps, commande, numcli)).execute();
							}
		else {
			if(commande.split(" ")[0].equals("pass") || commande.split(" ")[0].equals("user")) {
				// Le mot de passe pour l'authentification
				if(commande.split(" ")[0].equals("pass")) 
					(new CommandePASS(ps, commande, numcli)).execute(client, numcli);
	
				// Le login pour l'authentification
				if(commande.split(" ")[0].equals("user")) 
					(new CommandeUSER(ps, commande, numcli)).execute(client, numcli);
				System.out.println(numcli);
				numcli++;
			}
			else
				ps.println("2 Vous n'êtes pas connecté !");
		}
		
	}

}
