package client;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandHandler {
	String lastcmd;
	boolean hasConnection = true;
	
	String baseDir;
	
	public CommandHandler() {
		baseDir = System.getProperty("user.dir") +  "/client_root/";
	}
	
	
	
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
			FileOutputStream file = new FileOutputStream(baseDir + (lastcmd.split(" ")[lastcmd.split(" ").length -1]));
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
			System.out.println("Ack:" + ack);
			if(ack.equals("ACK-UPLOAD"))
			{
				System.out.println("on est dans le if");
				System.out.println(Paths.get(baseDir));
				byte[] data  = Files.readAllBytes(Paths.get(baseDir +lastcmd.split(" ")[lastcmd.split(" ").length -1]));
				out.writeLong(data.length);
				out.write(data);
				out.flush();
				System.out.println("On a upload tout:" + data);
			}
			
		}
		catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void handleOut( DataInputStream in, DataOutputStream out, Socket s)
	{
		
		
		switch (lastcmd.split(" ")[0]) {
		case "EXIT": 
			exit(in,out,s);
			hasConnection = false;
			break;
		case "DOWNLOAD" :
			download(in,out);
			break;
		case "UPLOAD":
			upload(in,out);
			break;
		default : 
			try 
			{
				out.writeUTF(lastcmd);
				System.out.println(in.readUTF());
			}
			catch ( IOException e )
			{
				 e.printStackTrace();
			}
				
		}
	}
	
}
