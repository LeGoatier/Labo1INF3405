package client;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandHandler {
	String lastcmd;
	
	
	
	
	private void exit ( DataInputStream in, DataOutputStream out, Socket s)
	{
		System.out.println("Fermeture de la connection au serveur");
		try {
			s.close();
		}
		catch (IOException e) {
            e.printStackTrace();

		}
	}
	
	private void download(DataInputStream in, DataOutputStream out)
	{
		try {
			out.writeUTF(lastcmd);
			FileOutputStream file = new FileOutputStream((lastcmd.split(" ")[lastcmd.split(" ").length -1]));
			file.write(in.readAllBytes());
			file.close();
		}
		catch (IOException e) {
            e.printStackTrace();
		}
	}
	private void upload(DataInputStream in, DataOutputStream out)
	{
		try {
			out.writeUTF(lastcmd);
			String ack = in.readUTF();
			if(ack == "ACK-UPLOAD")
			{
				byte[] data  = Files.readAllBytes(Paths.get(new URI(lastcmd.split(" ")[lastcmd.split(" ").length -1])));
				out.write(data);
			}
			
		}
		catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void handleOut( DataInputStream in, DataOutputStream out, Socket s)
	{
		
		
		switch (lastcmd.split(" ")[0]) {
		case "exit": 
			exit(in,out,s);
			break;
		case "download" :
			download(in,out);
			break;
		case "upload":
			upload(in,out);
			break;
		default : 
			try 
			{
				out.writeUTF(lastcmd);
			}
			catch ( IOException e )
			{
				 e.printStackTrace();
			}
				
		}
	}
	
}
