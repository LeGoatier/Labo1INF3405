package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread { // pour traiter la demande de chaque client sur un socket particulier
	private Socket socket;
	private int clientNumber;
	private CommandHandler cmd;
	public ClientHandler(Socket socket, int clientNumber) {
		this.cmd = new CommandHandler();
		this.socket = socket;
		this.clientNumber = clientNumber;
		System.out.println("New connection with client#" + clientNumber + " at" + socket);
	}

	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // création de canal d’envoi
			out.writeUTF("\033[91mConnection Completed\033[0m\n"); // envoi de message
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while(true) {
				cmd.handle(in.readUTF(), out);
			}
		} catch (IOException e) {
			System.out.println("Error handling client# " + clientNumber + ": " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Couldn't close a socket, what's going on?");
			}
			System.out.println("Connection with client# " + clientNumber + " closed");
		}
	}
}