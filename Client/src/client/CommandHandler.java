package client;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandHandler {
	String lastcmd;
	
	public void handleIn(String ack, DataInputStream in, DataOutputStream out) {
		if(ack == "ACK-UPLOAD")
		{
			try {
				byte[] data  = Files.readAllBytes(Paths.get(new URI(lastcmd.split(" ")[lastcmd.split(" ").length -1])));
				out.write(data);
			}
			catch (IOException e) {
	            e.printStackTrace();

			}
		}
	}
	
	public void handleOut( DataInputStream in, DataOutputStream out, Socket s)
	{
		
		
		switch (lastcmd.split(" ")[0]) {
		case "exit": 
			System.out.println("Fermeture de la connection au serveur");
			try {
				s.close();
			}
			catch (IOException e) {
	            e.printStackTrace();

			}
			break;
		case "download" :
			try {
				out.writeChars(lastcmd);
				FileOutputStream file = new FileOutputStream((lastcmd.split(" ")[lastcmd.split(" ").length -1]));
				file.write(in.readAllBytes());
				file.close();
			}
			catch (IOException e) {
	            e.printStackTrace();
			}
			
			
			
		}
		
			
		
		
	}
	
}
