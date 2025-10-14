package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
public class CommandHandler {
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		try {
		String[] words = input.split(" ");
		if(words[0] == "upload") {
			out.writeUTF("ACK-UPLOAD");
			in.readAllBytes();
		}
		else if (words[0] == "echo") {


		}
		}catch(IOException e) {
			System.out.println("IO EXCEPTION");
		}
	}
}
