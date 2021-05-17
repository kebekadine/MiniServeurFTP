package serveur;
/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {
	static int numcli=0;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
			System.out.println("Serveur FTP");
			
			try {
			int i =0;
			ServerSocket serveurFTP = new ServerSocket(2121+i);
			while (true) {
			Socket socket =serveurFTP.accept();
		//creation du thread
			Thread t = new Thread(new accept(socket, numcli));
	        t.start();
	        numcli++;
			}
	        } 
			
			catch (IOException e) {
	             System.err.println("Erreur de creation du serveur");
	            e.printStackTrace();
	        }
		
		
	}
}
	
	