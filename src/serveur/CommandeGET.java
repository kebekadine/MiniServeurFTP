package serveur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class CommandeGET extends Commande {
	int port=0;
	public CommandeGET(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
		port++;
		
	}
/**/
	public void execute(Dossier_client client) {
	
		try {
			
			if (!commandeArgs[0].isEmpty()) {
		PrintStream envoie;
		File directory= new File (".");		
		//String f=directory.getAbsolutePath() ;
		File fichier= directory.getAbsoluteFile();
		Path ph= fichier.toPath().resolve("src/serveur/get_doc/"+ commandeArgs[0]).normalize();
		System.out.println(ph.toString());
		File fic= new File (ph.toString());
		
		try {
			
		
		if (fic.exists()) {
			try {
				//int i=0;
				//this.port=this.port +i;
				//i++;
				int tt= 3006;
				int t2= tt+port;
			ps.println("1  Le fichier " + commandeArgs[0] +" existe et prêt pour l’envoi ");
			ps.println("0 sur le port "+t2);
			//creation du nouveau server
			ServerSocket socketserver= new ServerSocket(t2);
			//creation de la socket
			Socket sock =socketserver.accept();
			
			FileInputStream fis = new FileInputStream(fic.toString());
			
			envoie = new PrintStream(sock.getOutputStream());			
			
			InputStreamReader is= new InputStreamReader(fis);
			
			BufferedReader red= new BufferedReader(is);
			
			int byte_lu= 0;
			
			//tant que c'est possible de lire on lit et on ecrit 
			while ((byte_lu = red.read())!= -1) {
				
				envoie.println(byte_lu);
				
				
			}
			socketserver.close();
			
			}
			//gestion d'erreur
			catch (IOException e) {
				System.out.println("socket get erreur");
				//e.printStackTrace();
			} 
		}
		else
			ps.println("2 fichier inexistant");
		}
	catch(NullPointerException e) {
		ps.println("2 illegal path");
	}
	}
	else
		ps.println("2 il faut un argument a la commande");
		
	}catch(ArrayIndexOutOfBoundsException e) {
		ps.println("2 il faut un argument a la commande");
	}
			
		
		
		
		
	}
		

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	/*try {
			
			if (!commandeArgs[0].isEmpty()) {
			File directory= new File(".");
			File fichier= directory.getAbsoluteFile();
			//ps= new Print
			Path pathin = fichier.toPath().resolve("src/serveur/get_doc/"+commandeArgs[0]).normalize();
			System.out.println(pathin.toString());
			File fic= new File (pathin.toString());
			if (fic.exists()) {
				ps.println("1 Le fichier"+ commandeArgs[0]+"existe et prêt pour l’envoi");
				ps.println("0 Sur le port 9000");
				try {
					ServerSocket ss= new ServerSocket(9000);
					Socket sock= ss.accept();
					FileInputStream fis= new FileInputStream(fic.toString());
					PrintStream envoie= new PrintStream (sock.getOutputStream());
					InputStreamReader is= new InputStreamReader(fis);
					BufferedReader red= new BufferedReader(is);
					int lecture;
					byte[]b =new byte[800];
					while ((lecture= red.read()) != -1)
					{
						envoie.println(lecture);
					}
					
				} catch (IOException e) {
					System.out.println("socket get erreur");
					//e.printStackTrace();
				}
			}
			else
				ps.println("2 fichier inexistant");
			
			}
			else
				ps.println("2 il faut un argument a la commande");
			}
		catch(NullPointerException e) {
			ps.println("2 illegal path");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			ps.println("2 il faut un argument a la commande");
		}
					
				
			*/

}
