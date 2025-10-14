package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import client.IpValidater;
import client.CommandHandler;
// Application client
public class Client {
	private static Socket socket;

	public static void main(String[] args) throws Exception {
		// Adresse et port du serveur
		IpValidater ipv = new IpValidater();
		CommandHandler cmdHandler = new CommandHandler();
		String ip = ipv.getIP();
		int port = ipv.getPort();
		Scanner scanner = new Scanner(System.in);
	
		
		// Création d'une nouvelle connexion aves le serveur
		socket = new Socket(ip, port);
		// Céatien d'un canal entrant pour recevoir les messages envoyés, par le serveur
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		// Attente de la réception d'un message envoyé par le, server sur le canal
		
		// fermeture de La connexion avec le serveur
		while(true)
		{
			System.out.print(">");
			cmdHandler.lastcmd = scanner.nextLine().toUpperCase();
			cmdHandler.handleOut(in, out, socket);
			
		}
	}
}