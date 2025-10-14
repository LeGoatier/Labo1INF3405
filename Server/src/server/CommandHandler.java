package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.File;
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
			if(new File(wd.concat(words[1])).exists()) {
				wd += words[1];
			}
			break;
		case "ls":
			break;
		case "mkdir":
			File folder = new File(wd.concat(words[1]));
			if(!folder.exists()) {
				folder.mkdir();
				out.writeUTF("\033[93mFolder created\033[0m");
			}
			else {
				out.writeUTF("\033[91mFolder already Exists\033[0m");
			}
			break;
		case "delete":
			break;
		case "exit":
			break;
		}
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
}
