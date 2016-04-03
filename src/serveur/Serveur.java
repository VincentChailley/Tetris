package serveur;

import java.io.IOException;
import java.net.*;

public class Serveur {
	
	public static void main(String[] zero){
		
		ServerSocket socket;
		
		try {
		socket = new ServerSocket(2009);
		Thread t = new Thread(new Accepter_clients(socket));
		t.start();
		System.out.println("Connection d'un autre joueur en attente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {

	   private ServerSocket socketserver;
	   private Socket socket;
	   private int nbrclient = 1;
	   
	   public Accepter_clients(ServerSocket s){
		   socketserver = s;
	   }
		
	   public void run() {
		   try {
			   while(true){
	        		//lorsqu'un client se connecte, on accepte la connexion
	        		socket = socketserver.accept();
	        		
	        		if(nbrclient == 1){
	        			System.out.println("Connexion "+ nbrclient +": l'adversaire est prêt !");	
	        		}
	        		
	        		else{
	        			System.out.println("Connexion "+ nbrclient +": Attaque de l'adversaire !");
	        		}
	        		nbrclient++;
	             	socket.close();
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   
	   
}
