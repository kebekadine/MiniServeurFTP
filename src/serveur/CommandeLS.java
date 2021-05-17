package serveur;
import java.io.File;
import java.io.PrintStream;
//gere l'affichage de tous ce qui existe dans le  repertoire client
public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
	}

	public void execute(Dossier_client client) {
		File [] liste = client.ListFile2();
		
		//for (String var : liste)
		//for (int i=0; i < liste.length; i++)
			ps.println("0 "+liste[liste.length-1].getName());
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}

