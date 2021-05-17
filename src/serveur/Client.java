package serveur;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
	//gere le transfert du client vers le serveur
	public static void stor_cli(String filename)
	{
		PrintStream envoie;
		if (!filename.isEmpty()) {
			File fic= new File ("dossier/personne/"+ filename );
			FileInputStream fis;
			try {
				fis = new FileInputStream(fic.toString());
			
			if (fic.exists()) {
				try {
				Socket sock = new Socket("localhost", 2160);
				envoie = new PrintStream(sock.getOutputStream());
				InputStreamReader is= new InputStreamReader(fis);
				BufferedReader red= new BufferedReader(is);
				
				int byte_lu= 0;
				System.out.println(red.read());
				while ((byte_lu = red.read())!= -1) {
					System.out.println(red.read());
					envoie.println(byte_lu);
				}
				sock.close();
				} catch (IOException e) {
					System.err.println("Erreur de connexion");
				}
			}
			else
				System.out.println("2 fichier inexistant");
				
			} catch (FileNotFoundException e1) {
				System.err.println("Le fichier n'a pas ete retrouve");
			}
		}
		
	}
	
	//classe principale du client
	 @SuppressWarnings("unused")
	public static void main(String[] args) {
	   
	   Dossier_client client = new Dossier_client();
       Socket s;
       try {
		s = new Socket("localhost", 2121);
       //InputStream is = new InputStream(s.getInputStream());
       BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
       PrintStream ps = new PrintStream(s.getOutputStream());
       String reception = null;
       String envoie="v";
       
       char reponseX = 'v' ;
       
       Scanner sc = new Scanner( System.in);
       try {
    	//tant que la commande est differente de bye on envoie les commandes
        while( !envoie.equals("bye") && !envoie.isEmpty())
       {
    	   //lecture des reponses du serveur
           if((reception=br.readLine()) != null)
           {
               System.out.println( reception);
               //si cest la reponse de la commande get
               if (reception.startsWith("0 sur le port"))
               {
            	   try {
            		
            	//creation de socket qui gere la commande get
	            	 String res= reception.split(" ")[4]; 
	      	         //System.out.println(Integer.parseInt(res));
	      	         String cmd= envoie.split(" ")[1];
	      	         Socket sock = new Socket("localhost",Integer.parseInt(res));
	      	         String user=System.getProperty("user.dir");
	      	         File fileuser= new File(user);
	      	         Path pathin = fileuser.toPath().resolve("src/serveur/get_doc/"+ cmd).normalize();
	      	         Path pathout = fileuser.toPath().resolve("dossier/dos/"+cmd).normalize();
	      	         FileInputStream fis= new FileInputStream (pathin.toString());
	      	         
	      			 FileOutputStream fileOS = new FileOutputStream(pathout.toString());
	      			
	      			 int byte_lu;
	      			 byte[] stock= new byte[800];
	      			 
	      			 //transfert de la valeur lue
	      			 while ((byte_lu= fis.read(stock)) != -1){
	      				 fileOS.write(stock);
	     
	      			 }
	      			 fis.close();
	      			 fileOS.close();
	      			 sock.close();
            	  } 
            	   //gestion d'erreur
            	  catch (ArrayIndexOutOfBoundsException e) {
            		  System.err.println("il faut un argument a get");
            	  }
            	  System.out.print( "test " );
      		
    
               }
               reponseX = reception.charAt(0);
           }
           else 
           {
        	   System.err.print("serveur deconnee");
        	   sc.close();
       		   s.close();
        	   
           }
           
           if( reponseX =='0')
           {
               System.out.print( "Enter your Ordered1: " );
               envoie = sc.nextLine();
               //check si c'est la bonne commande
               while (!client.checkCmd(envoie.split(" ")[0], ps) ) {
            	   System.out.print( "Commande inconnue " );
            	   System.out.print( "Enter your Ordered: " );
                   envoie = sc.nextLine();
               }
	           if (envoie.split(" ")[0].equals("stor")) {
	        	   System.out.print(envoie.split(" ")[1]);
	        	   client.stor_cli(envoie.split(" ")[1], ps);
	            	  
	        	   ps.println(envoie);
	           }
	           else 
	        	   ps.println(envoie);
	           System.out.println( "envoie reussi" );
	 
           }
           if(reponseX == '2')
           {
             
        	   System.out.println( "la commande n'as pas marcher" );
               System.out.println( "Enter your Ordered2: " );
               sc = new Scanner(System.in);
               envoie = sc.nextLine();
               
              //check si c'est la bonne commande
               while (!client.checkCmd(envoie.split(" ")[0], ps) ) {
            	   System.out.println( "Commande inconnue " );
            	   System.out.println( "Enter your Ordered: " );
                   envoie = sc.nextLine();
               }
               //si c'est la commande stor
               if (envoie.split(" ")[0].equals("stor")) {
            	   System.out.print(envoie.split(" ")[1]);
            	   stor_cli(envoie.split(" ")[1]);
            	   ps.println(envoie);
            	   
               }
               else
            	   ps.println(envoie);
        
           }
                
       }
       }
       //gesstion d'erreur si le serveur se deconnecte
       catch (SocketException e) {
    	   System.err.println("serveur parti");
   		sc.close();
   		s.close();
       }
      
       System.out.println("Client parti");
		sc.close();
		s.close();
       } catch (IOException e) {
      		System.err.println("Erreur de creation de la socket");
    	   e.printStackTrace();
       }
       catch (NoSuchElementException e) {
    	   System.err.println("No such element");
       }
	 }
}




