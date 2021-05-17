package serveur;

import java.io.PrintStream;
import java.nio.file.Path;

//gere le deplacement du client
public class CommandeCD extends Commande  {
	
	public CommandeCD(PrintStream ps, String commandeStr, int numcli)  {
		super(ps, commandeStr, numcli);
	
	}

	public void execute(Dossier_client client)  {
		
try {
			//teste si le nom du repertoire n'est pas vide
			if(!commandeArgs[0].isEmpty()) {
			//creation des chemins
			Path oldpath= client.repertoireActuel.toPath().normalize();
			Path newPath= oldpath.resolve(commandeArgs[0]).normalize();
			int i= newPath.toString().length();
			int j= oldpath.toString().length();
			System.out.println(i);
			System.out.println(j);
			//changement de repertoire si le nouvau chemin est correct
			if(client.checkDirName(newPath.toString()) && newPath.toAbsolutePath().startsWith(client.repertoireRacine.getAbsolutePath())) {
				client.repertoireActuel= newPath.toFile();
				ps.println("0 "+newPath);
			}
			//sinon repertoire incorrect
			else {
				ps.println("2 non du repertoire incorrect");}
			}
			else {
				ps.println("2 non du repertoire incorrect");}
			
		}	
//gestion d'erreur
		catch(NullPointerException e) {
			ps.println("2 illegal path");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			ps.println("2 il faut un argument a la commande");
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	 

}

