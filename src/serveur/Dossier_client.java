package serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class Dossier_client {
	private String pseudo;
	public File repertoireActuel;
	public File repertoireRacine;
	public boolean liveuser=true;
	public int port;
	//private String pass;
	private final String repertoireClient= "dossier";
	public Dossier_client()
	{
		this.pseudo="";
		this.repertoireActuel=null;
		this.repertoireRacine=null;
		this.port =2930;
	}
	/*Verifie l'existence du mot de pass */
	@SuppressWarnings("resource")
	public boolean getpass (String pass)
	{
		String line;
		File directory= new File (".");		
		String f=directory.getAbsolutePath();
		if (!pass.isEmpty()) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(f.substring(0, f.length()-1)+repertoireClient+"/"+pseudo+"/"+ pseudo+".txt"));
			while ((line= br.readLine()) != null)
			{
				if (pass.equals(line))
				{
					System.out.println ("bon mot de passe");
					return true;
				}
			}
			br.close();
		}
		
		catch (FileNotFoundException e) {
			System.err.println ("Fichier de lecture introuvable");
		} catch (IOException e) {
			System.err.println ("IOException");
			
		}
		}
		return false;
	}
	
	/*Verifie  l'existence de la commande saisie*/
	public boolean checkCmd(String cmd, PrintStream ps)
	{
		String [] tab= {"ls", "get", "cd", "pwd", "stor", "user", "pass", "bye"};
		for (int i =0; i < tab.length; i ++)
		{
			if (tab[i].equals(cmd))
				return true;
		}
		return false;
	}
	
	/*check la vie de l'utilisateur connecte*/
	public boolean checkLive() {
		if (liveuser) {
			System.out.println(liveuser);
	
			return true;}
		else {
			System.out.println(liveuser);
			
			return false;}
	}
	
	/*teste si le user existe dans la bdd*/
	public boolean getRep(String pseudo) {
		this.pseudo=pseudo;
		File directory= new File (".");		
		String f=directory.getAbsolutePath() ;
		String repA= f.substring(0, f.length()-1)+repertoireClient+"/"+ pseudo;
		
		if (!pseudo.isEmpty())
			
		{
			File dir= new File (repA);		
			if (dir.exists()) {	
				//changement du repertoire de l'utilisateur
				this.repertoireRacine= new File (repA);
				this.repertoireActuel= new File (repA);
				return true;
				
			}
		}
		return  false;
	}
	
	
	
	/*affiche la liste des elements contenus dans le repertoire du user*/
	public String[] ListFile() 
	{
		String[] liste=this.repertoireActuel.list();
		return liste;
	}
	public File[] ListFile2() 
	{
		File[] liste=this.repertoireActuel.listFiles();
		return liste;
	}
	
	/*teste si le nom du repertoire choisie existe*/
	public boolean checkDirName(String dirname)
	{
		File f= new File(dirname);
		if (f.exists() && f.isDirectory()) {
			System.out.println(dirname);
			return true;
		}
		else
			return false;
	}
	
	/*realise le cd du client*/
	public void cd_client1(String cmd)
	{
		if (checkDirName(cmd))
		{
			File directory= new File (".");		
			String f=directory.getAbsolutePath() ;
			String repA= f.substring(0, f.length()-1)+repertoireClient+"/"+ pseudo+"/"+cmd;
			System.out.print(repA );
			File file_cd= new File(repA);
			repertoireActuel= file_cd;
		}
	}
	
	
	/*realise le telechargement de fichier du serveur vers le client*/
	@SuppressWarnings("resource")
	public  void Get_cli(String filename,PrintStream ps)  
	{
		try {
			//si le nom n'est pas vide
			if (!filename.isEmpty()) {
		PrintStream envoie;
		//creation des chemin
		File directory= new File (".");		
		
		File fichier= directory.getAbsoluteFile();
		
		Path ph= fichier.toPath().resolve("src/serveur/get_doc/"+ filename).normalize();
		
		System.out.println(ph.toString());
		File fic= new File (ph.toString());
		
		try {
			
		//test si le chemin existe
		if (fic.exists()) {
			try {
				int i=0;
				this.port=this.port +i;
				i++;
			//si le chemin existe on envoie 
			ps.println("1  Le fichier " + filename +" existe et prêt pour l’envoi ");
			ps.println("0 sur le port "+this.port);
			
			//creation et acceptation de la connexion
			ServerSocket socketserver= new ServerSocket(this.port);
			
			System.out.println(this.port);
			
			Socket sock =socketserver.accept();
			
			FileInputStream fis = new FileInputStream(fic.toString());
			envoie = new PrintStream(sock.getOutputStream());			
			InputStreamReader is= new InputStreamReader(fis);
			BufferedReader red= new BufferedReader(is);
			
			int byte_lu= 0;
			System.out.println("sdfghj");
			//tant que la lecture est possible on lit et on ecrit
			while ((byte_lu = red.read())!= -1) {
				
				envoie.println(byte_lu);
				System.out.println(red.read());
			}
			//gestion d'erreur
			} catch (IOException e) {
				System.out.println("socket get erreur");
				//e.printStackTrace();
			}
		}
		else
			ps.println("2 fichier inexistant");
		}
		//gestion d'erreur
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
		
	
	
	
	public void stor_cli(String filename, PrintStream ps)
	{
		try {
			if (!filename.isEmpty()) {
		PrintStream envoie;
		File directory= new File (".");		

		File fichier= directory.getAbsoluteFile();
		
		Path ph= fichier.toPath().resolve("dossier/dos/"+ filename).normalize();
		
		System.out.println(ph.toString());
		
		File fic= new File (ph.toString());
		
		try {
			
		
		if (fic.exists()) {
			try {
				
			ps.println("le 8000");
			ServerSocket socketserver= new ServerSocket(8000);
			System.out.println("hello");
			
			Socket sock =socketserver.accept();
			System.out.println("zaza");
			FileInputStream fis = new FileInputStream(fic.toString());
			envoie = new PrintStream(sock.getOutputStream());			
			InputStreamReader is= new InputStreamReader(fis);
			BufferedReader red= new BufferedReader(is);
			
			int byte_lu= 0;
			System.out.println("sdfghj");
			System.out.println(red.read());
			System.out.println(is.read());
			while ((byte_lu = red.read())!= -1) {
				//System.out.println(red.read());
				//System.out.println(byte_lu);
				envoie.println(byte_lu);
				System.out.println(red.read());
			}
			socketserver.close();
			
			} catch (IOException e) {
				System.out.println("socket get erreur");
				//e.printStackTrace();
			}
		}
		else
			System.out.println("2 fichier inexistant");
		}
	catch(NullPointerException e) {
		System.out.println("2 illegal path");
	}
	}
	else
		System.out.println("2 il faut un argument a la commande");
		
	}catch(ArrayIndexOutOfBoundsException e) {
		System.out.println("2 il faut un argument a la commande");
	}
			
		
		}
		
		
		public void m2(String cmd) {
			
			try {
				PrintStream envoie;
				System.out.println("hello");
				this.port=this.port +1;
			
				@SuppressWarnings("resource")
				ServerSocket socketserver= new ServerSocket(this.port);
				System.out.println("ludftgyh");
				System.out.println(this.port);
				
				Socket sock =socketserver.accept();
				System.out.println("burnnn");
				
				File directory= new File (".");
				File fichier= directory.getAbsoluteFile();
				String user=System.getProperty("user.dir");
     	         File fileuser= new File(user);
      	        Path pathout = fileuser.toPath().resolve("dossier/dos/"+cmd).normalize();

				//Path ph= fichier.toPath().resolve("src/serveur/get_doc/"+ cmd).normalize();
				
				envoie = new PrintStream(sock.getOutputStream());
				BufferedReader br= new BufferedReader(new InputStreamReader(sock.getInputStream()));

				//envoie = new PrintStream(sock.getOutputStream());			
				//InputStreamReader is= new InputStreamReader(fis);
				//BufferedReader red= new BufferedReader(is);
				
				
				FileInputStream fis= new FileInputStream(pathout.toString());
				//FileOutputStream fileOS = new FileOutputStream(ph.toString());
				InputStreamReader is= new InputStreamReader(fis);
				BufferedReader red= new BufferedReader(is);
				int byte_lu= 0;
				System.out.println(fis.read());
				System.out.println(br.read());
				System.out.println(is.read());
				System.out.println(red.read());
				while ((byte_lu = br.read())!= -1) {
					
					envoie.println(byte_lu);
					System.out.println(byte_lu);
				}
				br.close();
				//red.close();
				
				} catch (IOException e) {
					System.err.println("Erreur de connexion sdfghj");
					
				}
		}
			
			
		
	
}
