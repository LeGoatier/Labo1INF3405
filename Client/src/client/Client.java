package client;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;

// Application client
public class Client {
	private static Socket socket;

	public static void main(String[] args) throws Exception {
		// Adresse et port du serveur
		Scanner scanner = new Scanner(System.in);
		boolean ipValide = false;
		boolean portValide = false;
		
		inputValidationAdresse:
		while(!ipValide) {
			System.out.print("Veulliez entrer l'adresse IPv4 du serveur");
			String inputIp = scanner.nextLine();
			String[] bytes = inputIp.split("\\.");
			if(bytes.length != 4)
			{
				System.out.print("L'adresse doit être sur 4 octets");
				continue;
			}
			for(int i = 0; i < 4 ; i++)
			{
				int temp = Integer.parseInt(bytes[i]);
				if(temp < 0 || temp > 255)
				{
					System.out.print("Un octect est entre 0 et 255");
					
					continue inputValidationAdresse;
				}
					
			}
			ipValide = true;
			
		}
		
		while(!portValide) {
			System.out.print("Veulliez entrer le port du serveur du serveur "
					+ "\nil devrait ëtre entre 5000 et 5050 pour être valide");
			Integer inputPort  = scanner.nextInt();
			if(inputPort >= 5000 && inputPort <= 5050)
				 portValide = true;
			
		}
		
		
		String serverAddress = "127.0.0.1";
		int port = 5000;
		// Création d'une nouvelle connexion aves le serveur
		socket = new Socket(serverAddress, port);
		System.out.format("Serveur lancé sur [%s:%d]", serverAddress, port);
		// Céatien d'un canal entrant pour recevoir les messages envoyés, par le serveur
		DataInputStream in = new DataInputStream(socket.getInputStream());
		// Attente de la réception d'un message envoyé par le, server sur le canal
		String helloMessageFromServer = in.readUTF();
		System.out.println(helloMessageFromServer);
		// fermeture de La connexion avec le serveur
		socket.close();
	}
}