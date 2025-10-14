package server;
import java.io.DataOutputStream;
import java.io.DataInputStream;
public class CommandHandler {
	public void handleCmd(String input, DataInputStream in, DataOutputStream out) {
		String[] words = input.split(" ");
		if(input == "Upload") {
			while(true) {
				in.read()
			}
		}
	}
}
