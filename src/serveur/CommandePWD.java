package serveur;
import java.io.PrintStream;

public class CommandePWD extends Commande {
	
	public CommandePWD(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
	}

	public void execute(Dossier_client client) {
	//changement du chemin du user connecte
		System.out.println(client.repertoireActuel.toPath().toString());
		ps.println("0 "+client.repertoireActuel.toPath().toString());
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
