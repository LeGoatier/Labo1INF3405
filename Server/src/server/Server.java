package server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server {
	private static ServerSocket Listener;
	// Application Serveur
	public static void main(String[] args) throws Exception {
		IpValidater ipv = new IpValidater();
		int port = ipv.getPort();
		String ip = ipv.getIP();
		// Compteur incrémenté à chaque connexion d'un client au serveur
		int clientNumber = 0;
		// Adresse et port du serveur
		// Création de la connexien pour communiquer ave les, clients
		Listener = new ServerSocket();
		Listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(ip);
		// Association de l'adresse et du port à la connexien
		Listener.bind(new InetSocketAddress(serverIP, port));
		System.out.format("The server is running on %s:%d%n", ip, port);
		try {
			// À chaque fois qu'un nouveau client se, connecte, on exécute la fonstion
			// run() de l'objet ClientHandler
			while (true) {
				// Important : la fonction accept() est bloquante: attend qu'un prochain client
				// seconnecte
				// Une nouvetle connection : on incémente le compteur clientNumber
				new ClientHandler(Listener.accept(), clientNumber++).start();
			}
		} finally {
			// Fermeture de la connexion
			Listener.close();
		}
	}
}
