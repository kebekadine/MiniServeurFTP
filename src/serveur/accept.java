package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class accept  implements Runnable {
     private Socket socket;
     int numcli;
     public accept( Socket socket, int numcli)
     {
    	 this.socket= socket;
    	 this.numcli=numcli;
    	
     }
     @Override
	public void run() {
			try {
				Dossier_client client = new Dossier_client();
				BufferedReader br;
				/*Socket socket =socketserver.accept();*/
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream ps = new PrintStream(socket.getOutputStream());
			try {
				ps = new PrintStream(socket.getOutputStream());
				ps.println("1 Bienvenue ! ");
				ps.println("1 Serveur FTP Personnel.");
				ps.println("0 Authentification : ");
				
			String commande = "";
			// Attente de reception de commandes et leur execution
			try {
				while(!(commande=br.readLine()).equals("bye")) {
					System.out.println(">> "+commande);	
					
					CommandExecutor.executeCommande(ps, commande, client, numcli);
					System.out.println("fait");
				}
			//gestion des exceptions
			} catch (IOException e) {
				ps.println("2 la commande n'a pas marche");
				System.err.println("Desole, un petit probleme est survenu");
			}
			catch (ArrayIndexOutOfBoundsException e) {
				ps.println("2 la commande n'a pas marche");
      		  //System.err.println("il faut un argument a get");
      	  	}
			if (commande.equals("bye")) {
				System.out.println(" client partit");
				
				
				//socket.close();
			}
			
			
			commande= "";
			}
			
			catch (IOException e1) {

				ps.println("2 la commande n'a pas marche");
				//System.err.println("Erreur de la commande new Printstream...");
			}
			
			} catch (IOException e2) {
				System.err.println("Erreur de lecture de la commande");
				
			}
			
			catch (NullPointerException e2) {
				 System.err.println("Un client parti");
				 
			}
			
	}

}
