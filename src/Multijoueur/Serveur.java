package Multijoueur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] joueur) {
		
		ServerSocket socketserver  ;
		Socket socketduserveur ;

		try {
		
			socketserver = new ServerSocket(2009);
			socketduserveur = socketserver.accept(); 
			System.out.println("Nouveau joueur disponible !");
		        socketserver.close();
		        socketduserveur.close();

		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
