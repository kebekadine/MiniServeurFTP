package serveur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandeUSER extends Commande {
	
	public CommandeUSER(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
	}
	
	
	public boolean getRep(String pseudo) {
		
		
		if (!pseudo.isEmpty())
		{
			File dir= new File (pseudo);		
			if (dir.exists()) {
				System.out.print( "j'existe le mdp" );
				return true;
			}
			
		}
		
		return  false;
	}

	public void execute(Dossier_client client, int numcli ) {
		if (client.getRep(commandeArgs[0])) {
			//if (client.getpass(commandeArgs[0])) {
			System.out.println(commandeArgs[0]);
			CommandExecutor.userOk[numcli] = true;
			ps.println("0 Commande user OK");
		}
		else {
			ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
		}
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}


	

}
