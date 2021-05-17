package serveur;
import java.io.PrintStream;

public class CommandePASS extends Commande {
	
	public CommandePASS(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
		
	}

	public void execute(Dossier_client client, int numcli) {
		//teste si le pw est correct, on l'execute
		if (client.getpass(commandeArgs[0])) {
			CommandExecutor.pwOk[numcli] = true;
			System.out.println(numcli);
			ps.println("1 Commande pass OK");
			ps.println("0 Vous êtes bien connecté sur notre serveur");

		}
		//si le pw est incorrect
		else {
			ps.println("2 Le mot de passe est faux");
		}
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
