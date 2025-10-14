package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
public class CommandHandler {
	String wd = "";
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		try {
		String[] words = input.split(" ");
		switch(words[0]) {
		case "upload":
			out.writeUTF("ACK-UPLOAD");
			in.readAllBytes();
			break;
		case "download":
			break;
		case "echo":
			String s = "";
			for(int i = 1; i < words.length; ++i) {
				s.concat(words[i].concat(" "));
				out.writeUTF(s.concat("\n"));
			}
			break;
		case "cd":
			break;
		case "ls":
			break;
		case "mkdir":
			break;
		case "delete":
			break;
		case "exit":
			break;
		}
		if(words[0] == "upload") {
			
		}
		else if (words[0] == "echo") {
			
		}
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
}
