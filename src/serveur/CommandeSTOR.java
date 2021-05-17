package serveur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;

public class CommandeSTOR extends Commande {
	
	public CommandeSTOR(PrintStream ps, String commandeStr, int numcli) {
		super(ps, commandeStr, numcli);
	}

	public void execute(Dossier_client client) {
		try {
   		 //ps.println(envoie);
			Socket s;
			System.out.println("hello");
			try {
				//creation de la socket
				s = new Socket("localhost", 2121);
				
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		    PrintStream ps = new PrintStream(s.getOutputStream());
		    String reception = null;
		    //tannt que la lecture est possible
		    while((reception=br.readLine()) != null)
	           {
	               System.out.println( reception);
	           
	               if (reception.startsWith("le 8000"))
	               {
	            	 try {
	            	//reception du port 8000	 
	            	 String res= reception.split(" ")[1]; 
	      	         System.out.println(Integer.parseInt(res));
	      	         //String cmd= envoie.split(" ")[1];
	      	         //creation de la socket
	      	         Socket sock = new Socket("localhost",Integer.parseInt(res));
	      	       
	      	         String user=System.getProperty("user.dir");
	      	         File fileuser= new File(user);
	      	         //creation des chemin source et dest
	      	         Path pathout = fileuser.toPath().resolve("src/serveur/get_doc/"+ commandeArgs[0]).normalize();
	      	         Path pathin = fileuser.toPath().resolve("dossier/dos/"+commandeArgs[0]).normalize();
	      	         FileInputStream fis= new FileInputStream (pathin.toString());
	      	         
	      			 FileOutputStream fileOS = new FileOutputStream(pathout.toString());
	      			
	      			 int byte_lu;
	      			 byte[] stock= new byte[800];
	      			 //tant que la lecture est possible
	      			 while ((byte_lu= fis.read(stock)) != -1){
	      				 fileOS.write(stock);
	     
	      			 }
	      			 fis.close();
	      			 fileOS.close();
	      			 sock.close();
	            	  } 
	            	  catch (ArrayIndexOutOfBoundsException e) {
	            		  System.err.println("il faut un argument a get");
	            	  }
	      	
	      			//ps.println(envoie);
	    
			 
			 
			 
			 
		
		
		
	           }
		    	
		    	
		    	
		    	
		    	
	           }
		    
		    
		    
			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
			
   	  catch (ArrayIndexOutOfBoundsException e) {
   		  System.err.println("il faut un argument a get");
   	  }
	}
	


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
